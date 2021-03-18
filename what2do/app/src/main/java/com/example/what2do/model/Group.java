package com.example.what2do.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private int icon;

    private List<Member> groupMembers;

    public Group(String name, int icon, List<Member> groupMembers) {
        this.name = name;
        this.icon = icon;
        this.groupMembers = groupMembers;
    }

    public Group(String name, int icon, Member... members) {
        this(name, icon, new ArrayList<>());
        this.name = name;
        this.icon = icon;
        for(Member m: members) {
            groupMembers.add(m);
        }
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public List<Member> getGroupMembers() {
        return groupMembers;
    }
}
