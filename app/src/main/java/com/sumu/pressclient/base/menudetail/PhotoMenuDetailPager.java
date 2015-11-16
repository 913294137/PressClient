package com.sumu.pressclient.base.menudetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.sumu.pressclient.base.BaseMenuDetailPager;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   19:21
 * <p/>
 * 描述：
 * <p/>     侧滑菜单详情页--组图
 * ==============================
 */
public class PhotoMenuDetailPager extends BaseMenuDetailPager {
    public PhotoMenuDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView textView=new TextView(mActivity);
        textView.setText("组图");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {

    }
}
