package com.cyntwikip.android.phirelert;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.cyntwikip.android.phirelert.model.Contact;
import com.cyntwikip.android.phirelert.utils.ContactManager;

import java.util.ArrayList;

/**
 * Created by Cyntwikip on 7/22/2015.
 */
public class ContactsActivity extends ActionBarActivity{

    private String TAG = "Cyntwikip-Contacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_contacts);

        populateContacts();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateContacts() {
        ContentResolver cr = this.getContentResolver();
        ArrayList<Contact> contactsRaw = ContactManager.getContactsList(cr);
        ArrayList<Contact> contactsModified = new ArrayList<>();

        int k=0;
        for(int i=0; i<contactsRaw.size(); i++) {
            //if (string.trim().length() > 0)
            String number = "";
            try {
                number = (String) contactsRaw.get(i).getPhoneNumbers().get(0);
            } catch(Exception e) {};

            if(number.trim().length() > 0) {
            contactsModified.add(new Contact(Integer.toString(k), contactsRaw.get(i).getDisplayName(), number));
            k++;
            }
        }

        //print in log
//        for(int i=0; i<contactsModified.size(); i++) {
//            String name = contactsModified.get(i).getDisplayName();
//            String number = (String)contactsModified.get(i).getPhoneNumbers().get(0);
//            Log.i(TAG, "Name: " + name + " || " + "Number: " + number);
//        }

        ListView contact_listview = (ListView) findViewById(R.id.contact_listview);
        ContactListAdapter adapter = new ContactListAdapter(getApplicationContext(), R.layout.contact_list_item, contactsModified);
        contact_listview.setAdapter(adapter);

//        for(int i=0; i<contactsRaw.size(); i++) {
//            Log.i(TAG, "Name: " + contactsRaw.get(i).getDisplayName());
//            ArrayList<String> numbers = contactsRaw.get(i).getPhoneNumbers();
//            for(int j=0; j<numbers.size(); j++) {
//                Log.i(TAG, "Number: " + numbers.get(j));
//            }
//        }
    }

    public void firefeedScreen(View view) {
//        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.PREF_NAME), Context.MODE_PRIVATE);

        boolean login = sharedPref.getBoolean(getString(R.string.login), false);
        if(login==true) {
            Log.i(TAG, "Updating contacts details");
            //return to FireFeed
            finish();
            return;
        }
        else {
            Log.i(TAG, "Initializing contacts details");
        }

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.putBoolean(getString(R.string.login), true);
        editor.commit();

        Intent intent = new Intent(this, FireFeedActivity.class);
        //clears stack of activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void endContacts(View view) {
        finish();
    }
}
