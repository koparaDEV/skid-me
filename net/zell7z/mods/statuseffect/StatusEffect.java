package net.zell7z.mods.statuseffect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.zell7z.utils.ChatUtils;
import org.lwjgl.opengl.GL11;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StatusEffect {
    protected static float zLevel = -150.0F;
    private static ScaledResolution scaledResolution;
    public static boolean enabled = true;
    private static String alignMode = "middleleft";
    private static boolean enableBackground = false;
    private static boolean enableEffectName = true;
    private static boolean enableIconBlink = true;
    private static int durationBlinkSeconds = 10;
    private static String effectNameColor = "f";
    private static String durationColor = "f";
    public static int xOffset = 2;
    public static int yOffset = 2;
    private static int yOffsetBottomCenter = 41;
    private static boolean applyXOffsetToCenter = false;
    private static boolean applyYOffsetToMiddle = false;
    private static Map<PotionEffect, Integer> potionMaxDurationMap = new HashMap();

    private static void displayStatusEffects(Minecraft mc) {
        Collection<?> activeEffects = mc.thePlayer.getActivePotionEffects();
        PotionEffect potionEffect;
        if (!activeEffects.isEmpty()) {
            int yOffset = enableEffectName ? 20 : enableBackground ? 33 : 18;
            if ((activeEffects.size() > 5) && (enableBackground)) {
                yOffset = 132 / (activeEffects.size() - 1);
            }
            int yBase = getY(activeEffects.size(), yOffset);
            for (Iterator<?> iteratorPotionEffect = activeEffects.iterator(); iteratorPotionEffect.hasNext(); yBase += yOffset) {
                potionEffect = (PotionEffect)iteratorPotionEffect.next();
                if ((!potionMaxDurationMap.containsKey(potionEffect)) || ((potionMaxDurationMap.get(potionEffect)) < potionEffect.getDuration())) {
                    potionMaxDurationMap.put(potionEffect, potionEffect.getAmplifier());
                }
                Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                int xBase = getX(enableBackground ? 120 : 22 + mc.fontRendererObj.getStringWidth("0:00"));
                String potionName = "";
                if (enableEffectName) {
                    potionName = StatCollector.translateToLocal(potion.getName());
                    if (potionEffect.getAmplifier() == 1) {
                        potionName = potionName + " II";
                    } else if (potionEffect.getAmplifier() == 2) {
                        potionName = potionName + " III";
                    } else if (potionEffect.getAmplifier() == 3) {
                        potionName = potionName + " IV";
                    } else if (potionEffect.getAmplifier() == 4) {
                        potionName = potionName + " V";
                    } else if (potionEffect.getAmplifier() == 5) {
                        potionName = potionName + " VI";
                    } else if (potionEffect.getAmplifier() == 6) {
                        potionName = potionName + " VII";
                    } else if (potionEffect.getAmplifier() == 7) {
                        potionName = potionName + " VIII";
                    } else if (potionEffect.getAmplifier() == 8) {
                        potionName = potionName + " IX";
                    } else if (potionEffect.getAmplifier() == 9) {
                        potionName = potionName + " X";
                    } else if (potionEffect.getAmplifier() > 9) {
                        potionName = potionName + " " + (potionEffect.getAmplifier() + 1);
                    }
                    xBase = getX(enableBackground ? 120 : 22 + mc.fontRendererObj.getStringWidth(potionName));
                }
                String effectDuration = Potion.getDurationString(potionEffect);
                if (enableBackground) {
                    drawTexturedModalRect(xBase, yBase, 0, 166, 140, 32, zLevel);
                }
                if (alignMode.toLowerCase().contains("right")) {
                    xBase = getX(0);
                    if (potion.hasStatusIcon()) {
                        int potionStatusIcon = potion.getStatusIconIndex();
                        if ((!enableIconBlink) || ((enableIconBlink) && (shouldRender(potionEffect, potionEffect.getDuration(), durationBlinkSeconds)))) {
                            drawTexturedModalRect(xBase + (enableBackground ? -24 : -18), yBase + (enableBackground ? 7 : 0), 0 + potionStatusIcon % 8 * 18, 198 + potionStatusIcon / 8 * 18, 18, 18, zLevel);
                        }
                    }
                    int stringWidth = mc.fontRendererObj.getStringWidth(potionName);
                    mc.fontRendererObj.drawStringWithShadow(ChatUtils.repairColors("&" + effectNameColor + potionName + "&r"), xBase + (enableBackground ? -10 : -4) - 18 - stringWidth, yBase + (enableBackground ? 6 : 0), 16777215);
                    stringWidth = mc.fontRendererObj.getStringWidth(effectDuration);
                    if (shouldRender(potionEffect, potionEffect.getDuration(), durationBlinkSeconds)) {
                        mc.fontRendererObj.drawStringWithShadow(ChatUtils.repairColors("&" + durationColor + effectDuration + "&r"), xBase + (enableBackground ? -10 : -4) - 18 - stringWidth, yBase + (enableBackground ? 6 : 0) + (enableEffectName ? 10 : 5), 16777215);
                    }
                }
                else {
                    if (potion.hasStatusIcon()) {
                        int potionStatusIcon = potion.getStatusIconIndex();
                        drawTexturedModalRect(xBase + (enableBackground ? 6 : 0), yBase + (enableBackground ? 7 : 0), potionStatusIcon % 8 * 18, 198 + potionStatusIcon / 8 * 18, 18, 18, zLevel);
                    }
                    mc.fontRendererObj.drawStringWithShadow(ChatUtils.repairColors("&" + effectNameColor + potionName + "&r"), xBase + (enableBackground ? 10 : 4) + 18, yBase + (enableBackground ? 6 : 0), 16777215);
                    if (shouldRender(potionEffect, potionEffect.getDuration(), durationBlinkSeconds)) {
                        mc.fontRendererObj.drawStringWithShadow(ChatUtils.repairColors("&" + durationColor + effectDuration + "&r"), xBase + (enableBackground ? 10 : 4) + 18, yBase + (enableBackground ? 6 : 0) + (enableEffectName ? 10 : 5), 16777215);
                    }
                }
            }
            List<PotionEffect> toRemove = new LinkedList();
            for (PotionEffect pe : potionMaxDurationMap.keySet()) {
                if (!activeEffects.contains(pe)) {
                    toRemove.add(pe);
                }
            }
            for (PotionEffect pe : toRemove) {
                potionMaxDurationMap.remove(pe);
            }
        }
    }

    private static int getX(int width) {
        if ((alignMode.equalsIgnoreCase("topcenter")) || (alignMode.equalsIgnoreCase("middlecenter")) || (alignMode.equalsIgnoreCase("bottomcenter"))) {
            return scaledResolution.getScaledWidth() / 2 - width / 2 + (applyXOffsetToCenter ? xOffset : 0);
        }
        if ((alignMode.equalsIgnoreCase("topright")) || (alignMode.equalsIgnoreCase("middleright")) || (alignMode.equalsIgnoreCase("bottomright"))) {
            return scaledResolution.getScaledWidth() - width - xOffset;
        }
        return xOffset;
    }

    private static int getY(int rowCount, int height) {
        if ((alignMode.equalsIgnoreCase("middleleft")) || (alignMode.equalsIgnoreCase("middlecenter")) || (alignMode.equalsIgnoreCase("middleright"))) {
            return scaledResolution.getScaledHeight() / 2 - rowCount * height / 2 + (applyYOffsetToMiddle ? yOffset : 0);
        }
        if ((alignMode.equalsIgnoreCase("bottomleft")) || (alignMode.equalsIgnoreCase("bottomright"))) {
            return scaledResolution.getScaledHeight() - rowCount * height - yOffset;
        }
        if (alignMode.equalsIgnoreCase("bottomcenter")) {
            return scaledResolution.getScaledHeight() - rowCount * height - yOffsetBottomCenter;
        }
        return yOffset;
    }

    private static boolean shouldRender(PotionEffect pe, int ticksLeft, int thresholdSeconds) {
        if (potionMaxDurationMap.get(pe) > 400 && ticksLeft / 20 <= thresholdSeconds) {
            return ticksLeft % 20 < 10;
        }
        return true;
    }

    public void render(Minecraft mc) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        scaledResolution = new ScaledResolution(mc);
        displayStatusEffects(mc);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void drawTexturedModalRect(int x, int y, int u, int v, int width, int height, float zLevel) {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(x, y + height, zLevel).tex((u) * var7, (v + height) * var8).endVertex();
        worldRenderer.pos(x + width, y + height, zLevel).tex((u + width) * var7, (v + height) * var8).endVertex();
        worldRenderer.pos(x + width, y, zLevel).tex((u + width) * var7, (v) * var8).endVertex();
        worldRenderer.pos(x, y, zLevel).tex((u) * var7, (v) * var8).endVertex();
        tessellator.draw();
    }
}
