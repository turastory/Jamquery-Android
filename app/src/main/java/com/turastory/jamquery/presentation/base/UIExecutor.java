package com.turastory.jamquery.presentation.base;

import android.os.Handler;
import android.os.Looper;

import com.turastory.jamquery.domain.UIThreadExecutor;

/**
 * Created by tura on 2018-04-15.
 * <p>
 * Wrapper for UI Main Thread.
 */
public class UIExecutor implements UIThreadExecutor {
    private final Handler handler;
    
    private UIExecutor() {
        this.handler = new Handler(Looper.getMainLooper());
    }
    
    public static UIExecutor getInstance() {
        return Singleton.instance;
    }
    
    @Override
    public void post(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("runnable == null");
        }
        handler.post(runnable);
    }
    
    private static class Singleton {
        private static final UIExecutor instance = new UIExecutor();
    }
}
