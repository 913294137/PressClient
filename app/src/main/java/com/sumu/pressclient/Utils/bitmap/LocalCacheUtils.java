package com.sumu.pressclient.utils.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.sumu.pressclient.utils.MD5Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * ==============================
 * 作者：苏幕
 * <p>
 * 时间：2015/11/19   17:13
 * <p>
 * 描述：
 * <p>本地缓存
 * ==============================
 */
public class LocalCacheUtils {
    public static final String CACHE_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/image";

    /**
     * 从本地sdcard读图片
     */
    public Bitmap getBitmapFromLocal(String url) {
        try {
            String fileName = MD5Encoder.encode(url);
            File file = new File(CACHE_PATH, fileName);

            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(
                        file));
                return bitmap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 向sdcard写图片
     *
     * @param url
     * @param bitmap
     */
    public void setBitmapToLocal(String url, Bitmap bitmap) {
        try {
            String fileName = MD5Encoder.encode(url);

            File file = new File(CACHE_PATH, fileName);

            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {// 如果文件夹不存在, 创建文件夹
                parentFile.mkdirs();
            }

            // 将图片保存在本地
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
