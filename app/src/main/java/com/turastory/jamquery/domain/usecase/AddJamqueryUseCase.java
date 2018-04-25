package com.turastory.jamquery.domain.usecase;

import com.turastory.jamquery.presentation.vo.Jamquery;

/**
 * Created by tura on 2018-04-25.
 * <p>
 * Jamquery를 추가하는 요청.
 */
public interface AddJamqueryUseCase extends UseCase {
    void execute(Jamquery jamquery);
}
