package com.turastory.jamquery.data.datasource;

import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;

import java.util.List;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery 데이터 저장소
 */
public interface JamqueryDataSource extends DataSource {
    void getJamqueryList(GetJamqueryListRq request, DataSourceCallback callback);
    
    interface DataSourceCallback {
        void onLoad(List<GetJamqueryListRs> jamqueries);
        
        void onError(Exception e);
    }
}
