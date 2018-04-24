package com.turastory.jamquery.domain.usecase;

import com.turastory.jamquery.presentation.vo.JamqueryVO;

import java.util.List;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery List를 가져오는 비동기 요청.
 */
public interface GetJamqueryListUseCase extends UseCase {
    
    void execute(String keyWord, UseCaseCallback callback);
    
    interface UseCaseCallback {
        void onJamqueryListLoaded(List<JamqueryVO> jamqueries);
        
        void onError(Exception e);
    }
}
