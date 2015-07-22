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


public class RegisterActivity extends ActionBarActivity {

//    public static String homeAddress = "Unit 2510 2 Torre Lorenzo Taft Ave. Malate Manila";
    //public static Editable homeAddress;
    //public final static String EXTRA_HOME_ADDRESS = "cyntwikip.homeaddress.text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_register);
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

    public void lookoutsScreen(View view) {

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.PREF_NAME), Context.MODE_PRIVATE);

        boolean login = sharedPref.getBoolean(getString(R.string.login), false);
        if(login==true) {
            Log.i("Cyntwikip", "Updating account details");
            //return to FireFeed
            finish();
            return;
        }
        else {
            Log.i("Cyntwikip", "Initializing account details");
        }

        Intent intent = new Intent(this, LookoutsActivity.class);
        startActivity(intent);
    }


}
