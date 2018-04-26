package com.turastory.jamquery.domain.usecase;

import com.turastory.jamquery.data.datasource.JamqueryDataRepository;
import com.turastory.jamquery.domain.ThreadExecutor;
import com.turastory.jamquery.presentation.vo.Jamquery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by tura on 2018-04-26.
 */
public class AddJamqueryUseCaseTest {
    
    private AddJamqueryUseCase useCase;
    
    @Mock
    private JamqueryDataRepository mockRepository;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        useCase = new AddJamqueryUseCaseImpl(mockRepository, mockThreadExecutor);
    }
    
    @Test
    public void execute() throws Exception {
        Jamquery mockJamquery = mock(Jamquery.class);
        
        useCase.execute(mockJamquery);
        
        verify(mockThreadExecutor).execute(any(Runnable.class));
        verifyNoMoreInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockRepository);
    }
    
    @Test
    public void executeAndRun() throws Exception {
        Jamquery mockJamquery = mock(Jamquery.class);
    
        useCase.execute(mockJamquery);
        useCase.run();
    
        verify(mockThreadExecutor).execute(any(Runnable.class));
        verify(mockRepository).addJamquery(mockJamquery);
        verifyNoMoreInteractions(mockThreadExecutor);
        verifyNoMoreInteractions(mockRepository);
    }
    
}