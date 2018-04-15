package com.turastory.jamquery.data.executor;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by tura on 2018-04-15.
 */
public class JobExecutorTest {
    
    private AtomicInteger counter = new AtomicInteger(0);
    
    @Test
    public void test_execute() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
    
        executeAddOne(countDownLatch);
    
        try {
            countDownLatch.await();
            assertThat(counter.get(), is(1));
        } catch (InterruptedException e) {
            fail();
            e.printStackTrace();
        }
    }
    
    @Test
    public void test_multipleExecutes() {
        int executeCount = 5;
        CountDownLatch countDownLatch = new CountDownLatch(executeCount);
        
        for (int i = 0; i < executeCount; i++)
            executeAddOne(countDownLatch);
        
        try {
            countDownLatch.await();
            assertThat(counter.get(), is(executeCount));
        } catch (InterruptedException e) {
            fail();
            e.printStackTrace();
        }
    }
    
    private void executeAddOne(CountDownLatch countDownLatch) {
        JobExecutor.getInstance().execute(() -> {
            counter.addAndGet(1);
            countDownLatch.countDown();
        });
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