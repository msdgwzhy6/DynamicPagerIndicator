package com.kcrason.dynamicpagerindicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;

import com.kcrason.dynamicpagerindicatorlibrary.DynamicPagerIndicator;

/**
 * @author KCrason
 * @date 2018/1/23
 */
public class CustomPagerIndicator extends DynamicPagerIndicator {

    public CustomPagerIndicator(Context context) {
        super(context);
    }

    public CustomPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void createTabView(PagerAdapter pagerAdapter, final int position) {
        CustomPagerTabView customPagerTabView = new CustomPagerTabView(mContext);
        setTabTitleTextView(customPagerTabView.getTitleTextView(), position, pagerAdapter);
        setTabViewLayoutParams(customPagerTabView, position);
    }
}
