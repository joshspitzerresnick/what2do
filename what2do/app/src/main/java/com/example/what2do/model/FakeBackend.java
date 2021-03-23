package com.example.what2do.model;

import com.example.what2do.R;

import java.util.ArrayList;
import java.util.List;

public class FakeBackend {
    private static List<Group> groups;
    private static List<Match> matches; //holds top 3
    private static List<ItemModel> genres;
    private static List<ItemModel> activities;

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

        genres = new ArrayList<>();
        genres.add(new ItemModel(R.drawable.fiveguys, "Sports", "", 0f));
        genres.add(new ItemModel(R.drawable.mcdonalds2, "Movies", "", 0f));
        genres.add(new ItemModel(R.drawable.pizzahut, "Eating Out", "", 0f));
        genres.add(new ItemModel(R.drawable.subway, "Genre 4", "", 0f));
        genres.add(new ItemModel(R.drawable.tacobell, "Genre 5", "", 0f));

        activities = new ArrayList<>();
        activities.add(new ItemModel(R.drawable.fiveguys, "Five Guys", "1 mi, $", 4.2f));
        activities.add(new ItemModel(R.drawable.mcdonalds2, "McDonalds", "3.7 mi, $", 5.0f));
        activities.add(new ItemModel(R.drawable.pizzahut, "Pizza Hut", "2 mi, $", 4.7f));
        activities.add(new ItemModel(R.drawable.subway, "Subway", "3.9 mi, $", 3.9f));
        activities.add(new ItemModel(R.drawable.tacobell, "Taco Bell", "6.7 mi, $", 3.2f));

    }

    public static List<Group> getGroups() {
        return groups;
    }

    public static List<ItemModel> getActivities() {
        return activities;
    }

    public static List<ItemModel> getGenres() {
        return genres;
    }

    public static void addActivity(String activityName) {
        activities.add(new ItemModel(R.drawable.questionmark, activityName, "User Created Activity", 5f));
    }
}
