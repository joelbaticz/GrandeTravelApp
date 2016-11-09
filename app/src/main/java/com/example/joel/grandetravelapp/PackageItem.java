package com.example.joel.grandetravelapp;

public class PackageItem {

    private int id;
    private String name;
    private String thumbnailUrl;
    private String location;
    private String description;
    private double price;
    private boolean isActive;
    private int providerId;
    private double rating;
    private int numberOfFeedbacks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberOfFeedbacks() {
        return numberOfFeedbacks;
    }

    public void setNumberOfFeedbacks(int numberOfFeedbacks) {
        this.numberOfFeedbacks = numberOfFeedbacks;
    }

    public PackageItem(int id, String name, String thumbnailUrl, String location, String description, double price, boolean isActive, int providerId, double rating, int numberOfFeedbacks)
    {
        this.id=id;
        this.name=name;
        this.thumbnailUrl=thumbnailUrl;
        this.location=location;
        this.description=description;
        this.price=price;
        this.isActive=isActive;
        this.providerId=providerId;
        this.rating=rating;
        this.numberOfFeedbacks=numberOfFeedbacks;
    }
}