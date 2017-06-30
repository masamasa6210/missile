package com.example.examplemod.mc_10_biome;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomePlains;

import javax.swing.text.html.parser.Entity;

/**
 * Created by masatoshi on 2017/04/23.
 */
public class BiomeMyBiome extends BiomePlains {
	private static BiomeProperties properties = new Biome.BiomeProperties("MyBiome")
			.setTemperature(1F)
			.setRainfall(1F)
			.setBaseHeight(-0.25F)
			.setHeightVariation(0.4F);


	public BiomeMyBiome(){
		super(false, properties);

		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityVillager.class, 30, 5,5));

		theBiomeDecorator.treesPerChunk = 15;
		theBiomeDecorator.flowersPerChunk = 10;

	}

}
