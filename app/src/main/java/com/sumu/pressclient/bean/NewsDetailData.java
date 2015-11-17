package com.sumu.pressclient.bean;

import java.util.ArrayList;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/17   14:22
 * <p/>
 * 描述：
 * <p/> 新闻详情数据
 * ==============================
 */
public class NewsDetailData {
    private String more;
    private String title;
    private ArrayList<TabNewsData> news;
    private ArrayList<TopNewsData> topnews;

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<TabNewsData> getNews() {
        return news;
    }

    public void setNews(ArrayList<TabNewsData> news) {
        this.news = news;
    }

    public ArrayList<TopNewsData> getTopnews() {
        return topnews;
    }

    public void setTopnews(ArrayList<TopNewsData> topnews) {
        this.topnews = topnews;
    }

    @Override
    public String toString() {
        return "NewsDetailData{" +
                "more='" + more + '\'' +
                ", title='" + title + '\'' +
                ", news=" + news +
                ", topnews=" + topnews +
                '}';
    }
}
