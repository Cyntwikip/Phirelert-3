package com.cyntwikip.android.phirelert;

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

import com.cyntwikip.android.phirelert.model.DatabaseHandler;
import com.cyntwikip.android.phirelert.model.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyntwikip on 7/22/2015.
 */
public class LookoutsActivity extends ActionBarActivity {

    private String TAG = "Cyntwikip-Lookouts";
    private String[] locations = {"Metro Manila", "Cebu", "Davao"};
    private ListView lookouts_listview;
    private LookoutsListAdapter adapter;
    private List<Location> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_lookouts);

        populateLookouts();
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

    private void populateLookouts() {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Location> fromDB = db.getAllLocations();

        //for testing purposes
        for(int i=0; i<fromDB.size(); i++) {
            Log.i(TAG, fromDB.get(i).getLocation());
        }

        int k=0;
        for(int i=0; i<locations.length; i++) {
            list.add(new Location(locations[i]));
            for(int c=0; c<fromDB.size(); c++) {
                if(locations[i].equals(fromDB.get(c).getLocation())) {
                    list.get(k).setIsChecked(true);
                }
            }
            k++;
        }

        lookouts_listview = (ListView) findViewById(R.id.lookouts_listview);
        adapter = new LookoutsListAdapter(getApplicationContext(), R.layout.lookouts_list_item, list);
        lookouts_listview.setAdapter(adapter);
    }

    private List<Location> getCheckedItems() {
        boolean[] checked = adapter.getItemsChecked();
        List<Location> locations = new ArrayList<>();

        for(int i=0; i<list.size(); i++) {
            if(checked[i]) {
                Log.i(TAG, list.get(i).getLocation());
                Location loc = new Location(list.get(i).getLocation());
                locations.add(loc);
            }
        }
        return locations;
    }

    private void storeLocationstoDB() {
        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteAllLocations();
        List<Location> locations = getCheckedItems();
        for(int i=0; i<locations.size(); i++) {
            Log.i(TAG, "Added " + locations.get(i).getLocation() + " to database.");
            db.addLocation(locations.get(i));
        }
    }

    public void contactsScreen(View view) {

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.PREF_NAME), Context.MODE_PRIVATE);

        boolean login = sharedPref.getBoolean(getString(R.string.login), false);
        if(login==true) {
            Log.i("Cyntwikip", "Updating lookouts details");
            storeLocationstoDB();
            //return to FireFeed
            finish();
            return;
        }
        else {
            Log.i("Cyntwikip", "Initializing lookouts details");
            storeLocationstoDB();
        }

        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    public void endLookouts(View view) {
        finish();
    }
}
