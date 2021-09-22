package com.stac2021.mwproject.CalendarDecorator;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.List;

public class UserPeriodDates {
    private List<List<CalendarDay>> userPeriodDates;

    public void addDates(List<CalendarDay> dates) {
        this.userPeriodDates.add(dates);
    }
    public List<List<CalendarDay>> getDates() {
        return this.userPeriodDates;
    }
}
