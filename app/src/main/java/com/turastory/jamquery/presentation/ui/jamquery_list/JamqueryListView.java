package com.turastory.jamquery.presentation.ui.jamquery_list;

import com.turastory.jamquery.presentation.base.JamqueryView;
import com.turastory.jamquery.presentation.vo.Jamquery;

import java.util.List;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery 리스트를 보여주는 View
 */
public interface JamqueryListView extends JamqueryView {
    void showResult(List<Jamquery> jamqueries);
    
    void showEmptyView(boolean show);
    
    void showError(Exception e);
}
