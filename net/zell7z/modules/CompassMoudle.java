package net.zell7z.modules;

import net.zell7z.configuration.Configuration;

public class CompassMoudle {
    public static boolean isEnabled() {
        return Boolean.valueOf(Configuration.getProperty("achunk"));
    }

    public static void setEnabled(boolean enabled) {
        Configuration.setProperty("achunk", String.valueOf(enabled));
    }
}
