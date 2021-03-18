package com.example.what2do.model;

import com.example.what2do.R;

public class Member {
    private String name;
    private int image;
    private int icon = R.drawable.group_wait;
    private boolean isUser = false;

    public Member(String name, int image, boolean isUser) {
        this.name = name;
        this.image = image;
        this.isUser = isUser;
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

    public boolean isUser() {
        return isUser;
    }
}
