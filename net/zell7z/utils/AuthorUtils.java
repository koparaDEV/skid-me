package net.zell7z.utils;

public class AuthorUtils {
    public static String getAuthors(String username) {
        if(username.equalsIgnoreCase("Gr0chu") || username.equalsIgnoreCase("_zell7z") || username.equalsIgnoreCase("www_all0w_pl")) {
            return username + ChatUtils.repairColors(" &7- &fDropGuard");
        }
        return username;
    }
}
