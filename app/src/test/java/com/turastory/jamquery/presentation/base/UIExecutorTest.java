package com.turastory.jamquery.presentation.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by tura on 2018-04-15.
 */
@RunWith(RobolectricTestRunner.class)
public class UIExecutorTest {
    
    @Test
    public void test_post() {
        Runnable mockRunnable = mock(Runnable.class);
        
        UIExecutor.getInstance().post(mockRunnable);
        
        verify(mockRunnable).run();
    }
    
    @Test
    public void test_notAllowNull() {
        try {
            UIExecutor.getInstance().post(null);
            fail();
        } catch (IllegalArgumentException ignored) {
        
        }
    }
}