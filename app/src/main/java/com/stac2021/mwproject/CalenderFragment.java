package com.stac2021.mwproject;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.stac2021.mwproject.CalendarDecorator.NextPeriodDecorator;
import com.stac2021.mwproject.CalendarDecorator.OvulationDecorator;
import com.stac2021.mwproject.CalendarDecorator.PeriodDecorator;
import com.stac2021.mwproject.CalendarDecorator.PregnantPossibleDecorator;
import androidx.appcompat.app.AppCompatActivity;


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
    ImageView desciption;
    androidx.appcompat.widget.Toolbar tb;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        tb = view.findViewById(R.id.toolbar) ;
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);

        calendarView = view.findViewById(R.id.calendarView);
        btnWritePeriod = view.findViewById(R.id.writePeriod);
        btnWritePeriodComplete = view.findViewById(R.id.writePeriodComplete);
        desciption = view.findViewById(R.id.calendar_description);
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
                desciption.setVisibility(View.INVISIBLE);

            }
        });

        btnWritePeriodComplete.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                btnWritePeriodComplete.setVisibility(View.GONE);
                btnWritePeriod.setVisibility(View.VISIBLE);
                final List<CalendarDay> dates = calendarView.getSelectedDates();
                if(dates.size() > 0) {
                    calendarView.addDecorator(new PeriodDecorator(Color.parseColor("#FB6D37"), dates));
                    // CalendarDay firstDay = dates.get(0);
                    // Toast.makeText(getContext(), ""+dates.get(0).getMonth(), Toast.LENGTH_SHORT).show();
                    calendarView.addDecorator(new PregnantPossibleDecorator(Color.parseColor("#98D1FF"), dates.get(0), period, term));
                    calendarView.addDecorator(new OvulationDecorator(Color.parseColor("#007BDF"), dates.get(0)));
                    calendarView.addDecorator(new NextPeriodDecorator(Color.parseColor("#FFC2AB"), dates.get(0), period, term));
                }
                calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
                // Toast.makeText(getContext(), period + term +" " + dates.get(0).getMonth(), Toast.LENGTH_SHORT).show();
                desciption.setVisibility(View.VISIBLE);

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_calender, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                // Toast.makeText(getContext(), "설정 넘어가기", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getContext(), PeriodSetting.class);
                startActivity(in);

        }
        return false;
    }
}