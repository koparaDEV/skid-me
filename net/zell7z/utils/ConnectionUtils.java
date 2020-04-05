package net.zell7z.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionUtils {
    public static boolean check() {
        try {
            URL website = new URL("http://paczka.megadrop.pl/");
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            in.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
