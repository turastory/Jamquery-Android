package com.turastory.jamquery.data.network;

import com.google.gson.GsonBuilder;
import com.turastory.jamquery.data.json.GetJamqueryListRsMock;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by tura on 2018-04-13.
 */
public class JamqueryRestApiTest {
    
    @Rule
    public final MockWebServer server = new MockWebServer();
    
    private JamqueryRestApi restApi;
    
    @Before
    public void setUp() throws Exception {
        restApi = buildJamqueryRestApi(server.url("/").toString());
    }
    
    @Test
    public void test_readJsonData() throws Exception {
        server.enqueue(new MockResponse().setBody(new GetJamqueryListRsMock().getJson()));
        
        Response<List<GetJamqueryListRs>> response =
            restApi.getJamqueryList(new GetJamqueryListRq("test")).execute();
        
        assertNotNull(response.body());
        assertThat(response.body().size(), is(2));
    }
    
    private JamqueryRestApi buildJamqueryRestApi(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build();
        
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(buildGsonConverter())
            .build();
        
        return retrofit.create(JamqueryRestApi.class);
    }
    
    private Converter.Factory buildGsonConverter() {
        final GsonBuilder builder = new GsonBuilder();
        
        // Adding custom deserializers
        builder
            .serializeNulls()
            .setLenient();
        
        return GsonConverterFactory.create(builder.create());
    }
}