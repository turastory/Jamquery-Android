package com.turastory.jamquery.presentation.util;

import org.junit.Test;

import java.util.Date;

/**
 * Created by tura on 2018-04-12.
 */
public class DateComparatorTest {
    
    @Test
    public void test_isEqualExactlySame() {
        Date date = new Date();
        
        DateComparator.isEqual(date, date);
    }
    
    @Test
    public void test_isEqual() {
        Date date = new Date();
        Date another = new Date(date.getTime());
        
        DateComparator.isEqual(date, another);
    }
}