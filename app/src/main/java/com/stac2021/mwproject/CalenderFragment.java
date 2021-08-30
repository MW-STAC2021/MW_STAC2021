package com.stac2021.mwproject;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import static com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_RANGE;

public class CalenderFragment extends Fragment {
    MaterialCalendarView calendarView;
    Button btnWritePeriod;
    LinearLayout btnLinear;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        btnWritePeriod = view.findViewById(R.id.writePeriod);
        btnLinear = view.findViewById(R.id.btnLinear);

        btnWritePeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setSelectionMode(SELECTION_MODE_RANGE);
                btnWritePeriod.setBackgroundResource(R.drawable.button_completewriteperiod);
                btnWritePeriod.setText("완료");
                btnWritePeriod.setTextColor(Color.parseColor("#ffffff"));
                btnLinear.setGravity(Gravity.CENTER_HORIZONTAL);
                btnWritePeriod.setTypeface(null, Typeface.BOLD);
            }
        });
        return view;
    }
}