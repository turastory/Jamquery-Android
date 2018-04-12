package com.turastory.jamquery.data.repository;

import com.turastory.jamquery.data.datasource.JamqueryDataSource;
import com.turastory.jamquery.data.datasource.JamqueryDataSourceProvider;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;
import com.turastory.jamquery.domain.repository.JamqueryRepository;

import java.util.List;

/**
 * Created by tura on 2018-04-12.
 * <p>
 * Implementation of {@link com.turastory.jamquery.domain.repository.JamqueryRepository}
 */
public class JamqueryDataRepository implements JamqueryRepository {
    
    private static JamqueryDataRepository instance;
    private JamqueryDataSourceProvider dataSourceProvider;
    
    protected JamqueryDataRepository(JamqueryDataSourceProvider dataSourceProvider) {
        this.dataSourceProvider = dataSourceProvider;
    }
    
    public static JamqueryDataRepository getInstance(JamqueryDataSourceProvider dataSourceProvider) {
        if (instance == null)
            instance = new JamqueryDataRepository(dataSourceProvider);
        return instance;
    }
    
    @Override
    public void getJamqueryList(GetJamqueryListRq request, RepositoryCallback callback) {
        dataSourceProvider.createCloudDataSource().getJamqueryList(request, new JamqueryDataSource.DataSourceCallback() {
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
