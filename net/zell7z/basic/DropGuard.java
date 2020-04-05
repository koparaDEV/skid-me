package net.zell7z.basic;

import net.zell7z.configuration.Configuration;
import net.zell7z.mods.memoryfix.MemoryUtilization;
import org.lwjgl.opengl.Display;

public class DropGuard {

    public static void startClient() {
        new MemoryUtilization().run();
        Display.setTitle("Minecraft 1.8.8 (DropGuard)");
        try {
            Configuration.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
