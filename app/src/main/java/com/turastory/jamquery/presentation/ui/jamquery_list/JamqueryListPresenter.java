package com.turastory.jamquery.presentation.ui.jamquery_list;

import com.turastory.jamquery.presentation.base.JamqueryPresenter;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery 리스트를 보여주는 View
 */
public interface JamqueryListPresenter extends JamqueryPresenter {
    void onEnterText(String text);
    
    void onClickAdd();
    
    void addJamquery(String title, String url);
    // TODO: 2018-04-11 RecyclerView Item 눌렀을 때 반응
}
