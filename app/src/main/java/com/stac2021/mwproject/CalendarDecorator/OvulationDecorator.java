package com.stac2021.mwproject.CalendarDecorator;

import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.HashSet;
import java.util.List;

public class OvulationDecorator implements DayViewDecorator {
    private int color;
    private CalendarDay day;
    int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    public OvulationDecorator(int color, CalendarDay day) {
        this.color = color;
        this.day = day;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        int target = this.day.getDate().getDayOfMonth() + 14;
        if(target > month[this.day.getMonth()-1]) {
            if(this.day.getMonth() + 1 > 12) {
                return day.getYear() == this.day.getYear() + 1 && day.getMonth() == (this.day.getMonth() + 1)%12 && day.getDate().getDayOfMonth() == target - month[this.day.getMonth()-1];
            }
            else return day.getMonth() == this.day.getMonth() + 1 && day.getDate().getDayOfMonth() == target - month[this.day.getMonth()-1];
        }
        else return day.getMonth() == this.day.getMonth() && target == day.getDate().getDayOfMonth();
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(8, color));
    }
}
