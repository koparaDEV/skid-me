package net.zell7z.modules;

import net.zell7z.configuration.Configuration;

public class DesignModule {

    public static boolean isEnabled() {
        return Boolean.valueOf(Configuration.getProperty("redesign"));
    }

    public static void setEnabled(boolean enabled) {
        Configuration.setProperty("redesign", String.valueOf(enabled));
    }
}
