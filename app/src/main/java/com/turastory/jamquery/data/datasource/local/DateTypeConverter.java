package com.turastory.jamquery.data.datasource.local;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by tura on 2018-04-24.
 * <p>
 * Date를 디비에 저장할 수 있게 Unix Time으로 상호 변환하는 컨버터
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
