package com.sumu.pressclient.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.sumu.pressclient.base.BasePager;

import java.util.List;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   12:34
 * <p/>
 * 描述：
 * 主内容页导航页ViewPage适配器
 * <p/>
 * ==============================
 */
public class ContentAdapter extends PagerAdapter {
    private List<BasePager> basePagers;

    public ContentAdapter(List<BasePager> basePagers) {
        this.basePagers = basePagers;
    }

    @Override
    public int getCount() {
        return basePagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager basePager = basePagers.get(position);
        container.addView(basePager.baseView);
        // basePager.initData();// 初始化数据.... 不要放在此处初始化数据, 否则会预加载下一个页面
        return basePager.baseView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
