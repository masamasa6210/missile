package com.example.examplemod.mc_10_biome;

import jdk.nashorn.internal.ir.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by masatoshi on 2017/04/23.
 */
public class BiomeIceberg extends Biome {
	private static BiomeProperties properties = new Biome.BiomeProperties("Iceberg")
			.setTemperature(0F)
			.setRainfall(1F)
			.setSnowEnabled()
			.setHeightVariation(0.75F);

	public BiomeIceberg(){
		super(properties);
		this.topBlock = Blocks.ICE.getDefaultState();
		this.fillerBlock = Blocks.PACKED_ICE.getDefaultState();
	}

	@Override
	public int getSkyColorByTemp(float currentTemperature){
		return 0xFFFFFF;
	}

	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal){
		boolean is_top = true;
		int x1 = x & 15;
		int z1 = z & 15;

		for(int y = 255; y >= 0; y--){
			if(y <= rand.nextInt(4)){
				chunkPrimerIn.setBlockState(x1, y, z1, Blocks.BEDROCK.getDefaultState());
			} else if(y <= 5  + rand.nextInt(20)){
				chunkPrimerIn.setBlockState(x1, y, z1, Blocks.STONE.getDefaultState());
			}else{
				IBlockState block = chunkPrimerIn.getBlockState(x1, y, z1);

				if(block.getBlock() != Blocks.AIR){
					if(is_top) {
						chunkPrimerIn.setBlockState(x1, y, z1, this.topBlock);
						is_top = false;
					}else{
						chunkPrimerIn.setBlockState(x1, y, z1, this.fillerBlock);
					}
				}
			}
		}
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos){
		new WorldGenKamakura().generate(worldIn, rand, pos);
		super.decorate(worldIn, rand, pos);
	}
}

