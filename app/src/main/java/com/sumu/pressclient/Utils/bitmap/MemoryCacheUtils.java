package com.sumu.pressclient.utils.bitmap;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * ==============================
 * 作者：苏幕
 * <p>
 * 时间：2015/11/19   19:11
 * <p>
 * 描述：
 * <p>内存缓存
 * ==============================
 */
public class MemoryCacheUtils {
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCacheUtils() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 8;// 模拟器默认是16M
        mMemoryCache = new LruCache<String, Bitmap>((int) maxMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
             //   int byteCount = value.getRowBytes() * value.getHeight();// 获取图片占用内存大小
                int byteCount = value.getByteCount();// 获取图片占用内存大小
                return byteCount;
            }
        };
    }

    /**
     * 从内存读
     *
     * @param url
     */
    public Bitmap getBitmapFromMemory(String url) {
        return mMemoryCache.get(url);
    }

    /**
     * 写内存
     *
     * @param url
     * @param bitmap
     */
    public void setBitmapToMemory(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
    }
}
