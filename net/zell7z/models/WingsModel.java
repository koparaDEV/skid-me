package net.zell7z.models;

import net.zell7z.models.base.WingsModelBase;
import net.zell7z.modules.WingsModule;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class WingsModel extends WingsModelBase {

    private ModelRenderer rightWing;
    private ModelRenderer leftWing;
    private ResourceLocation wingsImages;

    public void init(int wingsID) {
        this.wingsImages = new ResourceLocation("textures/dropguard/cosmetics/wings/wings" + wingsID + ".png");
        this.leftWing = new ModelRenderer(this, 0, 0);
        this.rightWing = new ModelRenderer(this, 0, 0);

        if (WingsModule.isEnabled()) {
            this.leftWing.addBox(0.3F, -6F, 1.5F, 0, 32, 32, 0.05F);
            this.rightWing.addBox(0.3F, -6F, 1.5F, 0, 32, 32, 0.05F);
        }

        this.leftWing.setRotationPoint(0f, -1f, -1f);
        this.rightWing.setRotationPoint(0f, -1f, -1f);
        this.leftWing.setTextureSize(16,64);
        this.rightWing.setTextureSize(16,64);
    }

    public void render(EntityPlayerSP player, boolean isSneaking) {
        this.postRender(player, isSneaking);
    }

    private void postRender(EntityPlayerSP player, boolean isSneaking) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.wingsImages);
        this.renderWings(player, player.onGround, isSneaking);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    private void renderWings(EntityPlayerSP player, boolean isGround, boolean isSneaking) {

        float angle = getWingAngle(45.0F, 6000, player.getYOffset());
        this.setRotation(this.leftWing, (float) Math.toRadians(-angle - 3.0F));
        this.setRotation(this.rightWing, (float) Math.toRadians(angle + 3.0F));

        if (isSneaking)  {
            this.leftWing.rotateAngleX = 0.27f;
            this.rightWing.rotateAngleX = 0.27f;
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(0F, 0F, 1.7F * 0.0625F);
        this.leftWing.render(0.0625F);
        this.rightWing.render(0.0625F);
        GL11.glPopMatrix();
    }
}