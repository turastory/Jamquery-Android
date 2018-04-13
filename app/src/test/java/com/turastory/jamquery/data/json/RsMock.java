package com.turastory.jamquery.data.json;

import java.util.List;

/**
 * Created by tura on 2018-04-13.
 */
public interface RsMock<T> {
    String getJson();
    
    T get();
    
    List<T> getList();
}
