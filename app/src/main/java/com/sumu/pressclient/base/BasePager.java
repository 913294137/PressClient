package com.sumu.pressclient.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sumu.pressclient.R;
import com.sumu.pressclient.activity.MainActivity;
import com.sumu.pressclient.slidingmenu.SlidingMenu;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   12:39
 * <p/>
 * 描述：
 * <p/>  主页下5个子页面的基类
 * ==============================
 */
public class BasePager {
    public Activity mActivity;
    public View baseView;
    public TextView baseTitle;
    public ImageButton baseMenu;
    public FrameLayout baseContent;

    public BasePager(Activity activity) {
        mActivity = activity;
        initView();
    }

    /**
     * 初始化布局
     */
    public void initView() {
        baseView = View.inflate(mActivity, R.layout.base_pager, null);
        baseTitle = (TextView) baseView.findViewById(R.id.base_title);
        baseMenu = (ImageButton) baseView.findViewById(R.id.base_menu);
        baseContent= (FrameLayout) baseView.findViewById(R.id.base_content);
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 设置侧边栏开启或关闭
     *
     * @param enable
     */
    public void setSlidingMenuEnable(boolean enable) {
        MainActivity mainActivity= (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
        if (enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}
