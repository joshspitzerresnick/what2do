package com.example.what2do.model;

public class ItemModel {
    private int image;
    private String name, stats;
    private float rating;

    public ItemModel() {
    }

    public ItemModel(int image, String name, String stats, float rating) {
        this.image = image;
        this.name = name;
        this.stats = stats;
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getStats() {
        return stats;
    }

    public float getRating() {
        return rating;
    }
}