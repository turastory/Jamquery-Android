package com.turastory.jamquery.data.datasource;

import android.content.Context;

import com.turastory.jamquery.data.network.JamqueryRestApi;

/**
 * Created by tura on 2018-04-12.
 */
public class JamqueryDataSourceProvider {
    
    private static final String remoteServerUrl = "http://jamquery.teamidus.com/";
    
    private Context context;
    
    public JamqueryDataSourceProvider(Context context) {
        this.context = context.getApplicationContext();
    }
    
    public JamqueryDataSource createCloudDataSource(JamqueryRestApi restApi) {
        return new JamqueryCloudDataSource(restApi);
    }
}
