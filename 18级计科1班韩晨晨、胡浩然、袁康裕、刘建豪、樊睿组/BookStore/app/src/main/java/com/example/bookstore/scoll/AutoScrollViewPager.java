package com.example.bookstore.scoll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class AutoScrollViewPager extends ViewPager {
    private static final String TAG = "AutoScrollViewPager";
    private com.example.bookstore.scoll.AutoScrollHandler autoScrollHandler;
    private Context mContext;
    private boolean noScroll = true;
    public AutoScrollViewPager(@NonNull Context context) {
        this(context,null);
        mContext = context;
        autoScrollHandler = new com.example.bookstore.scoll.AutoScrollHandler(this);
    }

    public AutoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        autoScrollHandler = new com.example.bookstore.scoll.AutoScrollHandler(this);
    }


    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }


    public void startAutoPlay() {
        if (autoScrollHandler != null) {
            autoScrollHandler.start();
        }
    }

    public void stopAutoPlay() {
        if (autoScrollHandler != null) {
            autoScrollHandler.stop();
        }
    }
}