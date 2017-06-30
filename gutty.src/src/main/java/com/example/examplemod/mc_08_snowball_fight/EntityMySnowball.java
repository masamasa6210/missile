package com.example.examplemod.mc_08_snowball_fight;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnowBlock;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by masatoshi on 2017/03/27.
 */
public class EntityMySnowball extends EntitySnowball {
	public static final int Entity_ID = 3;
	private static final float DAMAGE = 2.0F;

	public EntityMySnowball(World worldIn){
		super(worldIn);
	}

	public EntityMySnowball(World worldIn, EntityLivingBase throwerIn){
		super(worldIn, throwerIn);
	}

	public EntityMySnowball(World worldIn, double x, double y, double z ){
		super(worldIn, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result){
		if(result.typeOfHit == RayTraceResult.Type.BLOCK){

			switch (result.sideHit){
				case NORTH:
				case SOUTH:
				case WEST:
				case EAST:
					Block block = worldObj.getBlockState(result.getBlockPos()).getBlock();
					if (block instanceof BlockSnowBlock){
						worldObj.setBlockToAir(result.getBlockPos());
					}
					break;
			}

		}else if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), DAMAGE);
		}

		for (int i = 0; i < 8; i++){
			worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, posX, posY, posZ, 0, 0, 0);
		}
		if(!worldObj.isRemote){
			setDead();
		}
		super.onImpact(result);
	}
}

