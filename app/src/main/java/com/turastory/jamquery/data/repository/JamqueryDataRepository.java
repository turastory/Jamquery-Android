package com.turastory.jamquery.data.repository;

import com.turastory.jamquery.data.datasource.JamqueryDataSource;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;

import java.util.List;

/**
 * Created by tura on 2018-04-12.
 * <p>
 * Repository 패턴을 이용한 DataSource Provider.
 */
public class JamqueryDataRepository implements JamqueryDataSource {
    
    // TODO: 2018-04-24 Use remote data source.
    private JamqueryDataSource localDataSource;
    private JamqueryDataSource remoteDataSource;
    
    public JamqueryDataRepository(JamqueryDataSource localDataSource,
                                  JamqueryDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }
    
    @Override
    public void getJamqueryList(GetJamqueryListRq request, DataSourceCallback callback) {
        localDataSource.getJamqueryList(request, new DataSourceCallback() {
            @Override
            public void onLoad(List<GetJamqueryListRs> jamqueries) {
                callback.onLoad(jamqueries);
            }
        
            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
    }
}
