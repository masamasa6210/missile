package com.example.examplemod.mc_11_explosive_arrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by masatoshi on 2017/04/25.
 */
public class EntityExplosiveArrow extends EntityArrow {
	public static final int ENTITY_ID = 0;
	public EntityExplosiveArrow(World worldIn) {
		super(worldIn);
	}

	public EntityExplosiveArrow(World worldIn, double x, double y, double z){
		super(worldIn, x, y, z);
	}

	public EntityExplosiveArrow(World worldIn, EntityLivingBase shooter){
		super(worldIn, shooter);
	}

	@Override
	protected ItemStack getArrowStack(){
		return new ItemStack(Items.ARROW);
	}

	@Override
	protected void onHit(RayTraceResult rayTraceResultIn){
		if(!worldObj.isRemote){
			float power = 4;
			boolean blockBreak = true;
			worldObj.createExplosion(this, posX, posY, posZ, power, blockBreak);
			setDead();
		}
	}
}
