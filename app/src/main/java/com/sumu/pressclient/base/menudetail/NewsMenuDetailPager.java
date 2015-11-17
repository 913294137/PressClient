package com.sumu.pressclient.base.menudetail;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sumu.pressclient.R;
import com.sumu.pressclient.activity.MainActivity;
import com.sumu.pressclient.adapter.MenuDetailAdapter;
import com.sumu.pressclient.base.BaseMenuDetailPager;
import com.sumu.pressclient.base.TabDetailPager;
import com.sumu.pressclient.bean.NewsTabData;
import com.sumu.pressclient.slidingmenu.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

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
public class NewsMenuDetailPager extends BaseMenuDetailPager{
    private List<TabDetailPager> tabDetailPagers;
    @ViewInject(R.id.vp_news_menu_detail)
    private ViewPager vpNewsMenuDetail;
    @ViewInject(R.id.indicator)
    private TabPageIndicator mIndicator;
    private  ArrayList<NewsTabData> newsMenuDatas;// 页签网络数据
    public NewsMenuDetailPager(Activity activity, ArrayList<NewsTabData> newsMenuDatas) {
        super(activity);
        this.newsMenuDatas=newsMenuDatas;
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.news_menu_detail, null);
        ViewUtils.inject(this, view);
        return view;
    }
    // 跳转下一个页面
    @OnClick(R.id.next_page)
    public void NextPage(View view){
        int currentItem = vpNewsMenuDetail.getCurrentItem();
        vpNewsMenuDetail.setCurrentItem(currentItem+1);
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
        mIndicator.setViewPager(vpNewsMenuDetail);// 将viewpager和mIndicator关联起来,必须在viewpager设置完adapter后才能调用
        // 滑动监听需要设置给Indicator而不是viewpager
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MainActivity mainActivity= (MainActivity) mActivity;
                SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
                if (position==0){//只有在第一个页面(北京), 侧边栏才允许出来
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                }else {
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
