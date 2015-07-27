package com.cyntwikip.android.phirelert.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import com.cyntwikip.android.phirelert.model.Contact;

import java.util.ArrayList;

/**
 * Created by Miko on 7/24/2015.
 */
public class ContactManager {
    public static final String CONTACT_NAME = "CONTACT_NAME";
    public static final String CONTACT_NUMBERS = "CONTACT_NUMBERS";

    public static ArrayList<Contact> getContactsList(ContentResolver cr) {
        ArrayList<Contact> contactList = new ArrayList<>();

        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                Contact contact = new Contact(id, name);
                /*
                    Gets all numbers registered to the one user using their CONTACT_ID
                 */
                if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, ContactsContract.Contacts.STARRED);

                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact.addPhoneNumber(phoneNo);
                    }
                    pCur.close();

                }
                contactList.add(contact);
            }
        }
        return contactList;
    }

    /*
        Instatiate TelephonyManager:
                (TelephonyManager)getSystemService(TELEPHONY_SERVICE)
     */
    public static String getContactNumberFromSIM(TelephonyManager tm) {
        String number = tm.getLine1Number();
        return number;
    }

    public static void sendSMStoCellphone(String destNumber, String areaCode, String location) {
        SmsManager manager = SmsManager.getDefault();
        String text = "";
        text += "REPORT " + areaCode + " " + location;

        manager.sendTextMessage(destNumber, null, text, null, null);
    }
}
