package com.cyntwikip.android.phirelert.model;

/**
 * Created by Cyntwikip on 7/27/2015.
 */
public class Contact2 {
    private int id;
    private String number;

    public Contact2() {}

    public Contact2(String number) {
        this.number = number;
    }
    public Contact2(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
