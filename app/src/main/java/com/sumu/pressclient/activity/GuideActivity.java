package com.sumu.pressclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sumu.pressclient.R;
import com.sumu.pressclient.utils.DensityUtil;
import com.sumu.pressclient.utils.SharedPreferencesUtil;
import com.sumu.pressclient.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 */
public class GuideActivity extends Activity {
    private static final int[] imageIds = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private GuideAdapter guideAdapter;
    private List<ImageView> guidePages;
    private ViewPager vpGuide;
    private Button btnGuide;
    private LinearLayout llPonitGroup;
    private View pointRed;
    private int mPointWidth;//圆点之间的距离
    private int redLeftMargin;//红点的做边距
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        //获取树视图，对layout()结束事件进行监听
        llPonitGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //因为会多次回调，所有让他只执行一次
                llPonitGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //通过第二个点的左边距-第一个点的做边距获得两点之间的距离
                mPointWidth = llPonitGroup.getChildAt(1).getLeft() - llPonitGroup.getChildAt(0).getLeft();
            }
        });
        /**
         * 跳转到主页面
         */
        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtil.putBoolean(GuideActivity.this,"guide_showed",true);
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        btnGuide = (Button) findViewById(R.id.btn_guide);
        llPonitGroup= (LinearLayout) findViewById(R.id.ll_point_group);
        pointRed=findViewById(R.id.point_red);
        guidePages = new ArrayList<>();
        //初始化引导页图片
        for (int i = 0; i < imageIds.length; i++) {
            ImageView guidePage = new ImageView(this);
            guidePage.setImageResource(imageIds[i]);
            guidePages.add(guidePage);
        }
        guideAdapter = new GuideAdapter(guidePages);
        vpGuide.setAdapter(guideAdapter);
        //初始化引导页的小圆点
        for (int i = 0; i < imageIds.length; i++) {
            View pointGray = new View(this);
            pointGray.setBackgroundResource(R.drawable.shape_point_gray);//设置默认的圆点
            //设置宽高
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,10),DensityUtil.dip2px(this,10));
            params.leftMargin=DensityUtil.dip2px(this,10);//设置圆的左边距
            params.rightMargin=DensityUtil.dip2px(this,10);//设置圆的右边距
            llPonitGroup.addView(pointGray,params);
        }
        //可以根据自己设置的参数直接设置两点的距离，也可以动态获取
        //mPointWidth=3*DensityUtil.dip2px(this,10);
        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             *
             * @param position  第几页面
             * @param positionOffset  偏移百分比
             * @param positionOffsetPixels   偏移量
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) pointRed.getLayoutParams();
                if (position==0 && positionOffsetPixels==0) {
                    //获取红点默认所设置的左边距
                    redLeftMargin = params.leftMargin;
                }
                params.leftMargin= (int)(mPointWidth*positionOffset)+redLeftMargin+mPointWidth*position;
                pointRed.setLayoutParams(params);

                if (position==imageIds.length-1){//最后一个页面显示开始体验的按钮
                    btnGuide.setVisibility(View.VISIBLE);
                }else {
                    btnGuide.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
