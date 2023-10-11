package com.potech.helpform.entity;

public class Coordinate {
    private int id;
    private double latitude;
    private double longitude;
    private int barId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getBarId() {
        return barId;
    }

    public void setBarId(int barId) {
        this.barId = barId;
    }

    public Coordinate(double latitude, double longitude, int barId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.barId = barId;
    }

    public Coordinate(int id, double latitude, double longitude, int barId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.barId = barId;
    }

    public String toStringWithoutId(){
        return "\"" + latitude+"\",\"" + longitude+"\",\"" + barId+"\"";
    }
}
