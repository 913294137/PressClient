package com.sumu.pressclient.bean;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/17   14:23
 * <p/>
 * 描述：
 * <p/>头条新闻
 * ==============================
 */
public class TopNewsData {
    private String id;
    private String topimage;
    private String pubdate;
    private String title;
    private String type;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopimage() {
        return topimage;
    }

    public void setTopimage(String topimage) {
        this.topimage = topimage;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TopNewsData{" +
                "title='" + title + '\'' +
                ", pubdate='" + pubdate + '\'' +
                '}';
    }
}
