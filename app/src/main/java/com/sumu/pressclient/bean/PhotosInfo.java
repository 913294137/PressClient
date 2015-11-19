package com.sumu.pressclient.bean;

import java.util.ArrayList;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/19   12:52
 * <p/>
 * 描述：
 * <p/>     图片集数据对象
 * ==============================
 */
public class PhotosInfo {
    private String title;
    private ArrayList<PhotoInfo> news;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<PhotoInfo> getNews() {
        return news;
    }

    public void setNews(ArrayList<PhotoInfo> news) {
        this.news = news;
    }
}
