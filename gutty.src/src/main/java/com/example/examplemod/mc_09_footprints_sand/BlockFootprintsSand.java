package com.example.examplemod.mc_09_footprints_sand;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by masatoshi on 2017/03/27.
 */
public class BlockFootprintsSand extends Block {
	private static final PropertyInteger COLOR =	 PropertyInteger.create("color", 0, 4);
	private static final AxisAlignedBB BLOCK_COLLISION = new AxisAlignedBB(0, 0, 0, 1, 0.9, 1);

	public BlockFootprintsSand() {
		super(Material.SAND);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setRegistryName("footprints_sand");
		setUnlocalizedName(ExampleMod.MODID + "_block_footprints_sand");
		setDefaultState(blockState.getBaseState().withProperty(COLOR, 0));
	}

	@Override
	public IBlockState getStateFromMeta(int meta){
		return getDefaultState().withProperty(COLOR, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(COLOR);
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, COLOR);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if(entityIn instanceof EntityPlayer && worldIn.getBlockState(pos).getValue(COLOR) != 4){
			worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(COLOR, 4));
			worldIn.scheduleBlockUpdate(pos.toImmutable(), this, 5, 100);
		}
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
		return BLOCK_COLLISION;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand){
		int next = state.getValue(COLOR) - 1;
		worldIn.setBlockState(pos, state.withProperty(COLOR, next));
		if(next != 0){
			worldIn.scheduleBlockUpdate(pos, this, 5, 100);
		}
	}
}
