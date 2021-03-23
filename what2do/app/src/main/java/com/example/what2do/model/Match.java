package com.example.what2do.model;

/**
 * Created by karanjaswani on 1/12/18.
 */

public class Match {
    private int id;
    private String title;
    private String shortdesc;
    private String rating;
    private String percentage;
    private int image;
    private String location1;
    private String location2;
    private String phoneNum;
    private String website;

    public Match(int id, String title, String shortdesc, String rating, String percentage, int image, String location1, String location2, String phoneNum, String website) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.percentage = percentage;
        this.image = image;
        this.location1 = location1;
        this.location2 = location2;
        this.phoneNum = phoneNum;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getRating() {
        return rating;
    }

    public String getPercentage() { return percentage; }

    public int getImage() {
        return image;
    }

    public String getLocation1() { return location1; }

    public String getLocation2() { return location2; }

    public String getPhoneNum() { return phoneNum; }

    public String getWebsite() { return website; }
}
