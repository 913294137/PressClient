package com.sumu.pressclient.base;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sumu.pressclient.Contants;
import com.sumu.pressclient.R;
import com.sumu.pressclient.adapter.NewsAdapter;
import com.sumu.pressclient.adapter.TopNewsAdapter;
import com.sumu.pressclient.bean.NewsDetailData;
import com.sumu.pressclient.bean.NewsTabData;
import com.sumu.pressclient.bean.NewsTabDetailData;
import com.sumu.pressclient.bean.TabNewsData;
import com.sumu.pressclient.bean.TopNewsData;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

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
    public NewsTabData newsTabData;//新闻子标签页下的所有数据
    @ViewInject(R.id.vp_top_news)
    private ViewPager vpTopNews;
    @ViewInject(R.id.lv_news_details)
    private ListView lvNewsDetails;
    @ViewInject(R.id.top_title)
    private TextView topTitle;//头条新闻的新闻标题
    @ViewInject(R.id.top_indicator)
    private CirclePageIndicator topIndicator;//头条新闻位置指示器
    private TopNewsAdapter topNewsAdapter;
    private ArrayList<TopNewsData> topNewsDatas;//头条新闻
    private ArrayList<TabNewsData> tabNewsDatas;//新闻数据合
    private NewsAdapter newsAdapter;//新闻数据适配器

    public TabDetailPager(Activity activity, NewsTabData newsTabData) {
        super(activity);
        this.newsTabData = newsTabData;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.tab_detail_pager, null);
        // 加载头布局
        View topView=View.inflate(mActivity,R.layout.top_news,null);
        ViewUtils.inject(this, view);
        ViewUtils.inject(this, topView);
        // 将头条新闻以头布局的形式加给listview
        lvNewsDetails.addHeaderView(topView);
        return view;
    }

    @Override
    public void initData() {
        String url = Contants.SERVER_URL + newsTabData.getUrl();
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                parseData(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
                        .show();
                error.printStackTrace();
            }
        });
    }

    /**
     * 解析网络数据
     *
     * @param result
     */
    private void parseData(String result) {
        Gson gson = new Gson();
        NewsTabDetailData newsTabDetailData = gson.fromJson(result, NewsTabDetailData.class);
        //获取新闻详情数据
        NewsDetailData newsDetailData = newsTabDetailData.getData();
        topNewsDatas = newsDetailData.getTopnews();
        tabNewsDatas = newsDetailData.getNews();
        if (tabNewsDatas!=null){
            newsAdapter =new NewsAdapter(mActivity,tabNewsDatas);
            lvNewsDetails.setAdapter(newsAdapter);
        }
        if (topNewsDatas != null) {
            //手动设置第一页的标题
            topTitle.setText(topNewsDatas.get(0).getTitle());
            topNewsAdapter = new TopNewsAdapter(mActivity, topNewsDatas);
            vpTopNews.setAdapter(topNewsAdapter);
            topIndicator.setViewPager(vpTopNews);
            topIndicator.setSnap(true);// 支持快照显示
            topIndicator.onPageSelected(0);// 让指示器重新定位到第一个点
        }
        vpTopNews.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                topTitle.setText(topNewsDatas.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
