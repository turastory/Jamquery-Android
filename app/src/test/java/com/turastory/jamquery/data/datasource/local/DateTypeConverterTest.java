package com.turastory.jamquery.data.datasource.local;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by tura on 2018-04-26.
 */
public class DateTypeConverterTest {
    @Test
    public void test_longToDate() throws Exception {
        DateTypeConverter converter = new DateTypeConverter();
        Date date = new Date();
        
        assertThat(converter.longToDate(date.getTime()), is(date));
    }
    
    @Test
    public void test_longToDateNull() throws Exception {
        DateTypeConverter converter = new DateTypeConverter();
        
        assertThat(converter.longToDate(null), is(nullValue()));
    }
    
    @Test
    public void test_dateToLong() throws Exception {
        DateTypeConverter converter = new DateTypeConverter();
        Date date = new Date();
        
        assertThat(converter.dateToLong(date), is(date.getTime()));
    }
    
    @Test
    public void test_dateToLongNull() throws Exception {
        DateTypeConverter converter = new DateTypeConverter();
        
        assertThat(converter.dateToLong(null), is(nullValue()));
    }
}