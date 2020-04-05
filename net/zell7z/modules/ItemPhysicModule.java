package net.zell7z.modules;

import net.zell7z.configuration.Configuration;

public class ItemPhysicModule {
    public static boolean isEnabled() {
        return Boolean.valueOf(Configuration.getProperty("ip"));
    }

    public static void setEnabled(boolean enabled) {
        Configuration.setProperty("ip", String.valueOf(enabled));
    }
}
