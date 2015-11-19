package com.sumu.pressclient.base.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.sumu.pressclient.Contants;
import com.sumu.pressclient.activity.MainActivity;
import com.sumu.pressclient.base.BaseMenuDetailPager;
import com.sumu.pressclient.base.BasePager;
import com.sumu.pressclient.base.menudetail.InteractMenuDetailPager;
import com.sumu.pressclient.base.menudetail.NewsMenuDetailPager;
import com.sumu.pressclient.base.menudetail.PhotoMenuDetailPager;
import com.sumu.pressclient.base.menudetail.TopicMenuDetailPager;
import com.sumu.pressclient.bean.NewsData;
import com.sumu.pressclient.fragment.LeftMenuFragment;
import com.sumu.pressclient.utils.CacehUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   13:10
 * <p/>
 * 描述：
 * <p/>    新闻中心
 * ==============================
 */
public class NewsCenterPager extends BasePager {
    private List<BaseMenuDetailPager> baseMenuDetailPagers;
    private NewsData newsData;

    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        baseMenu.setVisibility(View.VISIBLE);// 显示菜单按钮
        setSlidingMenuEnable(true);//设置侧边栏显示与隐藏
        String result = CacehUtils.getCache(mActivity, Contants.CATEGORIES_URL, "");//读取缓存数据
        if (!TextUtils.isEmpty(result)) {
            parseData(result);//如果不为空则将缓存数据设置到UI中
        }
        getDataFromServer();
        //点击按钮自动开关侧滑菜单
        baseMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mActivity;
                mainActivity.toggle();
            }
        });
    }

    /**
     * 从服务器获取数据
     */
    public void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, Contants.CATEGORIES_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                parseData(responseInfo.result);
                //获取数据成功后，将数据缓存
                CacehUtils.setCache(mActivity, Contants.CATEGORIES_URL, responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mActivity, s, Toast.LENGTH_SHORT)
                        .show();
                e.printStackTrace();
            }
        });
    }

    /**
     * 解析网络数据
     *
     * @param result
     */
    protected void parseData(String result) {
        Gson gson = new Gson();
        newsData = gson.fromJson(result, NewsData.class);
        MainActivity mainActivity = (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = mainActivity.getLeftMenuFragment();
        leftMenuFragment.setMenuData(newsData.getData());
        //初始化四个侧滑菜单详情页
        baseMenuDetailPagers = new ArrayList<>();
        baseMenuDetailPagers.add(new NewsMenuDetailPager(mActivity, newsData.getData().get(0).getChildren()));
        baseMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
        baseMenuDetailPagers.add(new PhotoMenuDetailPager(mActivity, ibPhoto));
        baseMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));
        setCurrentMenuDetailPager(0);// 设置菜单详情页-新闻为默认当前页
    }

    /**
     * 设置当前菜单详情页
     */
    public void setCurrentMenuDetailPager(int position) {
        BaseMenuDetailPager baseMenuDetailPager = baseMenuDetailPagers.get(position);// 获取当前要显示的菜单详情页
        baseContent.removeAllViews();// 清除之前的布局
        baseContent.addView(baseMenuDetailPager.mRootView);// 将菜单详情页的布局设置给帧布局
        baseTitle.setText(newsData.getData().get(position).getTitle());//设置标题
        baseMenuDetailPager.initData();// 初始化当前页面的数据
        showOrHide(position);
    }

    /**
     * 显示或者隐藏组图界面切换视图布局按钮
     */
    private void showOrHide(int position) {
        if (position == 2) {
            ibPhoto.setVisibility(View.VISIBLE);
        } else {
            ibPhoto.setVisibility(View.INVISIBLE);
        }
    }
}
