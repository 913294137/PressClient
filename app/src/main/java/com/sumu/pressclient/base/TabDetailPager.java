package com.sumu.pressclient.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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
import com.sumu.pressclient.activity.MewsDetailActivity;
import com.sumu.pressclient.adapter.NewsAdapter;
import com.sumu.pressclient.adapter.TopNewsAdapter;
import com.sumu.pressclient.bean.NewsDetailData;
import com.sumu.pressclient.bean.NewsTabData;
import com.sumu.pressclient.bean.NewsTabDetailData;
import com.sumu.pressclient.bean.TabNewsData;
import com.sumu.pressclient.bean.TopNewsData;
import com.sumu.pressclient.utils.CacehUtils;
import com.sumu.pressclient.utils.SharedPreferencesUtil;
import com.sumu.pressclient.view.RefreshListView;
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
    private RefreshListView lvNewsDetails;
    @ViewInject(R.id.top_title)
    private TextView topTitle;//头条新闻的新闻标题
    @ViewInject(R.id.top_indicator)
    private CirclePageIndicator topIndicator;//头条新闻位置指示器
    private TopNewsAdapter topNewsAdapter;
    private ArrayList<TopNewsData> topNewsDatas;//头条新闻
    private ArrayList<TabNewsData> tabNewsDatas;//新闻数据合
    private NewsAdapter newsAdapter;//新闻数据适配器
    private NewsDetailData newsDetailData;
    private String url;
    private String moreUrl;
    private Handler mHandler;
    private boolean isFirst=true;//是否是第一次加载，以防轮播条自动滑动速度叠加

    public TabDetailPager(Activity activity, NewsTabData newsTabData) {
        super(activity);
        this.newsTabData = newsTabData;

    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.tab_detail_pager, null);
        // 加载头布局
        View topView = View.inflate(mActivity, R.layout.top_news, null);
        ViewUtils.inject(this, view);
        ViewUtils.inject(this, topView);
        // 将头条新闻以头布局的形式加给listview
        lvNewsDetails.addHeaderView(topView);
        lvNewsDetails.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String result=CacehUtils.getCache(mActivity,url,"");
                if (!TextUtils.isEmpty(result)){
                    parseData(result,true);
                }
                getDataFromServer();
            }

            @Override
            public void onLoadMore() {
                String result=CacehUtils.getCache(mActivity,moreUrl,"");
                if (!TextUtils.isEmpty(result)){
                    parseData(result,false);
                }
                getMoreDataFromServer();
            }
        });
        lvNewsDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ids = SharedPreferencesUtil.getString(mActivity, "ids", "");
                String newIds = tabNewsDatas.get(position).getId() + ",";
                if (!ids.contains(newIds)) {
                    SharedPreferencesUtil.putString(mActivity, "ids", ids + newIds);//记录被点击的新闻id
                    TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                    tvTitle.setTextColor(Color.GRAY);//被点击后刷新局部字体颜色
                }
                Intent intent = new Intent(mActivity, MewsDetailActivity.class);
                intent.putExtra("url", Contants.SERVER_URL + tabNewsDatas.get(position).getUrl());
                mActivity.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        url = Contants.SERVER_URL + newsTabData.getUrl();
        String result=CacehUtils.getCache(mActivity,url,"");
        if (!TextUtils.isEmpty(result)){
            parseData(result, true);
            System.out.println("-----读取缓存数据------>");
        }
        getDataFromServer();
        if (isFirst){
            isFirst=false;
            roundBar();
        }
    }

    /**
     * 加载更多数据
     */
    private void getMoreDataFromServer() {
        moreUrl = newsDetailData.getMore();
        if (!TextUtils.isEmpty(moreUrl)) {
            moreUrl = Contants.SERVER_URL + moreUrl;
            HttpUtils httpUtils = new HttpUtils();
            httpUtils.send(HttpRequest.HttpMethod.GET, moreUrl, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    parseData(responseInfo.result,false);
                    lvNewsDetails.onRefreshComplete(true);
                    //将数据进行缓存
                    CacehUtils.setCache(mActivity, moreUrl, responseInfo.result);
                }

                @Override
                public void onFailure(HttpException error, String msg) {
                    Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
                            .show();
                    error.printStackTrace();
                    lvNewsDetails.onRefreshComplete(false);
                }
            });
        }else {
            Toast.makeText(mActivity,"最后一页",Toast.LENGTH_SHORT).show();
            lvNewsDetails.onRefreshComplete(true);
        }
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                parseData(responseInfo.result, true);
                lvNewsDetails.onRefreshComplete(true);
                //将数据进行缓存
                CacehUtils.setCache(mActivity, url, responseInfo.result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
                        .show();
                error.printStackTrace();
                lvNewsDetails.onRefreshComplete(false);
            }
        });
    }

    /**
     * 解析网络数据
     * @param result
     * @param isFirst 是否是加载第一页
     */
    private void parseData(String result,boolean isFirst) {
        Gson gson = new Gson();
        NewsTabDetailData newsTabDetailData = gson.fromJson(result, NewsTabDetailData.class);
        //获取新闻详情数据
        newsDetailData = newsTabDetailData.getData();
        if (isFirst){
            topNewsDatas = newsDetailData.getTopnews();
            tabNewsDatas = newsDetailData.getNews();
            if (tabNewsDatas != null) {
                newsAdapter = new NewsAdapter(mActivity, tabNewsDatas);
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
        }else {
            tabNewsDatas.addAll(newsDetailData.getNews());
            newsAdapter.notifyDataSetChanged();
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

    /**
     *  自动轮播条显示
     */
    private void roundBar() {
        if (mHandler==null){
            mHandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    int currentItem = vpTopNews.getCurrentItem();
                    if (currentItem!=(topNewsDatas.size()-1)){
                        currentItem++;
                    }else {
                        currentItem=0;
                    }
                    vpTopNews.setCurrentItem(currentItem);
                    mHandler.sendEmptyMessageDelayed(0,3000);
                }
            };
        }
        mHandler.sendEmptyMessageDelayed(0,3000);
    }
}
