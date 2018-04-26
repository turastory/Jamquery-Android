package com.turastory.jamquery.presentation.util.font;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.TypefaceUtils;

/**
 * Created by tura on 2018-04-26.
 *
 * 폰트 추상화
 */
public class Fonts {
    public static final String BOLD = "fonts/NotoSansKR-Bold-Hestia.otf";
    public static final String NORMAL = "fonts/NotoSansKR-Regular-Hestia.otf";
    public static final String LIGHT = "fonts/NotoSansKR-Light-Hestia.otf";
    
    private Context context;
    
    private Fonts(Context context) {
        this.context = context;
    }
    
    public static Fonts get(Context context) {
        return new Fonts(context);
    }
    
    public static void bold(TextView textView) {
        textView.setTypeface(load(textView.getContext()).bold());
    }
    
    public static void normal(TextView textView) {
        textView.setTypeface(load(textView.getContext()).normal());
    }
    
    public static void light(TextView textView) {
        textView.setTypeface(load(textView.getContext()).light());
    }
    
    public static Fonts load(Context context) {
        return new Fonts(context);
    }
    
    public Typeface bold() {
        return TypefaceUtils.load(context.getAssets(), BOLD);
    }
    
    public Typeface normal() {
        return TypefaceUtils.load(context.getAssets(), NORMAL);
    }
    
    public Typeface light() {
        return TypefaceUtils.load(context.getAssets(), LIGHT);
    }
}
