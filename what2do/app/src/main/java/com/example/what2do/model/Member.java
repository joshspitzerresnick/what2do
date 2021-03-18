package com.example.what2do.model;

public class Member {
    private String name;
    private int image, icon;

    public Member(String name, int image, int icon) {
        this.name = name;
        this.image = image;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getIcon() {
        return icon;
    }
}
