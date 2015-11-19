package com.sumu.pressclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.sumu.pressclient.Contants;
import com.sumu.pressclient.R;
import com.sumu.pressclient.activity.MewsDetailActivity;
import com.sumu.pressclient.bean.TopNewsData;

import java.util.List;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/17   14:03
 * <p/>
 * 描述：
 * <p/> 头条新闻适配器
 * ==============================
 */
public class TopNewsAdapter extends PagerAdapter {
    private List<TopNewsData> topNewsDatas;//新闻头条数据
    private Context context;
    private BitmapUtils bitmapUtils;
    public TopNewsAdapter(Context context,List<TopNewsData> topNewsDatas) {
        this.context=context;
        this.topNewsDatas=topNewsDatas;
        bitmapUtils=new BitmapUtils(context);
        bitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);// 设置默认图片
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.topnews_item_default);//设置加载失败的图片
    }

    @Override
    public int getCount() {
        return topNewsDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);// 基于控件大小填充图片
        final TopNewsData topNewsData=topNewsDatas.get(position);
        bitmapUtils.display(imageView,Contants.SERVER_URL +topNewsData.getTopimage());// 传递imagView对象和图片地址
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MewsDetailActivity.class);
                intent.putExtra("url", Contants.SERVER_URL +topNewsData.getUrl());
                context.startActivity(intent);
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
