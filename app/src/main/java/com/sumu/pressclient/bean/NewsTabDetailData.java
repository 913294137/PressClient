package com.sumu.pressclient.bean;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/17   14:06
 * <p/>
 * 描述：
 * <p/>页签详情页数据
 * ==============================
 */
public class NewsTabDetailData {
    private int retcode;
    private NewsDetailData data;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public NewsDetailData getData() {
        return data;
    }

    public void setData(NewsDetailData data) {
        this.data = data;
    }
}
