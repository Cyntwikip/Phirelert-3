package com.cyntwikip.android.phirelert;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cyntwikip.android.phirelert.model.Contact;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Cyntwikip on 7/26/2015.
 */
public class ContactListAdapter extends ArrayAdapter<Contact>{
    private Context context;
    private LayoutInflater inflater;
    private List<Contact> contacts;
    private final boolean[] buttonChecked;

    public ContactListAdapter(Context context, int textViewResourceId, List<Contact> contacts) {
        super(context, textViewResourceId, contacts);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contacts = contacts;
        buttonChecked = new boolean[contacts.size()];
        Arrays.fill(buttonChecked, Boolean.FALSE);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.contact_list_item, parent,false);
        }

        TextView c1 = (TextView)convertView.findViewById(R.id.contact_item_name);
        TextView c2 = (TextView)convertView.findViewById(R.id.contact_item_number);
        final CheckBox c3 = (CheckBox)convertView.findViewById(R.id.contact_check_box);

        Contact c = contacts.get(position);
        String name = c.getDisplayName();
        String number = (String) c.getPhoneNumbers().get(0);

        c1.setText(name);
        c2.setText(number);
        //this.buttonChecked[position] = false;

        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonChecked[position] = isChecked;
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonChecked[position]==false)
                    buttonChecked[position] = true;
                else
                    buttonChecked[position] = false;
                c3.setChecked(buttonChecked[position]);
            }
        });

        c3.setChecked(buttonChecked[position]);

        return convertView;
    }

}
