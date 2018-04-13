package com.turastory.jamquery.data.repository;

import com.turastory.jamquery.data.datasource.JamqueryDataSource;
import com.turastory.jamquery.data.json.GetJamqueryListRsMock;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;
import com.turastory.jamquery.domain.repository.JamqueryRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by tura on 2018-04-13.
 * <p>
 * Test Retrofit REST API using retrofit-mock + Mockito
 */
public class JamqueryDataRepositoryTest {
    
    @Mock
    private JamqueryDataSource mockDataSource;
    
    private JamqueryRepository repository;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repository = new JamqueryDataRepository(mockDataSource);
    }
    
    @Test
    public void test_getJamqueryListHappy() throws Exception {
        JamqueryRepository.RepositoryCallback mockCallback = mock(JamqueryRepository.RepositoryCallback.class);
        List<GetJamqueryListRs> mockList = new GetJamqueryListRsMock().getList();
    
        doAnswer(invocation -> {
            ((JamqueryDataSource.DataSourceCallback) invocation.getArgument(1))
                .onLoad(mockList);
            return null;
        }).when(mockDataSource).getJamqueryList(any(GetJamqueryListRq.class),
            any(JamqueryDataSource.DataSourceCallback.class));
        
        repository.getJamqueryList(new GetJamqueryListRq("test"), mockCallback);
    
        verify(mockCallback).onLoad(mockList);
        verifyNoMoreInteractions(mockCallback);
    }
    
    @Test
    public void test_getJamqueryListError() throws Exception {
        JamqueryRepository.RepositoryCallback mockCallback = mock(JamqueryRepository.RepositoryCallback.class);
        Exception mockException = mock(Exception.class);
    
        doAnswer(invocation -> {
            ((JamqueryDataSource.DataSourceCallback) invocation.getArgument(1))
                .onError(mockException);
            return null;
        }).when(mockDataSource).getJamqueryList(any(GetJamqueryListRq.class),
            any(JamqueryDataSource.DataSourceCallback.class));
        
        repository.getJamqueryList(new GetJamqueryListRq("test"), mockCallback);
    
        verify(mockCallback).onError(mockException);
        verifyNoMoreInteractions(mockCallback);
    }
}