package com.example.what2do.model;

import com.example.what2do.R;

import java.util.ArrayList;
import java.util.List;

public class FakeBackend {
    private static List<Group> groups;
    private static List<Match> matches; //holds top 3
    // need genres list
    //activities lists for genres

    public static void init() {
        if(groups != null) {
            return;
        }

        groups = new ArrayList<>();

        groups.add(new Group("Family Group", R.drawable.man,
                new Member("Dad",       R.drawable.man,     false),
                new Member("Mom",       R.drawable.woman,   false),
                new Member("User",      R.drawable.man,     true),
                new Member("Sister",    R.drawable.woman,   false),
                new Member("Brother",   R.drawable.man,     false)
        ));

        groups.add(new Group("Sibling Group", R.drawable.man,
                new Member("User",      R.drawable.man,     true),
                new Member("Sister",    R.drawable.woman,   false),
                new Member("Brother",   R.drawable.man,     false)
        ));

        groups.add(new Group("Friend Group 1", R.drawable.man,
                new Member("Friend 1",  R.drawable.man,     false),
                new Member("User",      R.drawable.man,     true),
                new Member("Friend 2",  R.drawable.woman,   false),
                new Member("Friend 3",  R.drawable.woman,   false),
                new Member("Friend 4",  R.drawable.man,     false)
        ));

        groups.add(new Group("Friend Group 2", R.drawable.man,
                new Member("Friend 1",  R.drawable.man,     false),
                new Member("Friend 2",  R.drawable.woman,   false),
                new Member("Friend 3",  R.drawable.woman,   false),
                new Member("Friend 4",  R.drawable.man,     false),
                new Member("User",      R.drawable.man,     true)
        ));
    }

    public static List<Group> getGroups() {
        return groups;
    }
}
