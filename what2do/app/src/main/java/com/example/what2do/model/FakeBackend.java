package com.example.what2do.model;

import android.os.CountDownTimer;
import android.util.Log;

import com.example.what2do.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeBackend {
    private static List<Group> groups;

    private static List<Match> restaurantMatches;
    private static Match match;

    private static List<ItemModel> genres;
    private static List<List<ItemModel>> activities;
    private static int genreChosen = -1;

    private static MemberStateListener memberStateListener;
    private static Map<Member, CountDownTimer> memberSimulatorTimers;


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
        genres.add(new ItemModel(R.drawable.eat, "Eating Out", "", 0f));
        genres.add(new ItemModel(R.drawable.movie, "Movies", "", 0f));
        genres.add(new ItemModel(R.drawable.indoors, "Indoors", "", 0f));
        genres.add(new ItemModel(R.drawable.outdoors_active, "Outdoors - Active", "", 0f));
        genres.add(new ItemModel(R.drawable.outdoors_casual, "Outdoors - Casual", "", 0f));


        activities = new ArrayList<>();

        List<ItemModel> eating_out = new ArrayList<>();
        eating_out.add(new ItemModel(R.drawable.fiveguys, "Five Guys", "1 mi, $", 4.2f));
        eating_out.add(new ItemModel(R.drawable.mcdonalds2, "McDonalds", "3.7 mi, $", 5.0f));
        eating_out.add(new ItemModel(R.drawable.pizzahut, "Pizza Hut", "2 mi, $", 4.7f));
        eating_out.add(new ItemModel(R.drawable.subway, "Subway", "3.9 mi, $", 3.9f));
        eating_out.add(new ItemModel(R.drawable.tacobell, "Taco Bell", "6.7 mi, $", 3.2f));

        List<ItemModel> movies = new ArrayList<>();
        movies.add(new ItemModel(R.drawable.nomadland, "Nomadland", "R", 4.7f));

        List<ItemModel> indoors = new ArrayList<>();
        indoors.add(new ItemModel(R.drawable.escape, "Escape Room", "", 0f));
        indoors.add(new ItemModel(R.drawable.bowling, "Bowling", "", 0f));
        indoors.add(new ItemModel(R.drawable.museum, "Art Museum", "", 0f));
        indoors.add(new ItemModel(R.drawable.laser, "Laser Tag", "", 0f));

        List<ItemModel> outdoors_active = new ArrayList<>();
        outdoors_active.add(new ItemModel(R.drawable.bike, "Biking", "", 0f));
        outdoors_active.add(new ItemModel(R.drawable.kayak, "Kayaking", "", 0f));
        outdoors_active.add(new ItemModel(R.drawable.hiking, "Hiking", "", 0f));
        outdoors_active.add(new ItemModel(R.drawable.fish, "Fishing", "", 0f));


        List<ItemModel> outdoors_casual = new ArrayList<>();
        outdoors_casual.add(new ItemModel(R.drawable.picnic, "Picnic", "", 0f));
        outdoors_casual.add(new ItemModel(R.drawable.beach, "Beach", "", 0f));


        activities.add(eating_out);
        activities.add(movies);
        activities.add(indoors);
        activities.add(outdoors_active);
        activities.add(outdoors_casual);


        memberSimulatorTimers = new HashMap<>();
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

    public static void setMemberStateListener(MemberStateListener memberStateListener) {
        FakeBackend.memberStateListener = memberStateListener;
    }

    public static void changeMemberState(GroupState groupState, Member member, MemberState memberState) {
        memberSimulatorTimers.remove(member);

        memberStateListener.memberStateChanged(groupState, member, memberState);


        if(member.isUser() && memberState == MemberState.COMPLETED) {
            // Simulate other group members changing states with delay
            Group group = memberStateListener.getCurrentGroup();

            for(Member m: group.getGroupMembers()) {
                if(m != member) {
                    long delay = Math.max(0, (long)(Math.pow(Math.random(), 2.0) * 5000) - 300);
                    Log.d("AA", "changing member " + group.getGroupMembers().indexOf(m) + " state to " + MemberState.COMPLETED + " after " + delay + " millis");
                    changeMemberStateDelayed(groupState, m, MemberState.COMPLETED, delay);
                }
            }
        }
    }

    private static void changeMemberStateDelayed(GroupState groupState, Member member, MemberState memberState, long delay) {
        if(memberSimulatorTimers.containsKey(member)) {
            memberSimulatorTimers.get(member).cancel();
        }
        memberSimulatorTimers.put(member, new CountDownTimer(delay, delay) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                FakeBackend.changeMemberState(groupState, member, memberState);
            }
        });
        memberSimulatorTimers.get(member).start();
    }
}
