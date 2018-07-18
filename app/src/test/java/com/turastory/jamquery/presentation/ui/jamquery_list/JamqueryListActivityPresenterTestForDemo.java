package com.turastory.jamquery.presentation.ui.jamquery_list;

import com.turastory.jamquery.domain.usecase.AddJamqueryUseCase;
import com.turastory.jamquery.domain.usecase.GetJamqueryListUseCase;
import com.turastory.jamquery.presentation.vo.Jamquery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by tura on 2018-04-12.
 */
public class JamqueryListActivityPresenterTestForDemo {
    
    private JamqueryListPresenter jamqueryListPresenter;
    
    @Mock
    private JamqueryListView mockJamqueryListView;
    
    // Inject Dependencies from DI Container
    private GetJamqueryListUseCase realGetJamqueryListUseCase;
    private AddJamqueryUseCase realAddJamqueryUseCase;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        jamqueryListPresenter = new JamqueryListActivityPresenter(
            mockJamqueryListView, realGetJamqueryListUseCase, realAddJamqueryUseCase);
    }
    
    @Test
    public void test_executeGetListUseCaseSuccessOnEnterText() {
        String keyWord = "asdf";
        
        jamqueryListPresenter.onEnterText(keyWord);
        
        verify(realGetJamqueryListUseCase).execute(eq(keyWord), any(GetJamqueryListUseCase.UseCaseCallback.class));
    }
    
    @Test
    public void test_executeGetListUseCaseFailedOnEnterText() {
        String keyWord = "asdf";
        
        Exception mockException = mock(Exception.class);
        
        jamqueryListPresenter.onEnterText(keyWord);
        
        verify(realGetJamqueryListUseCase).execute(eq(keyWord), any(GetJamqueryListUseCase.UseCaseCallback.class));
    }
    
    @Test
    public void test_executeAddJamqueryUseCase() {
        String title = "title";
        String url = "http://test.com/";
        
        jamqueryListPresenter.addJamquery(title, url);
        
        verify(realAddJamqueryUseCase).execute(any(Jamquery.class));
        verifyZeroInteractions(realGetJamqueryListUseCase);
    }
}