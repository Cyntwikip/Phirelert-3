package com.cyntwikip.android.phirelert.model;

import java.util.ArrayList;

/**
 * Created by Miko on 7/24/2015.
 * Modified by Cyntwikip on 7/26/2015
 */
public class Contact {
    private ArrayList<String> phoneNumber;
    private String displayName;
    private String contactID;

    public Contact(String contactID, String displayName, String phoneNumber) {
        this.contactID = contactID;
        this.displayName = displayName;
        this.phoneNumber = new ArrayList<>();
        this.phoneNumber.add(phoneNumber);
    }

    public Contact(String contactID, String displayName) {
        this.contactID = contactID;
        this.displayName = displayName;
        this.phoneNumber = new ArrayList<>();
    }

    public void addPhoneNumber(String number) {
        phoneNumber.add(number);
    }

    public String getContactID() {
        return contactID;
    }

    public ArrayList getPhoneNumbers() {
        return phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }
}
