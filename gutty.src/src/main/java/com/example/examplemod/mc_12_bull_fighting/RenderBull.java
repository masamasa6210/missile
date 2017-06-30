package com.example.examplemod.mc_12_bull_fighting;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.swing.text.html.parser.Entity;

/**
 * Created by masatoshi on 2017/04/25.
 */
public class RenderBull extends RenderLiving<EntityBull> {
	private static final ResourceLocation COW_TEXTURES = new ResourceLocation("textures/entity/cow/cow.png");

	public RenderBull(RenderManager renderManagerIn){
		super(renderManagerIn, new ModelCow(), 0.7f);
	}

	protected ResourceLocation getEntityTexture(EntityBull entity){
		return COW_TEXTURES;
	}
}
