package com.example.bookstore.scoll;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.example.bookstore.R;


public class BannerIndicator extends LinearLayout {
    private static final String TAG = "BannerIndicator";

    private int dashGap;

    private int position;
    private ViewPager viewPager;
    private int slider_width;
    private int slider_height;
    private int sliderAlign;

    public BannerIndicator(Context context) {
        this(context, null);
    }
    public BannerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public BannerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BannerIndicator);
        dashGap = (int) array.getDimension(R.styleable.BannerIndicator_gap, 3);
        slider_width = (int) array.getDimension(R.styleable.BannerIndicator_slider_width, 12);
        slider_height = (int) array.getDimension(R.styleable.BannerIndicator_slider_height, 4);
        sliderAlign = array.getInt(R.styleable.BannerIndicator_slider_align, 1);
        array.recycle();
    }

    public void setUpWidthViewPager(ViewPager viewPager) {
        removeAllViews();
        if (viewPager == null || viewPager.getAdapter() == null || viewPager.getAdapter().getCount() < 2)
            return;
        position = 0;
        this.viewPager = viewPager;
        for (int i = 0; i < 2; i++) {
            BannerItemView imageView = new BannerItemView(getContext(), sliderAlign);
            LayoutParams layoutParams = new LayoutParams(slider_width, slider_height);
            if (i > 0) {
                layoutParams.setMargins(dashGap, 0, 0, 0);
                imageView.setAlpha(0.4f);
            } else {
                layoutParams.setMargins(0, 0, 0, 0);
                imageView.setAlpha(1);
            }
            imageView.setLayoutParams(layoutParams);
            addView(imageView);
            setLarge(0);
        }
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                if (com.example.bookstore.scoll.BannerIndicator.this.position != position % 2) {
                    resetSize(com.example.bookstore.scoll.BannerIndicator.this.position, position % 2);
                    com.example.bookstore.scoll.BannerIndicator.this.position = position % 2;
                }
            }
        });
    }

    private void resetSize(int position, int current) {
        setLarge(current);
        setSmall(position);
    }

    //??????????????????????????????????????????
    private void setLarge(int position) {
        if (getChildAt(position) instanceof BannerItemView) {
            AnimatorSet set = new AnimatorSet();
            ValueAnimator animator = getEnlarge((BannerItemView) getChildAt(position));
            ValueAnimator alpha = ObjectAnimator.ofFloat(getChildAt(position), "alpha", 0.4f, 1f);
            set.play(animator).with(alpha);
            set.setDuration(618);
            set.start();
        }
    }


    //????????????
    private ValueAnimator getEnlarge(BannerItemView roundRectView) {
        return ObjectAnimator.ofFloat(roundRectView,
                "rectWidth",
                0, getOffset(roundRectView));
    }

    //??????????????????????????????????????????????????????
    private int getOffset(BannerItemView bannerItemView) {
        int offsest = 0;
        switch (bannerItemView.getLoaction()) {
            case BannerItemView.CENTER:
                offsest = (slider_width - slider_height) / 2;
                break;
            case BannerItemView.LEFT:
                offsest = slider_width - slider_height;
                break;
            case BannerItemView.RIGHT:
                offsest = slider_width - slider_height;
                break;
        }
        return offsest;
    }

    //????????????
    private ValueAnimator getShrink(BannerItemView roundRectView) {
        return ObjectAnimator.ofFloat(roundRectView,
                "rectWidth",
                getOffset(roundRectView), 0);
    }

    //???????????????????????????????????????
    public void setSmall(int small) {
        if (getChildAt(small) instanceof BannerItemView) {
            AnimatorSet set = new AnimatorSet();
            ValueAnimator alpha = ObjectAnimator.ofFloat(getChildAt(position), "alpha", 1, 0.4f);
            ValueAnimator animator = getShrink((BannerItemView) getChildAt(small));
            set.play(animator).with(alpha);
            set.setDuration(618);
            set.start();
        }
    }
}