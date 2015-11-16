package com.sumu.pressclient.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.sumu.pressclient.base.BasePager;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   13:10
 * <p/>
 * 描述：
 * <p/>    设置
 * ==============================
 */
public class SettingPager extends BasePager {
    public SettingPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        baseTitle.setText("设置");// 修改标题
        baseMenu.setVisibility(View.INVISIBLE);// 隐藏菜单按钮
        TextView textView=new TextView(mActivity);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setText("设置");
        baseContent.addView(textView);// 向FrameLayout中动态添加布局
        setSlidingMenuEnable(false);//设置侧边栏显示与隐藏
    }
}
