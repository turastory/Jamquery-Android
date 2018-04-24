package com.turastory.jamquery.domain.usecase;

import com.turastory.jamquery.data.datasource.JamqueryDataSource;
import com.turastory.jamquery.data.repository.JamqueryDataRepository;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRq;
import com.turastory.jamquery.data.rqrs.GetJamqueryListRs;
import com.turastory.jamquery.domain.ThreadExecutor;
import com.turastory.jamquery.domain.UIThreadExecutor;

import java.util.List;

/**
 * Created by tura on 2018-04-12.
 * <p>
 * Implementation of {@link GetJamqueryListUseCase}.
 */
public class GetJamqueryListUseCaseImpl implements GetJamqueryListUseCase {
    
    private JamqueryDataRepository repository;
    private ThreadExecutor threadExecutor;
    private UIThreadExecutor uiThreadExecutor;
    
    private String keyWord;
    private UseCaseCallback callback;
    
    public GetJamqueryListUseCaseImpl(JamqueryDataRepository repository,
                                      ThreadExecutor threadExecutor,
                                      UIThreadExecutor uiThreadExecutor) {
        this.repository = repository;
        this.threadExecutor = threadExecutor;
        this.uiThreadExecutor = uiThreadExecutor;
    }
    
    @Override
    public void execute(String keyWord, UseCaseCallback callback) {
        this.keyWord = keyWord;
        this.callback = callback;
        
        // Run this use case. (see run() method)
        threadExecutor.execute(this);
    }
    
    @Override
    public void run() {
        // Use case callback runs in Presenter,
        // so use UI Thread.
        repository.getJamqueryList(new GetJamqueryListRq(keyWord), new JamqueryDataSource.DataSourceCallback() {
            @Override
            public void onLoad(List<GetJamqueryListRs> jamqueries) {
                uiThreadExecutor.post(() -> callback.onJamqueryListLoaded(jamqueries));
            }
            
            @Override
            public void onError(Exception e) {
                uiThreadExecutor.post(() -> callback.onError(e));
            }
        });
    }
}
