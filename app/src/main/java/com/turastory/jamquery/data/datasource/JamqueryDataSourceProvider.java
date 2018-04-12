package com.turastory.jamquery.data.datasource;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.turastory.jamquery.data.network.JamqueryRestApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tura on 2018-04-12.
 */
public class JamqueryDataSourceProvider {
    
    private static final String remoteServerUrl = "http://jamquery.teamidus.com/";
    
    private Context context;
    private String baseUrl;
    
    public JamqueryDataSourceProvider(Context context) {
        this.context = context.getApplicationContext();
    }
    
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public JamqueryDataSource createCloudDataSource() {
        return new JamqueryCloudDataSource(buildJamqueryRestApi(baseUrl));
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
