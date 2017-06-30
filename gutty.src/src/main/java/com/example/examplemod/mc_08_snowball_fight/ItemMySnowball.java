package com.example.examplemod.mc_08_snowball_fight;

import com.example.examplemod.ExampleMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.swing.*;

/**
 * Created by masatoshi on 2017/03/27.
 */
public class ItemMySnowball extends ItemSnowball {
	public ItemMySnowball(){
		setCreativeTab(CreativeTabs.COMBAT);
		setRegistryName("my_snowball");
		setUnlocalizedName(ExampleMod.MODID + "_my_snowball");
	}
	@Override
	public  ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){

		if(!playerIn.capabilities.isCreativeMode){
			--itemStackIn.stackSize;
		}

		worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW,
				SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat()* 0.4F + 0.8F));

		if(!worldIn.isRemote){
			EntityMySnowball entity = new EntityMySnowball(worldIn, playerIn);
			entity.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.spawnEntityInWorld(entity);
		}

			return new  ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}
}
