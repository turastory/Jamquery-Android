package com.turastory.jamquery.domain;

/**
 * Created by tura on 2018-04-11.
 *
 * 일반적인 비동기 작업을 할 때 사용할 쓰레드 실행기.
 */
public interface ThreadExecutor {
    void execute(Runnable runnable);
}
