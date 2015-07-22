package com.cyntwikip.android.phirelert.FireFeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cyntwikip.android.phirelert.R;
import com.cyntwikip.android.phirelert.model.Report;
import com.cyntwikip.android.phirelert.model.ReportStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Cyntwikip on 6/30/2015.
 */
public class FireFeedListAdapter extends BaseAdapter{
    private Context context;
    //private List<Character> list;
    private List<Report> list;
    private LayoutInflater inflater;

    public FireFeedListAdapter(Context context, List<Report> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Report getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Code Optimization
        // http://stackoverflow.com/questions/21063867/android-out-of-memory-bitmap
        if(convertView==null)
        {
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.firefeed_list_item, parent,false);
        }

        //View r1 = (View)convertView.findViewById(R.id.report_status_color);
        ImageView r2 = (ImageView)convertView.findViewById(R.id.report_image);
        TextView r3 = (TextView)convertView.findViewById(R.id.report_location);
        TextView r4 = (TextView)convertView.findViewById(R.id.report_status);
        TextView r5 = (TextView)convertView.findViewById(R.id.report_date);

        //Character c = list.get(position);
        Report r = list.get(position);
        String bg = "#000000";

        switch(r.getStatus()) {
            case FOR_VERIFICATION:
                r2.setImageResource(R.drawable.exclamation);
                r4.setText(ReportStatus.toString(r.getStatus()));
                break;
            case RUBBISH_FIRE:
                r2.setImageResource(R.drawable.fire);
                r4.setText(ReportStatus.toString(r.getStatus()));
                break;
            case ELECTRICAL_FIRE:
                r2.setImageResource(R.drawable.fire);
                r4.setText(ReportStatus.toString(r.getStatus()));
                break;
            case FIRE_UNDER_CONTROL:
                r2.setImageResource(R.drawable.water);
                r4.setText(ReportStatus.toString(r.getStatus()));
                break;
            case FIRE_OUT:
                r2.setImageResource(R.drawable.check);
                r4.setText(ReportStatus.toString(r.getStatus()));
                break;
        }

        r3.setText(r.getLocation());

        String months[] = {
                "Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec"};

        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        DateFormat df = new SimpleDateFormat("hh:mm aa | MMM d, yyyy");
        String strDate = df.format(r.getDate());
        //http://stackoverflow.com/questions/13581608/displaying-am-and-pm-in-small-letter-after-date-formatting
        strDate = strDate.replace("am", "AM").replace("pm","PM");
        r5.setText(strDate);

        return convertView;
    }
}
