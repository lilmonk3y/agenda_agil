package com.devs.core.util;

import java.util.Calendar;

public class DateUtil {

    public Calendar getDate(){
        return Calendar.getInstance();
    }

    public static Calendar newDateAddingDays(Calendar calendar, int daysToAdd){
        Calendar newDay = (Calendar) calendar.clone();
        newDay.add(Calendar.DATE,daysToAdd);
        return newDay;

    }
}
