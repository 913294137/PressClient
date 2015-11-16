package com.sumu.pressclient.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.sumu.pressclient.base.TabDetailPager;

import java.util.List;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   20:54
 * <p/>
 * 描述：
 * <p/>
 * ==============================
 */
public class MenuDetailAdapter extends PagerAdapter {
    private List<TabDetailPager> tabDetailPagers;
    public MenuDetailAdapter(List<TabDetailPager> tabDetailPagers) {
        this.tabDetailPagers=tabDetailPagers;
    }

    @Override
    public int getCount() {
        return tabDetailPagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TabDetailPager tabDetailPager=tabDetailPagers.get(position);
        View view=tabDetailPager.mRootView;
        container.addView(view);
        tabDetailPager.initData();//初始化页面数据
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
