package net.zell7z.modules;

import net.zell7z.configuration.Configuration;

public class WingsModule {
    public static boolean isEnabled() {
        return Boolean.valueOf(Configuration.getProperty("wings"));
    }

    public static void setEnabled(boolean enabled) {
        Configuration.setProperty("wings", String.valueOf(enabled));
    }
}
