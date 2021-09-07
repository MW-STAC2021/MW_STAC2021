package com.stac2021.mwproject;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Decorator implements DayViewDecorator {
    private int color;
    private final HashSet<CalendarDay> dates = new HashSet<>();


    public Decorator(int color, List<CalendarDay> dates) {
        this.color = color;
        for(int i=0; i<dates.size(); i++) {
            this.dates.add(dates.get(i));
        }
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color));
    }
}
