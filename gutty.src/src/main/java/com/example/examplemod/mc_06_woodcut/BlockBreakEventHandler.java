package com.example.examplemod.mc_06_woodcut;

import com.google.common.eventbus.Subscribe;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by masatoshi on 2017/03/23.
 */
public class BlockBreakEventHandler {
	private static final int MAX_DEPTH = 5;

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event) {
		EntityPlayer player = event.getPlayer();
		if (player == null){
			return;
		}

		if(player.getHeldItemMainhand() == null){
			return;
		}

		Item item = player.getHeldItemMainhand().getItem();
		if (!(item == Items.WOODEN_AXE ||
				item == Items.STONE_AXE ||
				item == Items.IRON_AXE ||
				item == Items.GOLDEN_AXE ||
				item == Items.DIAMOND_AXE)) {
			return;
		}
		net.minecraft.block.Block block = event.getState().getBlock();
		if (block != Blocks.LOG && block != Blocks.LOG2) {
			return;
		}
		event.setCanceled(true);
		BlockPos pos = event.getPos();
		breakBlock(event.getWorld(), pos.getX(), pos.getY(), pos.getZ(), 1);
	}
	private void breakBlock(World world, int x, int y, int z, int depth){
		if (depth > MAX_DEPTH) {
			return;
		}
		BlockPos pos = new BlockPos(x, y, z);
		IBlockState blockState = world.getBlockState(pos);
		net.minecraft.block.Block block = blockState.getBlock();
		if(block ==Blocks.LOG || block == Blocks.LOG2 || block == Blocks.LEAVES || block == Blocks.LEAVES2){
			block.dropBlockAsItem(world, pos, blockState, 0);
			world.setBlockToAir(pos);
			if (block == Blocks.LOG || block == Blocks.LOG2){
					breakBlock(world, x, y + 1, z, depth );
			}
			breakBlock(world, x + 1, y, z, depth + 1);
			breakBlock(world, x - 1, y, z, depth + 1);
			breakBlock(world, x, y, z + 1, depth + 1);
			breakBlock(world, x, y, z - 1, depth + 1);
		}
	}
}