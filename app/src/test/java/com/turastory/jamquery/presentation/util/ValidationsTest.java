package com.turastory.jamquery.presentation.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by tura on 2018-04-25.
 */
public class ValidationsTest {
    
    @Test
    public void checkNotNull_inputNull() {
        try {
            Validations.checkNotNull(null);
            fail();
        } catch (NullPointerException ignored) {
        
        }
    }
    
    @Test
    public void checkNotNull_inputObject() {
        Object obj = new Object();
        
        assertThat(Validations.checkNotNull(obj), is(obj));
    }
}