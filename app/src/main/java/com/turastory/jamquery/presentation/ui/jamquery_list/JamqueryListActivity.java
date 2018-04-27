package com.turastory.jamquery.presentation.ui.jamquery_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.GsonBuilder;
import com.turastory.jamquery.R;
import com.turastory.jamquery.data.datasource.JamqueryDataRepository;
import com.turastory.jamquery.data.datasource.JamqueryDataSource;
import com.turastory.jamquery.data.datasource.local.JamqueryDao;
import com.turastory.jamquery.data.datasource.local.JamqueryLocalDataSource;
import com.turastory.jamquery.data.datasource.local.JamqueryLocalDatabase;
import com.turastory.jamquery.data.datasource.remote.JamqueryCloudDataSource;
import com.turastory.jamquery.data.executor.JobExecutor;
import com.turastory.jamquery.data.network.JamqueryRestApi;
import com.turastory.jamquery.domain.ThreadExecutor;
import com.turastory.jamquery.domain.UIThreadExecutor;
import com.turastory.jamquery.domain.usecase.AddJamqueryUseCase;
import com.turastory.jamquery.domain.usecase.AddJamqueryUseCaseImpl;
import com.turastory.jamquery.domain.usecase.GetJamqueryListUseCase;
import com.turastory.jamquery.domain.usecase.GetJamqueryListUseCaseImpl;
import com.turastory.jamquery.presentation.base.BaseActivity;
import com.turastory.jamquery.presentation.base.UIExecutor;
import com.turastory.jamquery.presentation.ui.jamquery_list.add.AddJamqueryDialog;
import com.turastory.jamquery.presentation.util.Stubs;
import com.turastory.jamquery.presentation.vo.Jamquery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    @BindView(R.id.fab_add)
    FloatingActionButton fab;
    
    private JamqueryListPresenter presenter;
    
    private JamqueryListAdapter jamqueryListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jamquery_list);
        ButterKnife.bind(this);
    
        initializePresenter();
    
        queryText.addTextChangedListener(new Stubs.TextWatcherLogged(false) {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                presenter.onEnterText(s.toString());
            }
        });
    
        fab.setOnClickListener(v -> {
            presenter.onClickAdd();
        });
    
        setupRecyclerView();
    }
    
    private void setupRecyclerView() {
        jamqueryListAdapter = new JamqueryListAdapter();
        jamqueryList.setAdapter(jamqueryListAdapter);
        jamqueryList.setLayoutManager(new LinearLayoutManager(this));
    }
    
    private void initializePresenter() {
        JamqueryDataRepository repository = new JamqueryDataRepository(
            provideLocalDataSource(), provideRemoteDataSource());
    
        ThreadExecutor threadExecutor = JobExecutor.getInstance();
        UIThreadExecutor uiThreadExecutor = UIExecutor.getInstance();
    
        GetJamqueryListUseCase getJamqueryListUseCase = new GetJamqueryListUseCaseImpl(
            repository, threadExecutor, uiThreadExecutor);
    
        AddJamqueryUseCase addJamqueryUseCase = new AddJamqueryUseCaseImpl(repository, threadExecutor);
    
        presenter = new JamqueryListActivityPresenter(this, getJamqueryListUseCase, addJamqueryUseCase);
    }
    
    private JamqueryDataSource provideLocalDataSource() {
        JamqueryDao dao = JamqueryLocalDatabase.getInstance(this).jamqueryDao();
        return new JamqueryLocalDataSource(dao);
    }
    
    private JamqueryDataSource provideRemoteDataSource() {
        JamqueryRestApi restApi = buildJamqueryRestApi(JamqueryCloudDataSource.remoteServerUrl);
        return new JamqueryCloudDataSource(restApi);
    }
    
    private JamqueryRestApi buildJamqueryRestApi(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build();
        
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(buildGsonConverter())
            .build();
        
        return retrofit.create(JamqueryRestApi.class);
    }
    
    private Converter.Factory buildGsonConverter() {
        final GsonBuilder builder = new GsonBuilder();
        
        // Adding custom deserializers
        builder
            .serializeNulls()
            .setLenient();
        
        return GsonConverterFactory.create(builder.create());
    }
    
    @Override
    public void showResults(List<Jamquery> jamqueries) {
        jamqueryListAdapter.setJamqueries(jamqueries);
    }
    
    @Override
    public void hideResults() {
        jamqueryListAdapter.setJamqueries(new ArrayList<>());
    }
    
    @Override
    public void showEmptyView(boolean show) {
        emptyView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }
    
    @Override
    public void showError(Exception e) {
        // TODO: 2018-04-12 Error handling
        e.printStackTrace();
    }
    
    @Override
    public void openAddDialog() {
        new AddJamqueryDialog(this, (title, url) -> {
            presenter.addJamquery(title, url);
        }).show();
    }
}
