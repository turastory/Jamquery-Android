package com.turastory.jamquery.presentation.ui.jamquery_list;

/**
 * Created by tura on 2018-04-11.
 */
public class JamqueryListActivityPresenter implements JamqueryListPresenter {
    
    private JamqueryListView view;
    
    public JamqueryListActivityPresenter(JamqueryListView view) {
        this.view = view;
    }
    
    @Override
    public void onEnterText(String text) {
        // TODO: 2018-04-11
    }
}
