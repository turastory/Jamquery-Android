package com.turastory.jamquery.data.network;

import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery REST API 정의
 */
public interface JamqueryRestApi {
    @POST("/searchJam")
    Call<List<GetJamqueryListRs>> getJamqueryList(@Body GetJamqueryListRq request);
}
