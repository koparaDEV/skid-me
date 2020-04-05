package net.zell7z.configuration;

import net.minecraft.client.Minecraft;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class Configuration {

    private static Properties properties = new Properties();
    private static Map<String, String> propertiesMap = new ConcurrentHashMap<>();
    private static File configFile;

    public static void load() throws Exception {
        final File dirFile = new File(Minecraft.getMinecraft().mcDataDir, ".dropguard");
        if (!dirFile.exists()) dirFile.mkdir();
        configFile = new File(dirFile, "config.properties");
        if (!configFile.exists()) {
            configFile.createNewFile();
        }
        final FileInputStream stream = new FileInputStream(configFile);
        properties.load(stream);
        propertiesMap.clear();
        properties.stringPropertyNames().forEach(property -> propertiesMap.put(property, properties.getProperty(property)));
    }

    private static void save() throws Exception {
        if (configFile != null) {
            final FileOutputStream stream = new FileOutputStream(configFile);
            propertiesMap.forEach((property, value) -> properties.setProperty(property, value));
            properties.store(stream, null);
        }
    }

    public static String getProperty(String property) {
        return propertiesMap.get(property);
    }

    public static void setProperty(String property, String value) {
        propertiesMap.remove(property);
        propertiesMap.put(property, value);
        try {
            save();
            load();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
