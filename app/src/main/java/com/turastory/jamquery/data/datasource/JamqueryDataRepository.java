package com.turastory.jamquery.data.datasource;

import com.turastory.jamquery.presentation.vo.Jamquery;

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
    public void addJamquery(Jamquery jamquery) {
        localDataSource.addJamquery(jamquery);
    }
    
    @Override
    public void getJamqueryList(String keyword, DataSourceCallback callback) {
        localDataSource.getJamqueryList(keyword, new DataSourceCallback() {
            @Override
            public void onLoad(List<Jamquery> jamqueries) {
                callback.onLoad(jamqueries);
            }
        
            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
    }
}
