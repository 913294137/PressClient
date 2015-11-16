package com.sumu.pressclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sumu.pressclient.R;
import com.sumu.pressclient.bean.NewsMenuData;

import java.util.List;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   18:32
 * <p/>
 * 描述：
 * <p/> 侧滑菜单列表适配器
 * ==============================
 */
public class LeftMenuAdapter extends BaseAdapter{
    private List<NewsMenuData> newsMenuDatas;
    private Context context;
    private int mCurrentPos;// 当前被点击的菜单项
    public LeftMenuAdapter(Context context,List<NewsMenuData> newsMenuDatas,int mCurrentPos) {
        this.context=context;
        this.newsMenuDatas=newsMenuDatas;
        this.mCurrentPos=mCurrentPos;
    }

    /**
     * 当点击其他Item时，重新绘制界面,即为了改变图片和字体的颜色
     * @param mCurrentPos
     */
    public void setmCurrentPos(int mCurrentPos){
        if (this.mCurrentPos!=mCurrentPos) {
            this.mCurrentPos=mCurrentPos;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return newsMenuDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return newsMenuDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_menu_item,null);
        TextView tvMenuItem= (TextView) view.findViewById(R.id.tv_menu_item);
        tvMenuItem.setText(newsMenuDatas.get(position).getTitle());
        // 判断当前绘制的view是否被选中
        if(position==mCurrentPos){
            tvMenuItem.setEnabled(false);// 显示红色
        }else {
            tvMenuItem.setEnabled(true);// 显示白色
        }
        return view;
    }
}
