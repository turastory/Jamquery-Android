package com.turastory.jamquery.presentation;

import android.app.Application;

import com.turastory.jamquery.R;
import com.turastory.jamquery.presentation.util.font.Fonts;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by tura on 2018-04-26.
 *
 * Custom Application class.
 */
public class JamqueryApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        initializeFonts();
    }
    
    private void initializeFonts() {
        CalligraphyConfig.initDefault(
            new CalligraphyConfig.Builder()
                .setDefaultFontPath(Fonts.NORMAL)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
