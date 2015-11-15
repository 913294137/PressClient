package com.sumu.pressclient.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sumu.pressclient.R;
import com.sumu.pressclient.fragment.ContentFragment;
import com.sumu.pressclient.fragment.LeftMenuFragment;
import com.sumu.pressclient.slidingmenu.SlidingMenu;
import com.sumu.pressclient.slidingmenu.app.SlidingFragmentActivity;

/**
 * 主页面
 */
public class MainActivity extends SlidingFragmentActivity {

    private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
    private static final String FRAGMENT_CONTENT = "fragment_content";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set the content view
        setBehindContentView(R.layout.left_menu);//设置侧边栏
        // configure the SlidingMenu
        SlidingMenu menu = getSlidingMenu();
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置全屏触摸
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//设置预留屏幕的宽度
        initFragment();
    }

    /**
     * 初始化fragment, 将fragment数据填充给布局文件
     */
    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_left_meun,new LeftMenuFragment(),FRAGMENT_LEFT_MENU);
        transaction.replace(R.id.fl_content,new ContentFragment(),FRAGMENT_CONTENT);
        transaction.commit();
    }
}
