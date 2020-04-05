package net.zell7z.gui.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.zell7z.mods.compass.Compass;
import net.zell7z.mods.statuseffect.StatusEffect;
import net.zell7z.modules.CompassMoudle;
import net.zell7z.modules.StatusEffectModule;

public class GuiInGameHook extends GuiIngame {
    private static Minecraft mc = Minecraft.getMinecraft();
    private static Compass compass;
    private static StatusEffect statusEffect;

    public GuiInGameHook(Minecraft mcIn) {
        super(mcIn);
        compass = new Compass();
        statusEffect = new StatusEffect();
    }

    @Override
    public void renderGameOverlay(float partialTicks) {
        super.renderGameOverlay(partialTicks);
        if ((!mc.gameSettings.showDebugInfo)) {
            if(CompassMoudle.isEnabled()) compass.drawCompass();
            if(StatusEffectModule.isEnabled()) statusEffect.render(mc);
        }
    }
}
