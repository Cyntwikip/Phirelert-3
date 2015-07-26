package com.cyntwikip.android.phirelert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Cyntwikip on 7/26/2015.
 */
public class LookoutsListAdapter extends ArrayAdapter<String>{
    private Context context;
    private LayoutInflater inflater;
    private List<String> locations;
    private final boolean[] buttonChecked;

    public LookoutsListAdapter(Context context, int textViewResourceId, List<String> locations) {
        super(context, textViewResourceId, locations);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.locations = locations;
        buttonChecked = new boolean[locations.size()];
        Arrays.fill(buttonChecked, Boolean.FALSE);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.lookouts_list_item, parent,false);
        }

        TextView c1 = (TextView)convertView.findViewById(R.id.lookouts_item_name);
        final CheckBox c2 = (CheckBox)convertView.findViewById(R.id.lookouts_checkbox);

        String name = locations.get(position);

        c1.setText(name);

        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                c2.setChecked(buttonChecked[position]);
            }
        });

        c2.setChecked(buttonChecked[position]);

        return convertView;
    }
}
