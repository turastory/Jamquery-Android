package com.turastory.jamquery.data.datasource;

import android.content.Context;

import com.turastory.jamquery.data.network.JamqueryRestApi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import okhttp3.mockwebserver.MockWebServer;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by tura on 2018-04-13.
 */
public class JamqueryDataSourceProviderTest {
    
    @Rule
    public final MockWebServer server = new MockWebServer();
    
    private JamqueryDataSourceProvider dataSourceProvider;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dataSourceProvider = new JamqueryDataSourceProvider(mock(Context.class));
    }
    
    @Test
    public void test_createCloudDataSource() throws Exception {
        JamqueryDataSource dataSource = dataSourceProvider.createCloudDataSource(mock(JamqueryRestApi.class));
        assertThat(dataSource, instanceOf(JamqueryCloudDataSource.class));
    }
}