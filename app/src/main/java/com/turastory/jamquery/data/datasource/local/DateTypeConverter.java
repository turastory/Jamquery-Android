package com.turastory.jamquery.data.datasource.local;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by tura on 2018-04-24.
 */
public class DateTypeConverter {
    
    @TypeConverter
    public Date longToDate(Long value) {
        return value == null ? null : new Date(value);
    }
    
    @TypeConverter
    public Long dateToLong(Date date) {
        return date == null ? null : date.getTime();
    }
}
