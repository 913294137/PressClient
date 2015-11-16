package com.sumu.pressclient.bean;

import java.util.ArrayList;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   16:20
 * <p/>
 * 描述：
 * <p/>侧边栏数据对象
 * ==============================
 */
public class NewsMenuData {
    private int id;
    private String title;
    private int type;
    private String url;
    private String url1;
    private ArrayList<NewsTabData> children;

    @Override
    public String toString() {
        return "NewsMenuData{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", children=" + children +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public ArrayList<NewsTabData> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<NewsTabData> children) {
        this.children = children;
    }
}
