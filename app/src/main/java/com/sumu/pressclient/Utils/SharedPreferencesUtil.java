package com.sumu.pressclient.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/15   18:54
 * <p/>
 * 描述：
 * SharedPreferences封装工具
 * <p/>
 * ==============================
 */
public class SharedPreferencesUtil {
    public static final String SHAREDPREFERENCES_NAME = "config";

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_APPEND);
        return preferences.getBoolean(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_APPEND);
        preferences.edit().putBoolean(key, value).commit();
    }
}
