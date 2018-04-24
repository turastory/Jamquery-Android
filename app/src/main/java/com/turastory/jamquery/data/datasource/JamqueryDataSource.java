package com.turastory.jamquery.data.datasource;

import com.turastory.jamquery.presentation.vo.Jamquery;

import java.util.List;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery 데이터 저장소
 */
public interface JamqueryDataSource extends DataSource {
    void getJamqueryList(String keyword, DataSourceCallback callback);
    
    interface DataSourceCallback {
        void onLoad(List<Jamquery> jamqueries);
        
        void onError(Exception e);
    }
}
