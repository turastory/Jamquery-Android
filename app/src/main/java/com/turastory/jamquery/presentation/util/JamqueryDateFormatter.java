package com.turastory.jamquery.presentation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Date format util class
 */
public class JamqueryDateFormatter {
    
    private static SimpleDateFormat jamqueryDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    
    public static String format(Date date) {
        return jamqueryDateFormat.format(date);
    }
    
    public static Date parse(String dateString) {
        try {
            return jamqueryDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
