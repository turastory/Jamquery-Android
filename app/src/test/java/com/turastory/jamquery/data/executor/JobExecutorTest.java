package com.turastory.jamquery.data.executor;

import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by tura on 2018-04-15.
 */
public class JobExecutorTest {
    
    @Test
    public void test_execute() {
        Runnable mockRunnable = mock(Runnable.class);
        
        JobExecutor.getInstance().execute(mockRunnable);
        
        verify(mockRunnable).run();
    }
    
    @Test
    public void test_notAllowNull() {
        try {
            JobExecutor.getInstance().execute(null);
            fail();
        } catch (IllegalArgumentException ignored) {
        
        }
    }
}