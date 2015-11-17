package com.sumu.pressclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.sumu.pressclient.R;
import com.sumu.pressclient.bean.TabNewsData;

import java.util.List;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/17   19:01
 * <p/>
 * 描述：
 * <p/>  新闻列表的适配器
 * ==============================
 */
public class NewsAdapter extends BaseAdapter {
    private List<TabNewsData> tabNewsDatas;//新闻数据合
    private Context context;
    private BitmapUtils bitmapUtils;

    public NewsAdapter(Context context, List<TabNewsData> tabNewsDatas) {
        this.context = context;
        this.tabNewsDatas = tabNewsDatas;
        bitmapUtils = new BitmapUtils(context);
        bitmapUtils.configDefaultLoadingImage(R.drawable.pic_item_list_default);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.pic_item_list_default);
    }

    @Override
    public int getCount() {
        return tabNewsDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return tabNewsDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item_news_details, null);
            viewHolder = new ViewHolder();
            viewHolder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TabNewsData tabNewsData = (TabNewsData) getItem(position);
        bitmapUtils.display(viewHolder.ivPic, tabNewsData.getListimage());
        viewHolder.tvTitle.setText(tabNewsData.getTitle());
        viewHolder.tvTime.setText(tabNewsData.getPubdate());
        return convertView;
    }

    static class ViewHolder {
        ImageView ivPic;
        TextView tvTitle;
        TextView tvTime;
    }
}
