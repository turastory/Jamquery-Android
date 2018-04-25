package com.turastory.jamquery.domain.usecase;

import com.turastory.jamquery.data.datasource.JamqueryDataRepository;
import com.turastory.jamquery.domain.ThreadExecutor;
import com.turastory.jamquery.presentation.vo.Jamquery;

/**
 * Created by tura on 2018-04-25.
 */
public class AddJamqueryUseCaseImpl implements AddJamqueryUseCase {
    
    private JamqueryDataRepository repository;
    private ThreadExecutor threadExecutor;
    private Jamquery jamquery;
    
    public AddJamqueryUseCaseImpl(JamqueryDataRepository repository,
                                  ThreadExecutor threadExecutor) {
        this.repository = repository;
        this.threadExecutor = threadExecutor;
    }
    
    @Override
    public void execute(Jamquery jamquery) {
        this.jamquery = jamquery;
        
        threadExecutor.execute(this);
    }
    
    @Override
    public void run() {
        repository.addJamquery(jamquery);
    }
}
