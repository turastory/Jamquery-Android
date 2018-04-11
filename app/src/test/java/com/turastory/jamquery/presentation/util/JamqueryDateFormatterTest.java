package com.turastory.jamquery.presentation.util;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by tura on 2018-04-12.
 */
public class JamqueryDateFormatterTest {
    
    @Test
    public void test_format() {
        String dateString = JamqueryDateFormatter.format(getMockDateForTestFormat());
        assertThat(dateString, is("20181225"));
    }
    
    private Date getMockDateForTestFormat() {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.set(2018, Calendar.DECEMBER, 25, 22, 55, 15);
        return c.getTime();
    }
    
    @Test
    public void test_parseSuccess() {
        Date date = JamqueryDateFormatter.parse("20170516");
        assertTrue(DateComparator.isEqual(date, getMockDateForTestParse()));
    }
    
    @Test
    public void test_parseIncorrect() {
        Date date = JamqueryDateFormatter.parse("2017-05-16");
        assertFalse(DateComparator.isEqual(date, getMockDateForTestParse()));
    }
    
    private Date getMockDateForTestParse() {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.set(Calendar.YEAR, 2017);
        c.set(Calendar.MONTH, Calendar.MAY);
        c.set(Calendar.DATE, 16);
        return c.getTime();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_parseError() {
        JamqueryDateFormatter.parse("3");
    }
}