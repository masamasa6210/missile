package com.example.examplemod.mc_launching;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.mc_missile.EntityMissile;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by masatoshi on 2017/05/14.
 */
public class BlockLaunchingInput extends Block {
	private static final PropertyInteger STATE = PropertyInteger.create("state", 0, 3);
	private static final PropertyInteger DIRECTION = PropertyInteger.create("direction", 0, 3);

	public BlockLaunchingInput(){
		super(Material.ROCK);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setRegistryName("launching_input");
		setUnlocalizedName(ExampleMod.MODID + "_launching_input");
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
			if (!worldIn.isRemote){
				EntityMissile entity = new EntityMissile(worldIn, pos.getX(), pos.getY() + 7, pos.getZ());
				entity.setHeadingFromLauncher(-80, 90 * state.getValue(DIRECTION), 5f, 0);
				worldIn.spawnEntityInWorld(entity);
			}
		}else{
			System.out.println("End Input");
		}
		worldIn.setBlockState(pos, state.withProperty(STATE, power / 4));
	}
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		int direction = state.getValue(DIRECTION);
		direction = direction + 1;
		if (direction > 3) {
			direction = 0;
		}
		worldIn.setBlockState(pos, state.withProperty(DIRECTION, direction));
		return true;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		int state = meta & 0b0011;
		int direction = (meta & 0b1100) >>> 2;
		return getDefaultState().withProperty(STATE, state).withProperty(DIRECTION, direction);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(DIRECTION) << 2 | state.getValue(STATE);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, STATE, DIRECTION);
	}
}
