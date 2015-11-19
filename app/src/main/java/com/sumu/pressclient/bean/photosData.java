package com.sumu.pressclient.bean;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/19   12:47
 * <p/>
 * 描述：
 * <p/>  组图数据
 * ==============================
 */
public class PhotosData {
    private int retcode;
    private PhotosInfo data;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public PhotosInfo getData() {
        return data;
    }

    public void setData(PhotosInfo data) {
        this.data = data;
    }
}
