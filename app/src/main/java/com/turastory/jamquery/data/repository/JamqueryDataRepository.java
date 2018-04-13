package com.turastory.jamquery.data.repository;

import com.turastory.jamquery.data.exception.JamqueryNotFoundException;
import com.turastory.jamquery.data.exception.NetworkException;
import com.turastory.jamquery.data.network.JamqueryRestApi;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;
import com.turastory.jamquery.domain.repository.JamqueryRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tura on 2018-04-12.
 * <p>
 * Implementation of {@link com.turastory.jamquery.domain.repository.JamqueryRepository}
 */
public class JamqueryDataRepository implements JamqueryRepository {
    
    private JamqueryRestApi restApi;
    
    protected JamqueryDataRepository(JamqueryRestApi jamqueryRestApi) {
        restApi = jamqueryRestApi;
    }
    
    @Override
    public void getJamqueryList(GetJamqueryListRq request, RepositoryCallback callback) {
        restApi.getJamqueryList(request).enqueue(new Callback<List<GetJamqueryListRs>>() {
            @Override
            public void onResponse(Call<List<GetJamqueryListRs>> call, Response<List<GetJamqueryListRs>> response) {
                if (response.isSuccessful()) {
                    callback.onLoad(response.body());
                } else {
                    callback.onError(new JamqueryNotFoundException());
                }
            }
        
            @Override
            public void onFailure(Call<List<GetJamqueryListRs>> call, Throwable t) {
                callback.onError(new NetworkException(t));
            }
        });
    }
}
