package com.cyntwikip.android.phirelert.model;

/**
 * Created by Cyntwikip on 7/27/2015.
 */
public class Account {
    private int id;
    private String name;
    private String number;
    private String address;

    public Account() {}

    public Account(String name, String number, String address) {
        this.name = name;
        this.number = number;
        this.address= address;
    }

    public Account(int id, String name, String number, String address) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.address= address;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
