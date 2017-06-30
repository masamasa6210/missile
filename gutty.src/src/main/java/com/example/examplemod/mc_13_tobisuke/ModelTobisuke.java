package com.example.examplemod.mc_13_tobisuke;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by masatoshi on 2017/04/30.
 */
@SideOnly(Side.CLIENT)
public class ModelTobisuke extends ModelBase {

	public ModelRenderer body;
//	public ModelRenderer head;

	public ModelTobisuke(){
		float f = 4.0F;
		body = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);

		body.setTextureOffset(0,0);
		body.addBox(-0.5f, -13, -0.5f, 1, 7, 1);

		body.setTextureOffset(0,0);
		body.addBox(-1.5f, -10, -1.5f, 3, 3, 3);

		body.setTextureOffset(0, 0);
		body.addBox(-2.5F, -7, -2.5F, 5, 30, 5);

		//foot
		body.setTextureOffset(0, 0);
		body.addBox(-4.5F, 13, -2.5F, 2, 10, 5);

		body.setTextureOffset(0, 0);
		body.addBox(-6.5F, 18, -2.5F, 2, 5, 5);

		body.setTextureOffset(0, 0);
		body.addBox(-2.5F, 13, -4.5F, 5, 10, 5);

		body.setTextureOffset(0, 0);
		body.addBox(-2.5F, 18, -6.5F, 5, 5, 5);

		body.setTextureOffset(0, 0);
		body.addBox(2.5F, 13, -2.5F, 2, 10, 5);

		body.setTextureOffset(0, 0);
		body.addBox(4.5F, 18, -2.5F, 2, 5, 5);

		body.setTextureOffset(0, 0);
		body.addBox(-2.5F, 13, 2.5F, 5, 10, 2);

		body.setTextureOffset(0, 0);
		body.addBox(-2.5F, 18, 4.5F, 5, 5, 2);

//		head = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
//
//		head.setTextureOffset(0, 0);
//		head.addBox(-3.0F, -1.0F, -7.0F, 6, 6, 10);
//
//		head.setTextureOffset(24, 0);
//		head.addBox(-1.5F, 3.0F, -8.0F, 3, 1, 1);
//		head.setRotationPoint(0.0F, 0.0F + f + 9.0F, 0.0F);
//
//		body = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
//
//		body.setTextureOffset(0, 18);
//		body.addBox(-4.5F, -6.0F, -7.5F, 9, 6, 12);
//
//		body.setTextureOffset(30, 0);
//		body.addBox(-5.5F, -5.0F, -7.0F, 1, 4, 8);
//
//		body.setTextureOffset(30, 0);
//		body.addBox(4.5F, -5.0F, -7.0F, 1, 4, 8);
//		body.setRotationPoint(0.0F, 0.0F + f + 20.0F,0.0F);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
//		head.rotateAngleY = netHeadYaw / (180f / (float) Math.PI) * 0.25f;
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
//		head.render(scale);
		body.render(scale);
	}
}
