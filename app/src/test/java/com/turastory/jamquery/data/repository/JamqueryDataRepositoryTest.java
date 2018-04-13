package com.turastory.jamquery.data.repository;

import com.turastory.jamquery.data.exception.JamqueryNotFoundException;
import com.turastory.jamquery.data.exception.NetworkException;
import com.turastory.jamquery.data.json.GetJamqueryListRsMock;
import com.turastory.jamquery.data.network.JamqueryRestApi;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;
import com.turastory.jamquery.domain.repository.JamqueryRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.mock.Calls;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by tura on 2018-04-13.
 *
 * Test Retrofit REST API using retrofit-mock + Mockito
 */
public class JamqueryDataRepositoryTest {
    
    @Mock
    private JamqueryRestApi mockRestApi;
    
    private JamqueryRepository repository;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repository = new JamqueryDataRepository(mockRestApi);
    }
    
    @Test
    public void test_getJamqueryListSuccessHappy() throws Exception {
        List<GetJamqueryListRs> responseList = new GetJamqueryListRsMock().getList();
    
        when(mockRestApi.getJamqueryList(any(GetJamqueryListRq.class)))
            .thenReturn(Calls.response(responseList));
    
        JamqueryRepository.RepositoryCallback mockCallback = mock(JamqueryRepository.RepositoryCallback.class);
        repository.getJamqueryList(new GetJamqueryListRq("test"), mockCallback);
    
        verify(mockCallback).onLoad(responseList);
        verifyNoMoreInteractions(mockCallback);
    }
    
    @Test
    public void test_getJamqueryListSuccessSad() throws Exception {
        when(mockRestApi.getJamqueryList(any(GetJamqueryListRq.class)))
            .thenReturn(Calls.response(Response.error(400,
                ResponseBody.create(null, "Error!"))));
        
        JamqueryRepository.RepositoryCallback mockCallback = mock(JamqueryRepository.RepositoryCallback.class);
        repository.getJamqueryList(new GetJamqueryListRq("test"), mockCallback);
        
        verify(mockCallback).onError(any(JamqueryNotFoundException.class));
        verifyNoMoreInteractions(mockCallback);
    }
    
    @Test
    public void test_getJamqueryListFailure() throws Exception {
        when(mockRestApi.getJamqueryList(any(GetJamqueryListRq.class)))
            .thenReturn(Calls.failure(new IOException("test")));
        
        JamqueryRepository.RepositoryCallback mockCallback = mock(JamqueryRepository.RepositoryCallback.class);
        repository.getJamqueryList(new GetJamqueryListRq("test"), mockCallback);
        
        verify(mockCallback).onError(any(NetworkException.class));
        verifyNoMoreInteractions(mockCallback);
    }
}