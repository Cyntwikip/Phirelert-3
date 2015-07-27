package com.cyntwikip.android.phirelert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cyntwikip.android.phirelert.model.Location;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Cyntwikip on 7/26/2015.
 */
public class LookoutsListAdapter extends ArrayAdapter<Location>{
    private Context context;
    private LayoutInflater inflater;
    private List<Location> locations;
    private final boolean[] buttonChecked;

    public LookoutsListAdapter(Context context, int textViewResourceId, List<Location> locations) {
        super(context, textViewResourceId, locations);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.locations = locations;
        buttonChecked = new boolean[locations.size()];
        Arrays.fill(buttonChecked, Boolean.FALSE);
        for(int i=0; i<locations.size(); i++) {
            if(locations.get(i).isChecked()) {
                buttonChecked[i] = true;
            }
        }
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.lookouts_list_item, parent,false);
        }

        TextView c1 = (TextView)convertView.findViewById(R.id.lookouts_item_name);
        final CheckBox c2 = (CheckBox)convertView.findViewById(R.id.lookouts_checkbox);

        String name = locations.get(position).getLocation();

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
                c2.setChecked(!buttonChecked[position]);
            }
        });

        c2.setChecked(buttonChecked[position]);

        return convertView;
    }

    public boolean[] getItemsChecked() {
        return buttonChecked;
    }
}
