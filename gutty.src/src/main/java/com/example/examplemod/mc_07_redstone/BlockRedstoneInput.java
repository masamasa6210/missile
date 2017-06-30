package com.example.examplemod.mc_07_redstone;

import com.example.examplemod.ExampleMod;
import com.oracle.webservices.internal.api.message.PropertySet;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by masatoshi on 2017/03/23.
 */
public class BlockRedstoneInput extends Block {
	private static final PropertyInteger STATE = PropertyInteger.create("state", 0, 3);

	public BlockRedstoneInput(){
		super(Material.ROCK);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setRegistryName("redstone_input");
		setUnlocalizedName(ExampleMod.MODID + "_redstone_input");
		setHardness(30);
		setDefaultState(blockState.getBaseState().withProperty(STATE, 0));
	}
	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn){
		int power = worldIn.isBlockIndirectlyGettingPowered(pos);
		if(power != 0) {
			System.out.println("Start Input");
			System.out.println("power =" + power);
		}else{
			System.out.println("End Input");
		}
		worldIn.setBlockState(pos, state.withProperty(STATE, power / 4));
	}

	@Override
	public IBlockState getStateFromMeta(int meta){
		return getDefaultState().withProperty(STATE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state){
		return state.getValue(STATE);
	}

	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, STATE);
	}
}
