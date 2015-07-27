package com.cyntwikip.android.phirelert.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyntwikip on 7/27/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Preferences";

    // Contacts table name
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String TABLE_LOCATIONS = "locations";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_HOME_ADDRESS = "home_address";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_LOCATIONS = "saved_locations";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_HOME_ADDRESS + " TEXT" + ")";
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PH_NO + " TEXT" + ")";
        String CREATE_LOCATIONS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOCATIONS + " TEXT" + ")";

        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_LOCATIONS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // Adding new account
    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, account.getName()); // Account Name
        values.put(KEY_PH_NO, account.getNumber()); // Account Mobile Number
        values.put(KEY_HOME_ADDRESS, account.getAddress()); // Account Address

        // Inserting Row
        db.insert(TABLE_ACCOUNTS, null, values);
        db.close(); // Closing database connection
    }

    // Adding new contact
    public void addContact(Contact2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PH_NO, contact.getNumber()); // Contact Mobile Number

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Adding new location
    public void addLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOCATIONS, location.getLocation()); // Location

        // Inserting Row
        db.insert(TABLE_LOCATIONS, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Accounts
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<Account>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ACCOUNTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Account account = new Account();
                account.setId(Integer.parseInt(cursor.getString(0)));
                account.setName(cursor.getString(1));
                account.setNumber(cursor.getString(2));
                account.setAddress(cursor.getString(3));
                // Adding contact to list
                accountList.add(account);
            } while (cursor.moveToNext());
        }
        // return contact list
        return accountList;
    }

    // Getting All Contacts
    public List<Contact2> getAllContacts() {
        List<Contact2> contactList = new ArrayList<Contact2>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact2 contact = new Contact2();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setNumber(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }

    // Getting All Locations
    public List<Location> getAllLocations() {
        List<Location> locList = new ArrayList<Location>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Location loc = new Location();
                loc.setId(Integer.parseInt(cursor.getString(0)));
                loc.setLocation(cursor.getString(1));
                // Adding contact to list
                locList.add(loc);
            } while (cursor.moveToNext());
        }
        // return contact list
        return locList;
    }

    // Updating single account
    public int updateAccount(Account acc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, acc.getName());
        values.put(KEY_PH_NO, acc.getNumber());
        values.put(KEY_HOME_ADDRESS, acc.getAddress());

        // updating row
        return db.update(TABLE_ACCOUNTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(acc.getId()) });
    }

    // Updating single contact
    public int updateContact(Contact2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PH_NO, contact.getNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    // Updating single location
    public int updateLocation(Location loc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOCATIONS, loc.getLocation());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(loc.getId()) });
    }

    // Deleting single account
    public void deleteAccount(Account acc) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNTS, KEY_ID + " = ?",
                new String[]{String.valueOf(acc.getId())});
        db.close();
    }

    // Deleting single contact
    public void deleteContact(Contact2 contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    // Deleting single location
    public void deleteLocation(Location loc) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATIONS, KEY_ID + " = ?",
                new String[]{String.valueOf(loc.getId())});
        db.close();
    }

    // Deleting all contacts
    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_CONTACTS);
        db.execSQL("vacuum");
        db.close();
    }

    // Deleting all locations
    public void deleteAllLocations() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_LOCATIONS);
        db.execSQL("vacuum");
        db.close();
    }

}
