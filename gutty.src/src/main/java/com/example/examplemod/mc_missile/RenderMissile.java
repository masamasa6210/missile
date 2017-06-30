package com.example.examplemod.mc_missile;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMissile extends Render<EntityMissile> {
    private static final ResourceLocation texture =
            new ResourceLocation("examplemod:textures/entity/missile.png");
    private final ModelMissile model = new ModelMissile();

    public RenderMissile(RenderManager rendermanagerIn) {
        super(rendermanagerIn);
    }

    @Override
    public void doRender(EntityMissile entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.bindEntityTexture(entity);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.translate((float) x, (float) y, (float) z);
        float yaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90;
        GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
        float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
        GlStateManager.rotate(pitch, 0.0F, 0.0F, 1.0F);
        float scale = 0.5F;
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.scale(scale, scale, scale);
        GlStateManager.translate(-4.0F, 0.0F, 0.0F);

        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        model.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, (float) (entity.rotationPitch - Math.PI / 2), scale);

        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityMissile entity) {
        return texture;
    }
}