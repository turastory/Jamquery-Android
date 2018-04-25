package com.turastory.jamquery.presentation.util;

/**
 * Created by tura on 2018-04-25.
 * <p>
 * 몇 가지 체크 유틸.
 */
public class Validations {
    
    public static <T> T checkNotNull(T value) {
        if (value == null)
            throw new NullPointerException("value is null!");
        return value;
    }
}
