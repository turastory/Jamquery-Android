package com.turastory.jamquery.data.datasource;

import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;

/**
 * Created by tura on 2018-04-24.
 * <p>
 * 로컬 저장소를 나타냄.
 */
public class JamqueryLocalDataSource implements JamqueryDataSource {
    
    @Override
    public void getJamqueryList(GetJamqueryListRq request, DataSourceCallback callback) {
        // FIXME: 2018-04-24 구현
        callback.onError(new Exception("Not Implemented"));
    }
}
