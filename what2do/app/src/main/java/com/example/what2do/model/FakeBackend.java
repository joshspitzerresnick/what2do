package com.example.what2do.model;

import com.example.what2do.R;

import java.util.ArrayList;
import java.util.List;

public class FakeBackend {
    private static List<Group> groups;

    private static List<Match> restaurantMatches;
    private static Match match;

    private static List<ItemModel> genres;
    private static List<List<ItemModel>> activities;
    private static int genreChosen = -1;


    public static void init() {
        if (groups != null) {
            return;
        }

        groups = new ArrayList<>();

        groups.add(new Group("Family Group", R.drawable.man,
                new Member("Dad", R.drawable.man, false),
                new Member("Mom", R.drawable.woman, false),
                new Member("User", R.drawable.man, true),
                new Member("Sister", R.drawable.woman, false),
                new Member("Brother", R.drawable.man, false)
        ));

        groups.add(new Group("Sibling Group", R.drawable.man,
                new Member("User", R.drawable.man, true),
                new Member("Sister", R.drawable.woman, false),
                new Member("Brother", R.drawable.man, false)
        ));

        groups.add(new Group("Friend Group 1", R.drawable.man,
                new Member("Friend 1", R.drawable.man, false),
                new Member("User", R.drawable.man, true),
                new Member("Friend 2", R.drawable.woman, false),
                new Member("Friend 3", R.drawable.woman, false),
                new Member("Friend 4", R.drawable.man, false)
        ));

        groups.add(new Group("Friend Group 2", R.drawable.man,
                new Member("Friend 1", R.drawable.man, false),
                new Member("Friend 2", R.drawable.woman, false),
                new Member("Friend 3", R.drawable.woman, false),
                new Member("Friend 4", R.drawable.man, false),
                new Member("User", R.drawable.man, true)
        ));


        restaurantMatches = new ArrayList<>();
        restaurantMatches.add(
                new Match(
                        1,
                        "Applebees",
                        "American Food",
                        "Rating: 4.1/5.0",
                        "98% match",
                        R.drawable.applebees,
                        "8312 MN-7",
                        "St. Louis Park, MN 55426",
                        "(952) 933-6701",
                        "www.applebees.com"));

        restaurantMatches.add(
                new Match(
                        2,
                        "Olive Garden",
                        "Italian Food",
                        "Rating: 4.3/5.0",
                        "84% match",
                        R.drawable.olivegarden,
                        "1525 County Road C West",
                        "Roseville, MN 55113",
                        "(651) 638-9557",
                        "www.olivegarden.com"));
        restaurantMatches.add(
                new Match(
                        3,
                        "McDonalds",
                        "Fast Food",
                        "Rating: 3.2/5.0",
                        "62% match",
                        R.drawable.mcdonalds,
                        "4565 Erin Ln.",
                        "Eagan, MN 55122",
                        "(651) 452-3179",
                        "www.mcdonalds.com"));

        genres = new ArrayList<>();
        genres.add(new ItemModel(R.drawable.fiveguys, "Sports", "", 0f));
        genres.add(new ItemModel(R.drawable.mcdonalds2, "Movies", "", 0f));
        genres.add(new ItemModel(R.drawable.pizzahut, "Eating Out", "", 0f));
        genres.add(new ItemModel(R.drawable.subway, "Genre 4", "", 0f));
        genres.add(new ItemModel(R.drawable.tacobell, "Genre 5", "", 0f));

        activities = new ArrayList<>();

        List<ItemModel> sports = new ArrayList<>();
        sports.add(new ItemModel(R.drawable.fiveguys, "Soccer", "", 3.7f));

        List<ItemModel> movies = new ArrayList<>();
        movies.add(new ItemModel(R.drawable.fiveguys, "The Godfather", "", 5.0f));

        List<ItemModel> eating_out = new ArrayList<>();
        eating_out.add(new ItemModel(R.drawable.fiveguys, "Five Guys", "1 mi, $", 4.2f));
        eating_out.add(new ItemModel(R.drawable.mcdonalds2, "McDonalds", "3.7 mi, $", 5.0f));
        eating_out.add(new ItemModel(R.drawable.pizzahut, "Pizza Hut", "2 mi, $", 4.7f));
        eating_out.add(new ItemModel(R.drawable.subway, "Subway", "3.9 mi, $", 3.9f));
        eating_out.add(new ItemModel(R.drawable.tacobell, "Taco Bell", "6.7 mi, $", 3.2f));

        // Add the other genres here and add them to the activities list

        activities.add(sports);
        activities.add(movies);
        activities.add(eating_out);


    }

    public static List<Group> getGroups() {
        return groups;
    }

    public static List<Match> getMatches() {
        return restaurantMatches;
    }

    public static Match getMatch() {
        return match;
    }

    public static void setMatch(Match matchIn) {
        match = matchIn;
    }

    public static List<List<ItemModel>> getActivities() {
        return activities;
    }

    public static List<ItemModel> getGenres() {
        return genres;
    }

    public static int getGenreChosen() {
        return genreChosen;
    }

    public static void setGenreChosen(int genre) {
        genreChosen = genre;
    }

    public static void addActivity(String activityName) {
        activities.get(genreChosen).add(new ItemModel(R.drawable.questionmark, activityName, "User Created Activity", 5f));

    }
}
