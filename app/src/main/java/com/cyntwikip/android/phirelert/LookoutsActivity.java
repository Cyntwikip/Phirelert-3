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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyntwikip on 7/22/2015.
 */
public class LookoutsActivity extends ActionBarActivity {

    private String[] locations = {"Metro Manila", "Cebu", "Davao"};

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
        List<String> list = new ArrayList<>();
        for(int i=0; i<locations.length; i++) {
            list.add(locations[i]);
        }
        ListView lookouts_listview = (ListView) findViewById(R.id.lookouts_listview);
        LookoutsListAdapter adapter = new LookoutsListAdapter(getApplicationContext(), R.layout.lookouts_list_item, list);
        lookouts_listview.setAdapter(adapter);
    }

    public void contactsScreen(View view) {

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.PREF_NAME), Context.MODE_PRIVATE);

        boolean login = sharedPref.getBoolean(getString(R.string.login), false);
        if(login==true) {
            Log.i("Cyntwikip", "Updating lookouts details");
            //return to FireFeed
            finish();
            return;
        }
        else {
            Log.i("Cyntwikip", "Initializing lookouts details");
        }

        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    public void endLookouts(View view) {
        finish();
    }
}
