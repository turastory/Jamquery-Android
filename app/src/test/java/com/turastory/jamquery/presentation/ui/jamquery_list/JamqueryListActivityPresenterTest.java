package com.turastory.jamquery.presentation.ui.jamquery_list;

import com.turastory.jamquery.domain.usecase.GetJamqueryListUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by tura on 2018-04-12.
 */
public class JamqueryListActivityPresenterTest {
    
    private JamqueryListPresenter jamqueryListPresenter;
    
    @Mock
    private JamqueryListView mockJamqueryListView;
    @Mock
    private GetJamqueryListUseCase mockGetJamqueryListUseCase;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        jamqueryListPresenter = new JamqueryListActivityPresenter(
            mockJamqueryListView, mockGetJamqueryListUseCase);
    }
    
    @Test
    public void test_executeUseCaseSuccessOnEnterText() {
        String keyWord = "asdf";
        
        // Call onJamqueryListLoaded() when useCase.execute() is called.
        doAnswer(invocation -> {
            ((GetJamqueryListUseCase.UseCaseCallback) invocation.getArgument(1))
                .onJamqueryListLoaded(anyList());
            return null;
        }).when(mockGetJamqueryListUseCase).execute(eq(keyWord), any(GetJamqueryListUseCase.UseCaseCallback.class));
        
        jamqueryListPresenter.onEnterText(keyWord);
        
        verify(mockGetJamqueryListUseCase).execute(eq(keyWord), any(GetJamqueryListUseCase.UseCaseCallback.class));
        verify(mockJamqueryListView).showResult(anyList());
    }
    
    @Test
    public void test_executeUseCaseFailedOnEnterText() {
        String keyWord = "asdf";
    
        Exception mockException = mock(Exception.class);
        
        // Call onJamqueryListLoaded() when useCase.execute() is called.
        doAnswer(invocation -> {
            ((GetJamqueryListUseCase.UseCaseCallback) invocation.getArgument(1))
                .onError(mockException);
            return null;
        }).when(mockGetJamqueryListUseCase).execute(eq(keyWord), any(GetJamqueryListUseCase.UseCaseCallback.class));
        
        jamqueryListPresenter.onEnterText(keyWord);
        
        verify(mockGetJamqueryListUseCase).execute(eq(keyWord), any(GetJamqueryListUseCase.UseCaseCallback.class));
        verify(mockJamqueryListView).showError(mockException);
    }
}