package com.khahani.app.quakeeventlist;

/**
 * Created by dev on 7/24/2018.
 */

public class Event {
    private double mag;
    private int felt;
    private String siteUrl;


    public Event(double mag, int felt, String siteUrl) {

        this.mag = mag;
        this.felt = felt;
        this.siteUrl = siteUrl;
    }

    public double getMag() {
        return mag;
    }

    public int getFelt() {
        return felt;
    }

    public String getSiteUrl() {
        return siteUrl;
    }
}
