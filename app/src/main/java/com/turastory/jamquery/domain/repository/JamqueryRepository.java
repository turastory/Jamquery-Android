package com.turastory.jamquery.domain.repository;

import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;

import java.util.List;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery에 관련된 데이터를 가져오는 Repository
 */
public interface JamqueryRepository extends Repository {
    void getJamqueryList(GetJamqueryListRq request, RepositoryCallback callback);
    
    interface RepositoryCallback {
        void onLoad(List<GetJamqueryListRs> jamqueries);
        
        void onError(Exception e);
    }
}
