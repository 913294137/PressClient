package com.sumu.pressclient.bean;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   16:20
 * <p/>
 * 描述：
 * <p/>新闻页面下11个子页签的数据对象
 * ==============================
 */
public class NewsTabData {
    private int id;
    private String title;
    private int type;
    private String url;

    @Override
    public String toString() {
        return "NewsTabData{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
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
}
