package com.example.examplemod.mc_11_explosive_arrow;

import com.example.examplemod.ExampleMod;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by masatoshi on 2017/04/25.
 */
public class RenderExplosiveArrow extends RenderArrow<EntityExplosiveArrow> {

	private static final ResourceLocation TEXTURE_EXPLOSIVE_ARROW =
			new ResourceLocation(ExampleMod.MODID, "textures/entity/explosive_arrow.png");

	public RenderExplosiveArrow(RenderManager manager){
		super(manager);
	}

	protected ResourceLocation getEntityTexture(EntityExplosiveArrow entity){
		return TEXTURE_EXPLOSIVE_ARROW;
	}
}
