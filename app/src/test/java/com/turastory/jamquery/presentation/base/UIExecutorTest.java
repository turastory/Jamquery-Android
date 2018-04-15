package com.turastory.jamquery.presentation.base;

import android.os.Looper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by tura on 2018-04-15.
 */
@RunWith(RobolectricTestRunner.class)
public class UIExecutorTest {
    
    @Test
    public void test_post() {
        Runnable mockRunnable = mock(Runnable.class);
        
        UIExecutor.getInstance().post(mockRunnable);
        shadowOf(Looper.getMainLooper()).idle();
        
        verify(mockRunnable).run();
    }
    
    @Test
    public void test_multiplePosts() {
        AtomicInteger counter = new AtomicInteger(0);
        int testCount = 5;
    
        for (int i = 0; i < testCount; i++)
            addOne(counter);
    
        shadowOf(Looper.getMainLooper()).idle();
    
        assertThat(counter.get(), is(testCount));
    }
    
    private void addOne(AtomicInteger counter) {
        UIExecutor.getInstance().post(() -> counter.addAndGet(1));
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