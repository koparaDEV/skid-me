package net.zell7z.managers;


import net.zell7z.objects.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class WingsManager {

    public static List<User> userList = new ArrayList<User>();

    public static void loadWings() {
        userList.clear();
        try {
            final URL website = new URL("http://paczka.megadrop.pl/api/wings.txt");
            final URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains(":")) {
                    String name = "";
                    int id = 0;
                    String[] ss = inputLine.split(":");
                    name = ss[0];
                    id = Integer.valueOf(ss[1]);
                    userList.add(new User(name, id));
                }
            }
        } catch (IOException ignored) {

        }
    }

    public static int getWingsID(String user) {
        int id = 997;
        for (User p : userList) {
            if (p.getUsername().equalsIgnoreCase(user)) {
                id = p.getId();
            }
        }
        return id;
    }

    public static boolean haveWings(String user) {
        boolean r = false;
        for (User p : userList) {
            if (p.getUsername().equalsIgnoreCase(user)) {
                r = true;
            }
        }
        return r;
    }

    public static String lastPlayer() {
        return userList.get(userList.size() - 1).getUsername();
    }

    public static boolean isEmpty() {
        return userList.isEmpty();
    }
}