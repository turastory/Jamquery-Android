package com.turastory.jamquery.presentation.ui.jamquery_list;

import com.turastory.jamquery.domain.usecase.AddJamqueryUseCase;
import com.turastory.jamquery.domain.usecase.GetJamqueryListUseCase;
import com.turastory.jamquery.presentation.vo.Jamquery;

import java.util.Date;
import java.util.List;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Presenter for JamqueryListActivity
 */
public class JamqueryListActivityPresenter implements JamqueryListPresenter {
    
    private JamqueryListView view;
    private GetJamqueryListUseCase getJamqueryUseCase;
    private AddJamqueryUseCase addJamqueryUseCase;
    
    public JamqueryListActivityPresenter(JamqueryListView view,
                                         GetJamqueryListUseCase getJamqueryUseCase,
                                         AddJamqueryUseCase addJamqueryUseCase) {
        this.view = view;
        this.getJamqueryUseCase = getJamqueryUseCase;
        this.addJamqueryUseCase = addJamqueryUseCase;
    }
    
    @Override
    public void onEnterText(String text) {
        if (text.isEmpty()) {
            view.showEmptyView(true);
            view.hideResults();
            return;
        }
    
        view.showEmptyView(false);
    
        getJamqueryUseCase.execute(text, new GetJamqueryListUseCase.UseCaseCallback() {
            @Override
            public void onJamqueryListLoaded(List<Jamquery> jamqueries) {
                view.showResults(jamqueries);
            }
    
            @Override
            public void onError(Exception e) {
                view.showError(e);
            }
        });
    }
    
    @Override
    public void onClickAdd() {
        view.openAddDialog();
    }
    
    @Override
    public void addJamquery(String title, String url) {
        addJamqueryUseCase.execute(new Jamquery(new Date(), title, url));
    }
}
