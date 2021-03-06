package com.sumu.pressclient.utils;

import android.content.Context;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/15   17:47
 * <p/>
 * 描述：
 *        dp与px之间的转化
 * <p/>
 * ==============================
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);//加上0.5f 为了四舍五入
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
