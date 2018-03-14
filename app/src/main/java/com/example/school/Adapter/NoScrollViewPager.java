package com.example.school.Adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 泡泡 on 2018/2/27.
 */

public class NoScrollViewPager extends ViewPager {

    private boolean isSlide = false;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public void setSlide(boolean slide) {
        isSlide = slide;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide;
    }
}
