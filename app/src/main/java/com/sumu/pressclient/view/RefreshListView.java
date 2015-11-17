package com.sumu.pressclient.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.sumu.pressclient.R;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/17   20:29
 * <p/>
 * 描述：
 * <p/>下拉刷新的ListView
 * ==============================
 */
public class RefreshListView extends ListView{
    public RefreshListView(Context context) {
        super(context);
        initHeaderView();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderView();
    }

    /**
     * 初始化头布局
     */
    private void initHeaderView(){
        View mHeaderView=View.inflate(getContext(), R.layout.refresh_header, null);
        addHeaderView(mHeaderView);//添加头布局
        mHeaderView.measure(0,0);//测量头布局
        int measuredHeight = mHeaderView.getMeasuredHeight();//获得头布局的高
        mHeaderView.setPadding(0,-measuredHeight,0,0);//设置头部的的上内边距，隐藏头布局
    }

}
