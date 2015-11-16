package com.sumu.pressclient.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   13:55
 * <p/>
 * 描述：
 * <p/> 禁止滑动的ViewPager
 * ==============================
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 表示事件是否拦截, 返回false表示不拦截
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    /**
     * 重写onTouchEvent事件,什么都不用做
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
