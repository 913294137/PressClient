package com.sumu.pressclient.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/15   16:15
 * <p/>
 * 描述：
 *      引导页导航页ViewPage适配器
 * <p/>
 * ==============================
 */
public class GuideAdapter extends PagerAdapter {
    private List<ImageView> guidePages;

    public GuideAdapter(List<ImageView> guidePages) {
        this.guidePages = guidePages;
    }

    @Override
    public int getCount() {
        return guidePages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView guidePage = guidePages.get(position);
        container.addView(guidePage);
        return guidePage;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(guidePages.get(position));
    }
}
