package com.turastory.jamquery.presentation.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tura on 2018-04-12.
 * <p>
 * 연/월/일까지만 해서 Date를 비교한다.
 */
class DateComparator {
    
    public static boolean isEqual(Date actual, Date expected) {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        
        c.setTime(actual);
        int actualYear = c.get(Calendar.YEAR);
        int actualMonth = c.get(Calendar.MONTH);
        int actualDate = c.get(Calendar.DATE);
        
        c.setTime(expected);
        int expectedYear = c.get(Calendar.YEAR);
        int expectedMonth = c.get(Calendar.MONTH);
        int expectedDate = c.get(Calendar.DATE);
        
        return actualYear == expectedYear &&
            actualMonth == expectedMonth &&
            actualDate == expectedDate;
    }
}
