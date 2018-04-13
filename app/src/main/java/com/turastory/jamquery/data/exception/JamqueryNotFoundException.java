package com.turastory.jamquery.data.exception;

/**
 * Created by tura on 2018-04-13.
 * <p>
 * Jamquery를 찾을 수 없을 때..
 */
public class JamqueryNotFoundException extends JamqueryException {
    public JamqueryNotFoundException() {
        super("Jamquery not found.");
    }
}
