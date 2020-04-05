package net.zell7z.modules;

import net.zell7z.configuration.Configuration;

public class BackgroundChatModule {

    public static boolean isEnabled() {
        return Boolean.valueOf(Configuration.getProperty("bchat"));
    }

    public static void setEnabled(boolean enabled) {
        Configuration.setProperty("bchat", String.valueOf(enabled));
    }
}
