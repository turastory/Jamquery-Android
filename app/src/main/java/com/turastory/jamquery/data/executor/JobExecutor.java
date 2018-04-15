package com.turastory.jamquery.data.executor;

import com.turastory.jamquery.domain.ThreadExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by tura on 2018-04-15.
 * <p>
 * Wrapper for ThreadPoolExecutor
 */
public class JobExecutor implements ThreadExecutor {
    
    private final BlockingQueue<Runnable> queue;
    private final ThreadPoolExecutor executor;
    
    private JobExecutor() {
        queue = new LinkedBlockingQueue<>();
        executor = new ThreadPoolExecutor(
            3,
            5,
            10,
            TimeUnit.SECONDS,
            queue);
    }
    
    public static JobExecutor getInstance() {
        return Singleton.instance;
    }
    
    @Override
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("runnable == null");
        }
        executor.execute(runnable);
    }
    
    public static class Singleton {
        private static final JobExecutor instance = new JobExecutor();
    }
}
