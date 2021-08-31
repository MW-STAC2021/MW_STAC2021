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
    Button btnWritePeriod, btnWritePeriodComplete;
    LinearLayout btnLinear;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        btnWritePeriod = view.findViewById(R.id.writePeriod);
        btnWritePeriodComplete = view.findViewById(R.id.writePeriodComplete);

        btnWritePeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setSelectionMode(SELECTION_MODE_RANGE);
                btnWritePeriod.setVisibility(View.GONE);
                btnWritePeriodComplete.setVisibility(View.VISIBLE);
            }
        });

        btnWritePeriodComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnWritePeriodComplete.setVisibility(View.GONE);
                btnWritePeriod.setVisibility(View.VISIBLE);
                calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
            }
        });
        return view;
    }
}