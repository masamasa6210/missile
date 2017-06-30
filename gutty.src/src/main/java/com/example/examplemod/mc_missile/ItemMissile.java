package com.example.examplemod.mc_missile;

import com.example.examplemod.ExampleMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMissile extends Item {
    public ItemMissile() {
        setCreativeTab(CreativeTabs.COMBAT);
        setRegistryName("missile");
        setUnlocalizedName(ExampleMod.MODID + "_missile");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (!playerIn.capabilities.isCreativeMode) {
            --itemStackIn.stackSize;
        }

        if (!worldIn.isRemote) {
            EntityMissile entity = new EntityMissile(worldIn, playerIn);
            entity.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntityInWorld(entity);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
    }
}