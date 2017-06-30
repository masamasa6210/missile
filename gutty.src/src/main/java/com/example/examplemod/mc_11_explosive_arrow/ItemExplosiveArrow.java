package com.example.examplemod.mc_11_explosive_arrow;

import com.example.examplemod.ExampleMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by masatoshi on 2017/04/25.
 */
public class ItemExplosiveArrow extends ItemArrow {
	public ItemExplosiveArrow(){
		super();
		setCreativeTab(CreativeTabs.COMBAT);
		setRegistryName("explosive_arrow");
		setUnlocalizedName(ExampleMod.MODID + "_explosive arrow");
	}

	@Override
	public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter){
		return new EntityExplosiveArrow(worldIn, shooter);
	}
}
