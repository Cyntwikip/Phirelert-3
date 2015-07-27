package com.cyntwikip.android.phirelert.model;

/**
 * Created by Cyntwikip on 7/27/2015.
 */
public class Location {
    private int id;
    private String location;

    public Location() {}

    public Location(String location) {
        this.location = location;
    }

    public Location(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
