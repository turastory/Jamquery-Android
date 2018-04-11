package com.turastory.jamquery.domain.usecase;

/**
 * Created by tura on 2018-04-11.
 *
 * 비동기 요청을 나타내는 클래스
 * domain - presentation 을 잇는 역할
 */
public interface UseCase extends Runnable {
    @Override
    void run();
}
