package com.example.examplemod.mc_04_rainbowblock;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by masatoshi on 2017/03/22.
 */
public class BlockRainbow extends Block {
	private static final PropertyInteger COLOR = PropertyInteger.create("color", 0, 6);

	public BlockRainbow(){
		super (Material.ROCK);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setRegistryName("rainbow");
		setUnlocalizedName(ExampleMod.MODID + "_block_rainbow");
		setHardness(300);
		setDefaultState(blockState.getBaseState().withProperty(COLOR, 0));
	}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn){
		IBlockState blockState = worldIn.getBlockState(pos);
		int color = blockState.getValue(COLOR);
		color = color + 1;
		if (color >= 7) {
			color = 0;
		}
		worldIn.setBlockState(pos, blockState.withProperty(COLOR, color));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(COLOR, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state){
		return state.getValue(COLOR);
	}

	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, COLOR);
	}
}
