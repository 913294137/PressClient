package com.sumu.pressclient.utils;

import android.content.Context;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/18   21:57
 * <p/>
 * 描述：
 * <p/>数据缓存工具
 * ==============================
 */
public class CacehUtils {
    /**
     * 设置缓存
     *
     * @param context
     * @param key     是url
     * @param value   json数据
     */
    public static void setCache(Context context, String key, String value) {
        SharedPreferencesUtil.putString(context, key, value);
        //可以将缓存放在文件中, 文件名就是Md5(url), 文件内容是json
    }

    /**
     * 读取缓存
     * @param context
     * @param key    url
     * @param defaultValue   json 数据
     * @return
     */
    public static String getCache(Context context, String key, String defaultValue) {
        return SharedPreferencesUtil.getString(context, key, defaultValue);
    }
}
