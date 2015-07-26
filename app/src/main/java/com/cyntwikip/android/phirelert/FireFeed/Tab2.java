package com.cyntwikip.android.phirelert.FireFeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cyntwikip.android.phirelert.R;
import com.cyntwikip.android.phirelert.model.Report;
import com.cyntwikip.android.phirelert.model.ReportStatus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Cyntwikip on 6/27/2015.
 */
public class Tab2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View v =inflater.inflate(R.layout.firefeed_list,container,false);
        //return v;
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.firefeed_list,container,false);
        ListView listView = (ListView) layout.findViewById(R.id.firefeed_list);

        List<Report> list = new ArrayList<Report>();

        Date today = Calendar.getInstance().getTime();

        list.add(new Report(ReportStatus.FIRE_OUT,"Makati", today));
        list.add(new Report(ReportStatus.ELECTRICAL_FIRE, "Manila", today));
        list.add(new Report(ReportStatus.FOR_VERIFICATION, "Laguna", today));
        list.add(new Report(ReportStatus.FIRE_OUT, "Pampanga", today));
        list.add(new Report(ReportStatus.FIRE_OUT, "Quezon", today));
        list.add(new Report(ReportStatus.RUBBISH_FIRE, "Bulacan", today));
        list.add(new Report(ReportStatus.FOR_VERIFICATION, "Manila", today));
        list.add(new Report(ReportStatus.FIRE_UNDER_CONTROL, "Taguig", today));
        list.add(new Report(ReportStatus.FOR_VERIFICATION,"Quezon", today));
        list.add(new Report(ReportStatus.FIRE_UNDER_CONTROL, "Manila", today));
        list.add(new Report(ReportStatus.FIRE_OUT,"Makati", today));
        list.add(new Report(ReportStatus.ELECTRICAL_FIRE, "Manila", today));
        list.add(new Report(ReportStatus.FOR_VERIFICATION, "Laguna", today));
        list.add(new Report(ReportStatus.FIRE_OUT, "Pampanga", today));
        list.add(new Report(ReportStatus.FIRE_OUT, "Quezon", today));

        final FireFeedListAdapter adapter = new FireFeedListAdapter(getActivity().getApplicationContext(), list);
        //ListView listView = (ListView)layout.findViewById(R.id.);
        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                String itemPicked = "Fire in " + adapter.getItem(position).getLocation() + "!!";
//                Toast.makeText(getActivity(), itemPicked, Toast.LENGTH_SHORT).show();
//            }
//        });

        return layout;
    }
}