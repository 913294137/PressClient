package com.sumu.pressclient.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/15   22:07
 * <p/>
 * 描述：
 *         fragment基类
 * <p/>
 * ==============================
 */
public abstract class BaseFragment extends Fragment{
    public Activity mActivity;

    // fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
    }

    // 处理fragment的布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    // 处理fragment的布局
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    // 子类必须实现初始化布局的方法
    public abstract View initView();

    // 初始化数据, 可以不实现
    public void initData(){

    }
}
