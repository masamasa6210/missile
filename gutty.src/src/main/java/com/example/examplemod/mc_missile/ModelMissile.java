package com.example.examplemod.mc_missile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by masatoshi on 2017/05/06.
 */
@SideOnly(Side.CLIENT)
public class ModelMissile extends ModelBase {

	public ModelRenderer body;

	public ModelMissile(){
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
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		body.render(scale);
	}
}