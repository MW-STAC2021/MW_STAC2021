package com.stac2021.mwproject;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.prolificinteractive.materialcalendarview.MaterialCalendarView.SELECTION_MODE_RANGE;

public class CalenderFragment extends Fragment {
    MaterialCalendarView calendarView;
    Button btnWritePeriod, btnWritePeriodComplete;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    EditText edtText1, edtText2;
    String period, term;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        btnWritePeriod = view.findViewById(R.id.writePeriod);
        btnWritePeriodComplete = view.findViewById(R.id.writePeriodComplete);
        sp = getActivity().getSharedPreferences("pref", 0);
        spe = sp.edit();
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);


        View dialogView = View.inflate(getContext(), R.layout.dialog_period, null);
        final AlertDialog dlg = new AlertDialog.Builder(getContext()).create();
        dlg.setView(dialogView);


        period = sp.getString("period", "");
        term = sp.getString("term", "");

        edtText1 = dialogView.findViewById(R.id.period_dialog1);
        edtText2 = dialogView.findViewById(R.id.period_dialog2);

        Button btnCancel, btnOk;
        btnCancel = dialogView.findViewById(R.id.btnCancel);
        btnOk = dialogView.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dlg_period = edtText1.getText().toString();
                String dlg_term = edtText2.getText().toString();
                spe.putString("period", dlg_period);
                spe.putString("term", dlg_term);
                spe.commit();

                dlg.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
            }
        });

        if(period.equals("") && term.equals("")) {
            dlg.show();
        }

        btnWritePeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setSelectionMode(SELECTION_MODE_RANGE);
                btnWritePeriod.setVisibility(View.GONE);
                btnWritePeriodComplete.setVisibility(View.VISIBLE);
            }
        });

        btnWritePeriodComplete.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                btnWritePeriodComplete.setVisibility(View.GONE);
                btnWritePeriod.setVisibility(View.VISIBLE);
                final List<CalendarDay> dates = calendarView.getSelectedDates();
                Decorator decorator = new Decorator(Color.RED, dates);
                calendarView.addDecorator(decorator);
                calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
            }
        });


        return view;
    }

}