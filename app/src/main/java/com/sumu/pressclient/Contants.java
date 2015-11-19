package com.sumu.pressclient;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   15:29
 * <p/>
 * 描述：
 * <p/>定义全局参数
 * ==============================
 */
public class Contants {
    //192.168.0.101
    public static final String SERVER_URL = "http://10.0.2.2:8080/zhbj";
    public static final String CATEGORIES_URL = SERVER_URL + "/categories.json";// 获取分类信息的接口
    public static final String PHOTOS_URL = SERVER_URL + "/photos/photos_1.json";// 获取组图数据的接口
}
