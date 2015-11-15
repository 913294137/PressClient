package com.sumu.pressclient.fragment;

import android.view.View;

import com.sumu.pressclient.R;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/15   22:17
 * <p/>
 * 描述：
 *       侧边栏
 * <p/>
 * ==============================
 */
public class LeftMenuFragment extends BaseFragment{
    @Override
    public View initView() {
        return View.inflate(mActivity, R.layout.fragment_left_menu,null);
    }
}
