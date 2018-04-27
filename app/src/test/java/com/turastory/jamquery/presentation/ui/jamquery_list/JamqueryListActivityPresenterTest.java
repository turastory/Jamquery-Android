package com.turastory.jamquery.presentation.ui.jamquery_list;

import com.turastory.jamquery.domain.usecase.AddJamqueryUseCase;
import com.turastory.jamquery.domain.usecase.GetJamqueryListUseCase;
import com.turastory.jamquery.presentation.vo.Jamquery;

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
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by tura on 2018-04-12.
 */
public class JamqueryListActivityPresenterTest {
    
    private JamqueryListPresenter jamqueryListPresenter;
    
    @Mock
    private JamqueryListView mockJamqueryListView;
    @Mock
    private GetJamqueryListUseCase mockGetJamqueryListUseCase;
    @Mock
    private AddJamqueryUseCase mockAddJamqueryUseCase;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        jamqueryListPresenter = new JamqueryListActivityPresenter(
            mockJamqueryListView, mockGetJamqueryListUseCase, mockAddJamqueryUseCase);
    }
    
    @Test
    public void test_executeGetListUseCaseSuccessOnEnterText() {
        String keyWord = "asdf";
        
        // Call onJamqueryListLoaded() when useCase.execute() is called.
        doAnswer(invocation -> {
            ((GetJamqueryListUseCase.UseCaseCallback) invocation.getArgument(1))
                .onJamqueryListLoaded(anyList());
            return null;
        }).when(mockGetJamqueryListUseCase).execute(eq(keyWord), any(GetJamqueryListUseCase.UseCaseCallback.class));
        
        jamqueryListPresenter.onEnterText(keyWord);
        
        verify(mockGetJamqueryListUseCase).execute(eq(keyWord), any(GetJamqueryListUseCase.UseCaseCallback.class));
        verify(mockJamqueryListView).showResults(anyList());
    }
    
    @Test
    public void test_executeGetListUseCaseFailedOnEnterText() {
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
    
    @Test
    public void test_executeAddJamqueryUseCase() {
        String title = "title";
        String url = "http://test.com/";
        
        jamqueryListPresenter.addJamquery(title, url);
        
        verify(mockAddJamqueryUseCase).execute(any(Jamquery.class));
        verifyZeroInteractions(mockGetJamqueryListUseCase);
        verifyZeroInteractions(mockJamqueryListView);
    }
    
    @Test
    public void test_showEmptyViewWhenNoText() {
        String keyWord = "";
        
        jamqueryListPresenter.onEnterText(keyWord);
        
        verify(mockJamqueryListView).showEmptyView(true);
    }
    
    @Test
    public void test_onClickAddShouldOpenAddDialog() {
        jamqueryListPresenter.onClickAdd();
        
        verify(mockJamqueryListView).openAddDialog();
    }
}