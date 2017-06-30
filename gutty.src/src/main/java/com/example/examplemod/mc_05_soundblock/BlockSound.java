package com.example.examplemod.mc_05_soundblock;

import com.example.examplemod.ExampleMod;
import com.oracle.webservices.internal.api.message.PropertySet;
import javafx.scene.paint.Material;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by masatoshi on 2017/03/22.
 */
public class BlockSound extends Block {

	private static final PropertyInteger SOUND = PropertyInteger.create("sound",0 , 2);

	public BlockSound() {
		super(net.minecraft.block.material.Material.ROCK);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setRegistryName("sound");
		setUnlocalizedName(ExampleMod.MODID + "_block_sound");
		setHardness(30);
		setDefaultState(blockState.getBaseState().withProperty(SOUND, 0));
	}
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn){
		if (playerIn == null){
			return;
		}

		if (playerIn.getHeldItemMainhand() == null){
			return;
		}

		Item item = playerIn.getHeldItemMainhand().getItem();

		if (item != Items.STICK) {
			return;
		}
		IBlockState blockState = worldIn.getBlockState(pos);
		int sound = blockState.getValue(SOUND);

		worldIn.playSound(playerIn, pos, ExampleMod.soundEvents[sound], SoundCategory.RECORDS, 1.0f, 1.0f);

		if (!worldIn.isRemote) {
			String s = Integer.toString(this.plus(sound));
			playerIn.addChatComponentMessage(new TextComponentString(s + "番目の曲に変更しました"));
		}
	}
	@Override
	public IBlockState getStateFromMeta(int meta){
		return getDefaultState().withProperty(SOUND, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state){
		return state.getValue(SOUND);
	}

	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, SOUND);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
									@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ	){

		IBlockState blockState = worldIn.getBlockState(pos);
		int sound = blockState.getValue(SOUND);

		sound = this.plus(sound);

		if (sound >= 3) {
			sound = 0;
		}

		worldIn.setBlockState(pos, blockState.withProperty(SOUND, sound));

		if (!worldIn.isRemote) {
			String s = Integer.toString(this.plus(sound));
			playerIn.addChatComponentMessage(new TextComponentString(s + "番目の曲を再生中です。"));
		}
		return true;
	}
	public int plus(int count) {
		count = count + 1;
		return count;
	}
}