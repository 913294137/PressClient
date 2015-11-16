package com.sumu.pressclient.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sumu.pressclient.R;
import com.sumu.pressclient.adapter.ContentAdapter;
import com.sumu.pressclient.base.BasePager;
import com.sumu.pressclient.base.impl.GovAffairsPager;
import com.sumu.pressclient.base.impl.HomePager;
import com.sumu.pressclient.base.impl.NewsCenterPager;
import com.sumu.pressclient.base.impl.SettingPager;
import com.sumu.pressclient.base.impl.SmartServicePager;

import java.util.ArrayList;
import java.util.List;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/15   22:18
 * <p/>
 * 描述：
 * 主页内容
 * <p/>
 * ==============================
 */
public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.vp_content)
    private ViewPager vpContent;
    private ContentAdapter contentAdapter;
    private List<BasePager> basePagers;
    @ViewInject(R.id.rg_bottom_tab)
    private RadioGroup rgBottomTab;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        ViewUtils.inject(this, view);// 注入view和事件
        return view;
    }

    @Override
    public void initData() {
        rgBottomTab.check(R.id.rb_home);// 默认勾选首页
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(mActivity));
        basePagers.add(new NewsCenterPager(mActivity));
        basePagers.add(new SmartServicePager(mActivity));
        basePagers.add(new GovAffairsPager(mActivity));
        basePagers.add(new SettingPager(mActivity));
        contentAdapter = new ContentAdapter(basePagers);
        vpContent.setAdapter(contentAdapter);
        rgBottomTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vpContent.setCurrentItem(0, false);//设置按钮点击切换页面,并且关闭切换动画
                        break;
                    case R.id.rb_news_center:
                        vpContent.setCurrentItem(1, false);
                        break;
                    case R.id.rb_smart_service:
                        vpContent.setCurrentItem(2, false);
                        break;
                    case R.id.rb_govaffairs:
                        vpContent.setCurrentItem(3, false);
                        break;
                    case R.id.rb_setting:
                        vpContent.setCurrentItem(4, false);
                        break;
                }
            }
        });
        basePagers.get(0).initData();// 初始化首页数据
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                basePagers.get(position).initData();// 获取当前被选中的页面, 初始化该页面数据
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 获取ViewPager中的页面
     * @param postion 在ViewPager中的位置
     * @return
     */
    public BasePager getBasePager(int postion){
        return basePagers.get(postion);
    }
}
