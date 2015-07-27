package com.cyntwikip.android.phirelert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.cyntwikip.android.phirelert.model.Account;
import com.cyntwikip.android.phirelert.model.DatabaseHandler;
import com.cyntwikip.android.phirelert.utils.ContactManager;

import java.util.List;


public class RegisterActivity extends ActionBarActivity {

//    public static String homeAddress = "Unit 2510 2 Torre Lorenzo Taft Ave. Malate Manila";
    //public static Editable homeAddress;
    //public final static String EXTRA_HOME_ADDRESS = "cyntwikip.homeaddress.text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_register);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.PREF_NAME), Context.MODE_PRIVATE);
        boolean login = sharedPref.getBoolean(getString(R.string.login), false);

        if(login==true) {
            DatabaseHandler db = new DatabaseHandler(this);
            EditText name = (EditText)findViewById(R.id.register_name);
            EditText number = (EditText)findViewById(R.id.register_mobile_number);
            EditText address = (EditText)findViewById(R.id.activity_register_home_address);

            List<Account> accounts = db.getAllAccounts();
            try {
                Account acc = accounts.get(0);
                name.setText(acc.getName());
                number.setText(acc.getNumber());
                address.setText(acc.getAddress());
            } catch(Exception e) {}
        }

        else {
            //set mobile number
            TelephonyManager tm = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            String number = ContactManager.getContactNumberFromSIM(tm);
            EditText num = (EditText) findViewById(R.id.register_mobile_number);
            //num.setText("heyy");
            num.setText(number);
        }
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
        DatabaseHandler db = new DatabaseHandler(this);

        boolean login = sharedPref.getBoolean(getString(R.string.login), false);
        if(login==true) {
            Log.i("Cyntwikip", "Updating account details");

            EditText name = (EditText)findViewById(R.id.register_name);
            EditText number = (EditText)findViewById(R.id.register_mobile_number);
            EditText address = (EditText)findViewById(R.id.activity_register_home_address);

            List<Account> accounts = db.getAllAccounts();
            try {
                Account acc = accounts.get(0);
                acc.setName(name.getText().toString());
                acc.setNumber(number.getText().toString());
                acc.setAddress(address.getText().toString());

                db.updateAccount(acc);

            } catch (Exception e) {}

            //return to FireFeed
            finish();
            return;
        }
        else {
            Log.i("Cyntwikip", "Initializing account details");

            EditText name = (EditText)findViewById(R.id.register_name);
            EditText number = (EditText)findViewById(R.id.register_mobile_number);
            EditText address = (EditText)findViewById(R.id.activity_register_home_address);
            db.addAccount(new Account(name.getText().toString(), number.getText().toString(), address.getText().toString()));
        }

        Intent intent = new Intent(this, LookoutsActivity.class);
        startActivity(intent);
    }


}
