package com.sumu.pressclient.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/17   12:32
 * <p/>
 * 描述：
 * <p/>
 * ==============================
 */
public class HorizontalViewpager extends ViewPager {
    public HorizontalViewpager(Context context) {
        super(context);
    }

    public HorizontalViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**
     * 事件分发, 请求父控件及祖宗控件是否拦截事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentItem()!=0) {// 用getParent去请求
            getParent().requestDisallowInterceptTouchEvent(true);//父控件不拦截
        }else {// 如果是第一个页面,需要显示侧边栏, 请求父控件拦截
            getParent().requestDisallowInterceptTouchEvent(false);//父空间拦截
        }
        return super.dispatchTouchEvent(ev);
    }
}
