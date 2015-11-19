package com.sumu.pressclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.sumu.pressclient.R;
import com.sumu.pressclient.bean.PhotoInfo;

import java.util.List;

/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/19   12:55
 * <p/>
 * 描述：
 * <p/>组图显示适配器
 * ==============================
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private List<PhotoInfo> photosInfos;
    private BitmapUtils bitmapUtils;
    private Context context;
    public PhotosAdapter(Context context,List<PhotoInfo> photosInfos) {
        this.photosInfos = photosInfos;
        this.context=context;
        bitmapUtils=new BitmapUtils(context);
        bitmapUtils.configDefaultLoadingImage(R.drawable.pic_item_list_default);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.pic_item_list_default);
    }


    @Override
    public PhotosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.photos_list_item,null);
        PhotosAdapter.ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotosAdapter.ViewHolder holder, int position) {
        PhotoInfo photoInfo=photosInfos.get(position);
        holder.tvTitle.setText(photoInfo.getTitle());
        bitmapUtils.display(holder.ivPic,photoInfo.getListimage());
    }

    @Override
    public int getItemCount() {
        return photosInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPic;
        TextView tvTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            ivPic= (ImageView) itemView.findViewById(R.id.iv_pic);
            tvTitle= (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
