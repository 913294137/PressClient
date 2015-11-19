package com.sumu.pressclient.bean;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/19   12:51
 * <p/>
 * 描述：
 * <p/>     图片数据对象
 * ==============================
 */
public class PhotoInfo {
    private String id;
    private String listimage;
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

    public String getListimage() {
        return listimage;
    }

    public void setListimage(String listimage) {
        this.listimage = listimage;
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
}
