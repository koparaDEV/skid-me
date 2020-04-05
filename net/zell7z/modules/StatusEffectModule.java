package net.zell7z.modules;

import net.zell7z.configuration.Configuration;

public class StatusEffectModule {
    public static boolean isEnabled() {
        return Boolean.valueOf(Configuration.getProperty("seffect"));
    }

    public static void setEnabled(boolean enabled) {
        Configuration.setProperty("seffect", String.valueOf(enabled));
    }
}
