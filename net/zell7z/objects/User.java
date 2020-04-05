package net.zell7z.objects;

public class User {

    private String username;
    private int id;

    public User(String name, int id) {
        this.username = name;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }
}
