package com.sumu.pressclient.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sumu.pressclient.R;
import com.sumu.pressclient.activity.MainActivity;
import com.sumu.pressclient.adapter.LeftMenuAdapter;
import com.sumu.pressclient.base.impl.NewsCenterPager;
import com.sumu.pressclient.bean.NewsMenuData;

import java.util.ArrayList;

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
    @ViewInject(R.id.list_view_menu)
    private ListView listViewMenu;
    @Override
    public View initView() {
        View view=View.inflate(mActivity, R.layout.fragment_left_menu,null);
        ViewUtils.inject(this,view);
        return view;
    }

    /**
     * 设置网络数据
     */
    public void setMenuData(ArrayList<NewsMenuData> newsMenuDatas){
        System.out.println("--------->"+newsMenuDatas);
        final LeftMenuAdapter leftMenuAdapter=new LeftMenuAdapter(mActivity,newsMenuDatas,0);
        listViewMenu.setAdapter(leftMenuAdapter);//初始化侧滑菜单的ListView
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity mainActivity= (MainActivity) mActivity;
                ContentFragment contentFragment = mainActivity.getContentFragment();// 获取主页面fragment
                NewsCenterPager newsCenterPager= (NewsCenterPager) contentFragment.getBasePager(1);// 获取新闻中心页面
                newsCenterPager.setCurrentMenuDetailPager(position);// 设置当前菜单详情页
                leftMenuAdapter.setmCurrentPos(position);// 当前被点击的菜单项
                mainActivity.toggle();//关闭侧滑菜单
            }
        });
    }
}
