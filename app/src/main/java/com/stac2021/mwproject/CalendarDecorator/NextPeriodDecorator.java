package com.stac2021.mwproject.CalendarDecorator;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.HashSet;
import java.util.List;

public class NextPeriodDecorator implements DayViewDecorator {
    private int color;
    int period, term;
    CalendarDay day;
    int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public NextPeriodDecorator(int color, CalendarDay day, String period, String term) {
        this.color = color;
        this.day = day;
        this.period = Integer.parseInt(period);
        this.term = Integer.parseInt(term);

    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        int target = this.day.getDate().getDayOfMonth() + period + term;
        if(target > month[this.day.getMonth()-1]) {
            if(this.day.getMonth() + 1 > 12) {
                return (this.day.getMonth() == day.getMonth() && day.getDate().getDayOfMonth() >= target - term)|| (day.getYear() == this.day.getYear() + 1 && day.getMonth() == (this.day.getMonth() + 1)%12 && day.getDate().getDayOfMonth() <= target - month[this.day.getMonth()-1]
                        && day.getDate().getDayOfMonth() >= target - month[this.day.getMonth()-1] - term);
            }
            else return (this.day.getMonth() == day.getMonth() && day.getDate().getDayOfMonth() >= target - term)|| (day.getMonth() == this.day.getMonth() + 1 && day.getDate().getDayOfMonth() <= target - month[this.day.getMonth()-1]
                    && day.getDate().getDayOfMonth() >= target - month[this.day.getMonth()-1] - term);
        }
        else {
            return this.day.getMonth() == day.getMonth() && day.getDate().getDayOfMonth() <= target && day.getDate().getDayOfMonth() >= target - term;
        }
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(8, color));
    }
}
