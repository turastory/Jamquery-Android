package com.turastory.jamquery.domain;

/**
 * Created by tura on 2018-04-11.
 *
 * UI 쓰레드에서 해야할 작업에 필요한 메인 쓰레드 실행기.
 */
public interface UIThreadExecutor {
    void post(Runnable runnable);
}
