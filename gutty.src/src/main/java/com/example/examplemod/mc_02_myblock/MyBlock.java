package com.example.examplemod.mc_02_myblock;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by masatoshi on 2017/03/21.
 */
public class MyBlock extends Block{
public MyBlock(){
		super(Material.ROCK);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setRegistryName("myblock");
		setUnlocalizedName(ExampleMod.MODID+"_myblock");
		System.out.println();
			}
		}
