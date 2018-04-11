package com.turastory.jamquery.presentation.ui.jamquery_list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.ViewGroup;
import android.widget.EditText;

import com.turastory.jamquery.R;
import com.turastory.jamquery.presentation.base.BaseActivity;
import com.turastory.jamquery.presentation.util.Stubs;
import com.turastory.jamquery.presentation.vo.JamqueryVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery 리스트를 보여주는 액티비티.
 */
public class JamqueryListActivity extends BaseActivity implements JamqueryListView {
    
    @BindView(R.id.jamquery_list)
    RecyclerView jamqueryList;
    @BindView(R.id.edit_text_query)
    EditText queryText;
    @BindView(R.id.jamquery_list_empty_view)
    ViewGroup emptyView;
    
    private JamqueryListPresenter presenter;
    
    private JamqueryListAdapter jamqueryListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jamquery_list);
        ButterKnife.bind(this);
    
        initializePresenter();
    
        queryText.addTextChangedListener(new Stubs.TextWatcherLogged() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                presenter.onEnterText(s.toString());
            }
        });
    
        setupJamqueries();
    }
    
    private void setupJamqueries() {
        jamqueryListAdapter = new JamqueryListAdapter();
        jamqueryList.setAdapter(jamqueryListAdapter);
        jamqueryList.setLayoutManager(new LinearLayoutManager(this));
    }
    
    private void initializePresenter() {
        // TODO: 2018-04-11 initialize presenter
    }
    
    @Override
    public void showResult(List<JamqueryVO> jamqueries) {
        jamqueryListAdapter.setJamqueries(jamqueries);
    }
}
