package com.turastory.jamquery.presentation.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Android Framework 관련 콜백들
 */
public class Stubs {
    private static String TAG = "Stubs";
    
    public static class TextWatcherLogged implements TextWatcher {
    
        private boolean enableLog;
    
        public TextWatcherLogged() {
            this(true);
        }
    
        public TextWatcherLogged(boolean enableLog) {
            this.enableLog = enableLog;
        }
        
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (enableLog) {
                Log.e(TAG, "-- call beforeTextChanged(s, start, count, after)");
                Log.e(TAG, "s: " + s);
                Log.e(TAG, "start: " + start);
                Log.e(TAG, "count: " + count);
                Log.e(TAG, "after: " + after);
            }
        }
        
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (enableLog) {
                Log.e(TAG, "-- call onTextChanged(s, start, before, count)");
                Log.e(TAG, "s: " + s);
                Log.e(TAG, "start: " + start);
                Log.e(TAG, "before: " + before);
                Log.e(TAG, "count: " + count);
            }
        }
        
        @Override
        public void afterTextChanged(Editable s) {
            if (enableLog) {
                Log.e(TAG, "-- call afterTextChanged(s)");
                Log.e(TAG, "s: " + s);
            }
        }
    }
}
