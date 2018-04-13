package com.turastory.jamquery.data.repository;

import com.turastory.jamquery.data.datasource.JamqueryDataSource;
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
    
    private JamqueryDataSource dataSource;
    
    protected JamqueryDataRepository(JamqueryDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public void getJamqueryList(GetJamqueryListRq request, RepositoryCallback callback) {
        dataSource.getJamqueryList(request, new JamqueryDataSource.DataSourceCallback() {
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
