package net.zell7z.mods.compass;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class Compass {
    private FontRenderer fontRenderer;
    private ScaledResolution scaledResolution;
    private int clientWidth;
    private int centerX;

    private int offY = 0;
    private int offsetAll = 0;
    public int width = 120;
    public int height = 20;

    public void drawCompass() {
        fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        clientWidth = scaledResolution.getScaledWidth();
        centerX = clientWidth / 2;
        int direction = normalize((int) Minecraft.getMinecraft().thePlayer.rotationYaw);
        offsetAll = (clientWidth * direction / 360);
        this.renderMarker();
        this.drawDirection("S", 0, 1.5D);
        this.drawDirection("SW", 45, 1.5D);
        this.drawDirection("W", 90, 1.5D);
        this.drawDirection("NW", 135, 1.5D);
        this.drawDirection("N", 180, 1.5D);
        this.drawDirection("NE", 225, 1.5D);
        this.drawDirection("E", 270, 1.5D);
        this.drawDirection("SE", 315, 1.5D);
    }

    private void renderMarker() {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        int colorMarker = 0;
        GlStateManager.color((colorMarker >> 16 & 0xFF) / 255.0F, (colorMarker >> 8 & 0xFF) / 255.0F, (colorMarker & 0xFF) / 255.0F, 1.0F);

        worldrenderer.begin(6, DefaultVertexFormats.POSITION);
        worldrenderer.pos(centerX, offY + 3, 0.0D).endVertex();
        worldrenderer.pos(centerX + 3, offY, 0.0D).endVertex();
        worldrenderer.pos(centerX - 3, offY, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    private void drawDirection(String dir, int degrees, double scale) {
        int offset = clientWidth * degrees / 360 - offsetAll;
        if (offset > clientWidth / 2) {
            offset -= clientWidth;
        }
        if (offset < -clientWidth / 2) {
            offset += clientWidth;
        }
        double opacity = 1.0D - Math.abs(offset) / (width / 2.0D);
        if (opacity > 0.1D) {
            int textColor = 0xFFFFFF;
            int color = textColor | (int) (opacity * 255.0D) << 24;
            int posX = centerX + offset - (int) (this.fontRenderer.getStringWidth(dir) * scale / 2.0D);
            int posY = offY + height / 2 - (int) (this.fontRenderer.FONT_HEIGHT * scale / 2.0D);

            GL11.glEnable(3042);
            GL11.glPushMatrix();
            GL11.glTranslated(-posX * (scale - 1.0D), -posY * (scale - 1.0D), 0.0D);
            GL11.glScaled(scale, scale, 1.0D);
            this.fontRenderer.drawStringWithShadow(dir, posX, posY, color);
            GL11.glPopMatrix();
            GL11.glDisable(3042);
        }
    }

    private int normalize(int direction) {
        if (direction > 360) {
            direction %= 360;
        }
        while (direction < 0) {
            direction += 360;
        }
        return direction;
    }
}

