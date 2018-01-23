package com.kcrason.dynamicpagerindicatorlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

/**
 * @author KCrason
 * @date 2018/1/21
 */
public class ScrollableLine extends View {

    private float mIndicatorLineRadius;

    private int mIndicatorLineHeight;

    private RectF mRectF;

    private Paint mPaint;

    private float mIndicatorStartX;

    private float mIndicatorEndX;


    public ScrollableLine(Context context) {
        super(context);
        initScrollableLine(context);
    }

    public ScrollableLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initScrollableLine(context);
    }

    public ScrollableLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initScrollableLine(context);
    }

    private void initScrollableLine(Context context) {
        mPaint = new Paint();
        mRectF = new RectF();
    }

    /**
     * 设置导航条的高度
     * @param indicatorLineHeight
     */
    public void setIndicatorLineHeight(int indicatorLineHeight) {
        mIndicatorLineHeight = indicatorLineHeight;
    }


    /**
     * 设置导航条的圆角
     * @param indicatorLineRadius
     * @return
     */
    public ScrollableLine setIndicatorLineRadius(float indicatorLineRadius) {
        mIndicatorLineRadius = indicatorLineRadius;
        return this;
    }

    public void updateScrollLineWidth(float indicatorStartX, float indicatorEndX, int indicatorStartColor, int indicatorEndColor, float fraction) {
        this.mIndicatorStartX = indicatorStartX;
        this.mIndicatorEndX = indicatorEndX;
        mPaint.setColor(evaluateColor(indicatorStartColor, indicatorEndColor, fraction));
        invalidate();
    }

    /**
     * 颜色渐变，需要把ARGB分别拆开进行渐变
     */
    private int evaluateColor(int startValue, int endValue, float fraction) {
        if (fraction <= 0) {
            return startValue;
        }
        if (fraction >= 1) {
            return endValue;
        }
        int startInt = startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return ((startA + (int) (fraction * (endA - startA))) << 24)
                | ((startR + (int) (fraction * (endR - startR))) << 16)
                | ((startG + (int) (fraction * (endG - startG))) << 8)
                | ((startB + (int) (fraction * (endB - startB))));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectF.set(mIndicatorStartX, 0, mIndicatorEndX, mIndicatorLineHeight);
        canvas.drawRoundRect(mRectF, mIndicatorLineRadius, mIndicatorLineRadius, mPaint);
    }
}
