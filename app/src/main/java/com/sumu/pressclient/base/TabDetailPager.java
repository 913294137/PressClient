package com.sumu.pressclient.base;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.sumu.pressclient.bean.NewsTabData;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   20:51
 * <p/>
 * 描述：
 * <p/>     页签详情页
 * ==============================
 */
public class TabDetailPager extends BaseMenuDetailPager {
    private NewsTabData newsTabData;
    private TextView textView;

    public TabDetailPager(Activity activity, NewsTabData newsTabData) {
        super(activity);
        this.newsTabData=newsTabData;
    }

    @Override
    public View initView() {
        textView = new TextView(mActivity);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText(newsTabData.getTitle());
    }
}
