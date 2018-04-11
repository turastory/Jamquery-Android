package com.turastory.jamquery.presentation.ui.jamquery_list;

import com.turastory.jamquery.domain.usecase.GetJamqueryListUseCase;
import com.turastory.jamquery.presentation.vo.JamqueryVO;

import java.util.List;

/**
 * Created by tura on 2018-04-11.
 *
 * Presenter for JamqueryListActivity
 */
public class JamqueryListActivityPresenter implements JamqueryListPresenter {
    
    private JamqueryListView view;
    private GetJamqueryListUseCase useCase;
    
    public JamqueryListActivityPresenter(JamqueryListView view, GetJamqueryListUseCase useCase) {
        this.view = view;
        this.useCase = useCase;
    }
    
    @Override
    public void onEnterText(String text) {
        useCase.execute(text, new GetJamqueryListUseCase.UseCaseCallback() {
            @Override
            public void onJamqueryListLoaded(List<JamqueryVO> jamqueries) {
                view.showResult(jamqueries);
            }
        
            @Override
            public void onError(Exception e) {
                view.showError(e);
            }
        });
    }
}
