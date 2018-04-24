package com.turastory.jamquery.data.datasource.remote;

import com.turastory.jamquery.data.datasource.JamqueryDataSource;
import com.turastory.jamquery.data.exception.JamqueryNotFoundException;
import com.turastory.jamquery.data.exception.NetworkException;
import com.turastory.jamquery.data.network.JamqueryRestApi;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;
import com.turastory.jamquery.domain.mapper.JamqueryMapper;
import com.turastory.jamquery.presentation.vo.Jamquery;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tura on 2018-04-12.
 * <p>
 * 원격 저장소를 나타냄.
 */
public class JamqueryCloudDataSource implements JamqueryDataSource {
    
    public static final String remoteServerUrl = "http://jamquery.teamidus.com/";
    
    private JamqueryRestApi restApi;
    
    public JamqueryCloudDataSource(JamqueryRestApi restApi) {
        this.restApi = restApi;
    }
    
    @Override
    public void addJamquery(Jamquery jamquery) {
        // TODO: 2018-04-25 구현
    }
    
    @Override
    public void getJamqueryList(String keyword, DataSourceCallback callback) {
        restApi.getJamqueryList(new GetJamqueryListRq(keyword)).enqueue(new Callback<List<GetJamqueryListRs>>() {
            @Override
            public void onResponse(Call<List<GetJamqueryListRs>> call, Response<List<GetJamqueryListRs>> response) {
                if (response.isSuccessful()) {
                    callback.onLoad(new JamqueryMapper().convert(response.body()));
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
