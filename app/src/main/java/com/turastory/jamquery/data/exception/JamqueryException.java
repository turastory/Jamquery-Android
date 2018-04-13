package com.turastory.jamquery.data.exception;

/**
 * Created by tura on 2018-04-13.
 * <p>
 * Jamquery에 관한 Exception..?
 */
public abstract class JamqueryException extends Exception {
    public JamqueryException(String message) {
        super(message);
    }
}
