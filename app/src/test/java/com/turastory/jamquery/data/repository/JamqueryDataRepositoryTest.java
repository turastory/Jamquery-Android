package com.turastory.jamquery.data.repository;

import android.content.Context;

import com.turastory.jamquery.data.datasource.JamqueryDataSourceProvider;
import com.turastory.jamquery.domain.repository.JamqueryRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by tura on 2018-04-12.
 */
public class JamqueryDataRepositoryTest {
    
    private JamqueryDataRepository repository;
    private MockWebServer server;
    
    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);
        
        server = new MockWebServer();
        server.start();
        
        JamqueryDataSourceProvider provider = new JamqueryDataSourceProvider(mock(Context.class));
        provider.setBaseUrl(server.url("").toString());
        
        repository = new JamqueryDataRepository(provider);
    }
    
    @Test
    public void test_receiveAnyDataFromServer() {
        JamqueryRepository.RepositoryCallback mockCallback =
            mock(JamqueryRepository.RepositoryCallback.class);
        
        Retrofit retrofit = new Retrofit.Builder()
            .build();
        
        server.enqueue(new MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setBody("{}"));
        
        verify(mockCallback).onLoad(anyList());
        verifyNoMoreInteractions(mockCallback);
    }
    
    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }
}