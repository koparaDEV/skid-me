package net.zell7z.modules;

import net.minecraft.client.Minecraft;
import net.zell7z.configuration.Configuration;

public class SprintModule {

    private static final Minecraft minecraft = Minecraft.getMinecraft();

    public static boolean isEnabled() {
        return Boolean.valueOf(Configuration.getProperty("as"));
    }

    public static void setEnabled(boolean enabled) {
        Configuration.setProperty("as", String.valueOf(enabled));
    }

    public static void update() {
        if(isEnabled()) {
            if(minecraft.gameSettings.keyBindForward.isKeyDown()) {
                minecraft.thePlayer.setSprinting(true);
            }
        }
    }
}
