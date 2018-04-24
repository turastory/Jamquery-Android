package com.turastory.jamquery.presentation.ui.jamquery_list;

import android.os.Bundle;
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
import com.turastory.jamquery.domain.usecase.GetJamqueryListUseCase;
import com.turastory.jamquery.domain.usecase.GetJamqueryListUseCaseImpl;
import com.turastory.jamquery.presentation.base.BaseActivity;
import com.turastory.jamquery.presentation.base.UIExecutor;
import com.turastory.jamquery.presentation.util.Stubs;
import com.turastory.jamquery.presentation.vo.JamqueryVO;

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
    
        setupJamqueries();
    }
    
    private void setupJamqueries() {
        jamqueryListAdapter = new JamqueryListAdapter();
        jamqueryList.setAdapter(jamqueryListAdapter);
        jamqueryList.setLayoutManager(new LinearLayoutManager(this));
    }
    
    private void initializePresenter() {
        JamqueryDataRepository repository = new JamqueryDataRepository(
            provideLocalDataSource(), provideRemoteDataSource());
    
        ThreadExecutor threadExecutor = JobExecutor.getInstance();
        UIThreadExecutor uiThreadExecutor = UIExecutor.getInstance();
    
        GetJamqueryListUseCase useCase = new GetJamqueryListUseCaseImpl(
            repository, threadExecutor, uiThreadExecutor);
    
        presenter = new JamqueryListActivityPresenter(this, useCase);
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
    public void showResult(List<JamqueryVO> jamqueries) {
        jamqueryListAdapter.setJamqueries(jamqueries);
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
}
