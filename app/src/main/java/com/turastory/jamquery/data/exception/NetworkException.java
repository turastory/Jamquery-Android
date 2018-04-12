package com.turastory.jamquery.data.exception;

/**
 * Created by tura on 2018-04-12.
 */
public class NetworkException extends Exception {
    public NetworkException(Throwable t) {
        super(t.getMessage(), t.getCause());
    }
}
