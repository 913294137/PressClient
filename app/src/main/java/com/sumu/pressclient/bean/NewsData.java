package com.sumu.pressclient.bean;

import java.util.ArrayList;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   15:16
 * <p/>
 * 描述：
 * <p/>网络分类信息的封装
 * <p/>字段名字必须和服务器返回的字段名一致, 方便gson解析
 * ==============================
 */
public class NewsData {
    private int retcode;
    private ArrayList<NewsMenuData> data;

    @Override
    public String toString() {
        return "NewsData{" +
                "retcode=" + retcode +
                ", data=" + data +
                '}';
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public ArrayList<NewsMenuData> getData() {
        return data;
    }

    public void setData(ArrayList<NewsMenuData> data) {
        this.data = data;
    }
}
