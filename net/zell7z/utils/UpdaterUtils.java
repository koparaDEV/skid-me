package net.zell7z.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import org.lwjgl.opengl.Display;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UpdaterUtils {
    private static String version = "1.2";

    public static void setVersion(String version) {
        UpdaterUtils.version = version;
    }

    public static String getVersion() {
        return version;
    }

    private static String message = "&fSprawdzanie aktualizacji...";

    private static void download() {
        new Thread(() -> {
            try {
                URL url = new URL("http://paczka.megadrop.pl/api/update/dropguard_1.8.8App.jar");
                BufferedInputStream bis = new BufferedInputStream(url.openStream());
                FileOutputStream fos = new FileOutputStream(System.getenv("APPDATA") + File.separator + ".minecraft" + File.separator + "versions" + File.separator + "dropguard_1.8.8" + File.separator + "dropguard_1.8.8Update.jar");
                byte[] buffer = new byte[1024];
                int count;
                while ((count = bis.read(buffer, 0, 1024)) != -1) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                bis.close();
                System.exit(0);
            } catch(Exception ignored) { }
        }).run();
    }

    private static Minecraft mc = Minecraft.getMinecraft();

    public static void checkVersion() {
        try {
            URL website = new URL("http://paczka.megadrop.pl/api/version.txt");
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (version.equals(inputLine)) {
                    message = "&2Twoja wersja jest aktualna!";
                    mc.displayGuiScreen(new GuiMainMenu());
                } else {
                    message = "&4Twoja wersja jest nie aktualna, pobieram najnowsza wersje!";
                    Display.setTitle("Minecraft 1.8.8 (DropGuard) - Pobieranie aktualizacji...");
                    download();
                    break;
                }
            }
        } catch (MalformedURLException e) {
            message = "&4Wystapil blad z polaczeniem serwera API!\n&2Zglosc to na naszym discordzie : https://discord.gg/SRgsuhP";
            mc.displayGuiScreen(new GuiMainMenu());
        } catch (IOException e) {
            message = "&4Brak polaczenia z internetem!";
        }
    }

    public static String getMessage() {
        return message;
    }
}