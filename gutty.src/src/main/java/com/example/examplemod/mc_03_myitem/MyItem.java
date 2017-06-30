package com.example.examplemod.mc_03_myitem;

import com.example.examplemod.ExampleMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by masatoshi on 2017/03/22.
 */
public class MyItem extends ItemSword {
	public MyItem(){
		super(EnumHelper.addToolMaterial("my_sword",4,200,16.0f,1.0f,22));
		setCreativeTab(CreativeTabs.COMBAT);
		setRegistryName("my_sword");
		setUnlocalizedName(ExampleMod.MODID + "_my_sword");
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)	{
		if(stack == null){
			return true;
		}
		if(!(attacker instanceof EntityPlayer)){
			return true;
		}

		target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,1200,0));
		return true;
	}
}
