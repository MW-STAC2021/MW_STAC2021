package com.stac2021.mwproject.CalendarDecorator;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.HashSet;

public class PregnantPossibleDecorator implements DayViewDecorator {
    private int color;
    private final CalendarDay day;
    int period, term;
    int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    public PregnantPossibleDecorator(int color, CalendarDay day, String period, String term) {
        this.color = color;
        this.day = day;
        this.period = Integer.parseInt(period);
        this.term = Integer.parseInt(term);
    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        int target = this.day.getDate().getDayOfMonth() + 16;
        if(target > month[this.day.getMonth()-1]) {
            if(this.day.getMonth() + 1 > 12) {
                return (this.day.getMonth() == day.getMonth() && day.getDate().getDayOfMonth() >= target - 4) || (day.getYear() == this.day.getYear() + 1 && day.getMonth() == (this.day.getMonth() + 1)%12 && day.getDate().getDayOfMonth() <= target - month[this.day.getMonth() - 1]
                        && day.getDate().getDayOfMonth() >= target - month[this.day.getMonth() - 1] - 4);
            }else {
                return (this.day.getMonth() == day.getMonth() && day.getDate().getDayOfMonth() >= target - 4) || (day.getMonth() == this.day.getMonth() + 1 && day.getDate().getDayOfMonth() <= target - month[this.day.getMonth() - 1]
                        && day.getDate().getDayOfMonth() >= target - month[this.day.getMonth() - 1] - 4);
            }
        }
        else {
            return this.day.getMonth() == day.getMonth() && day.getDate().getDayOfMonth() <= target && day.getDate().getDayOfMonth() >= target -4;
        }
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(8, color));
    }
}
