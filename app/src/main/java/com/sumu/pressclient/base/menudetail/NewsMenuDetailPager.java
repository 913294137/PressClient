package com.sumu.pressclient.base.menudetail;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sumu.pressclient.R;
import com.sumu.pressclient.adapter.MenuDetailAdapter;
import com.sumu.pressclient.base.BaseMenuDetailPager;
import com.sumu.pressclient.base.TabDetailPager;
import com.sumu.pressclient.bean.NewsTabData;

import java.util.ArrayList;
import java.util.List;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   19:21
 * <p/>
 * 描述：
 * <p/>     侧滑菜单详情页--新闻
 * ==============================
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager {
    private List<TabDetailPager> tabDetailPagers;
    @ViewInject(R.id.vp_news_menu_detail)
    private ViewPager vpNewsMenuDetail;
    private  ArrayList<NewsTabData> newsMenuDatas;// 页签网络数据
    public NewsMenuDetailPager(Activity activity, ArrayList<NewsTabData> newsMenuDatas) {
        super(activity);
        this.newsMenuDatas=newsMenuDatas;
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.news_menu_detail, null);
        ViewUtils.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        tabDetailPagers=new ArrayList<>();
        // 初始化页签数据
        for (int i=0;i<newsMenuDatas.size();i++){
            tabDetailPagers.add(new TabDetailPager(mActivity,newsMenuDatas.get(i)));
        }
        MenuDetailAdapter menuDetailAdapter=new MenuDetailAdapter(tabDetailPagers);
        vpNewsMenuDetail.setAdapter(menuDetailAdapter);
    }
}
