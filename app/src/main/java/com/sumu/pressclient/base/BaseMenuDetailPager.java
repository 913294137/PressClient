package com.sumu.pressclient.base;

import android.app.Activity;
import android.view.View;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   18:52
 * <p/>
 * 描述：
 * <p/>菜单详情页基类
 * ==============================
 */
public abstract class BaseMenuDetailPager {
    public Activity mActivity;
    public View mRootView;// 根布局对象

    public BaseMenuDetailPager(Activity activity) {
        mActivity = activity;
        mRootView = initView();
    }

    /**
     * 初始化界面
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public void initData() {

    }
}
