package com.turastory.jamquery.data.datasource;

import com.turastory.jamquery.data.exception.NetworkException;
import com.turastory.jamquery.data.network.JamqueryRestApi;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tura on 2018-04-12.
 */
public class JamqueryCloudDataSource implements JamqueryDataSource {
    
    private JamqueryRestApi restApi;
    
    public JamqueryCloudDataSource(JamqueryRestApi restApi) {
        this.restApi = restApi;
    }
    
    @Override
    public void getJamqueryList(GetJamqueryListRq request, DataSourceCallback callback) {
        restApi.getJamqueryList(request)
            .enqueue(new Callback<List<GetJamqueryListRs>>() {
                @Override
                public void onResponse(Call<List<GetJamqueryListRs>> call, Response<List<GetJamqueryListRs>> response) {
                    callback.onLoad(response.body());
                }
                
                @Override
                public void onFailure(Call<List<GetJamqueryListRs>> call, Throwable t) {
                    callback.onError(new NetworkException(t));
                }
            });
    }
}
