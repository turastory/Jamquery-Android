package com.turastory.jamquery.presentation.ui.jamquery_list;

import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;
import com.turastory.jamquery.domain.mapper.JamqueryMapper;
import com.turastory.jamquery.domain.usecase.GetJamqueryListUseCase;

import java.util.List;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Presenter for JamqueryListActivity
 */
public class JamqueryListActivityPresenter implements JamqueryListPresenter {
    
    private JamqueryListView view;
    private GetJamqueryListUseCase useCase;
    private JamqueryMapper mapper;
    
    public JamqueryListActivityPresenter(JamqueryListView view,
                                         GetJamqueryListUseCase useCase,
                                         JamqueryMapper mapper) {
        this.view = view;
        this.useCase = useCase;
        this.mapper = mapper;
    }
    
    @Override
    public void onEnterText(String text) {
        useCase.execute(text, new GetJamqueryListUseCase.UseCaseCallback() {
            @Override
            public void onJamqueryListLoaded(List<GetJamqueryListRs> jamqueries) {
                view.showResult(mapper.convert(jamqueries));
            }
    
            @Override
            public void onError(Exception e) {
                view.showError(e);
            }
        });
    }
}
