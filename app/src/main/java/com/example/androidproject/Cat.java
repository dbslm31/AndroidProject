package com.example.androidproject;

import java.io.Serializable;

public class Cat implements Serializable {

    private int id;
    private String imageId;
    private String url;
    private  String name;
    private String origin;
    private int adaptability;
    private int affectionLevel;
    private int childFriendly;
    private int energyLevel;
    private int intelligence;
    private int socialNeeds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getAdaptability() {
        return adaptability;
    }

    public void setAdaptability(int adaptability) {
        this.adaptability = adaptability;
    }

    public int getAffection_level() {
        return affectionLevel;
    }

    public void setAffectionLevel(int affection_level) {
        this.affectionLevel = affection_level;
    }

    public int getChildFriendly() {
        return childFriendly;
    }

    public void setChildFriendly(int childFriendly) {
        this.childFriendly = childFriendly;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getSocialNeeds() {
        return socialNeeds;
    }

    public void setSocialNeeds(int social_needs) {
        this.socialNeeds = socialNeeds;
    }

}
