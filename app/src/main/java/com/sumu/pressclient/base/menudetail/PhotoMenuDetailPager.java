package com.sumu.pressclient.base.menudetail;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.sumu.pressclient.Contants;
import com.sumu.pressclient.R;
import com.sumu.pressclient.adapter.PhotosAdapter;
import com.sumu.pressclient.base.BaseMenuDetailPager;
import com.sumu.pressclient.bean.PhotoInfo;
import com.sumu.pressclient.bean.PhotosData;
import com.sumu.pressclient.bean.PhotosInfo;
import com.sumu.pressclient.utils.CacehUtils;

import java.util.List;


/**
 * ==============================
 * 作者：苏幕
 * <p/>
 * 时间：2015/11/16   19:21
 * <p/>
 * 描述：
 * <p/>     侧滑菜单详情页--组图
 * ==============================
 */
public class PhotoMenuDetailPager extends BaseMenuDetailPager {

    private RecyclerView rvPhotos;
    private ImageButton ibChangeList;
    private PhotosAdapter photosAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public PhotoMenuDetailPager(Activity activity, ImageButton ibPhoto) {
        super(activity);
        this.ibChangeList = ibPhoto;
        ibPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView();
            }
        });
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.photo_list, null);
        rvPhotos = (RecyclerView) view.findViewById(R.id.photo_list);
        return view;
    }

    @Override
    public void initData() {
        //读取缓存
        String cache = CacehUtils.getCache(mActivity, "photos", "");
        if (!TextUtils.isEmpty(cache)) {
            parseData(cache);
        }
        getDataFormServer();
    }

    /**
     * 从网络获取数据
     */
    private void getDataFormServer() {
        final String url = Contants.PHOTOS_URL;
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                parseData(responseInfo.result);
                CacehUtils.setCache(mActivity, url, responseInfo.result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
                        .show();
                error.printStackTrace();
            }
        });
    }

    /**
     * 解析数据并初始化界面
     * @param result
     */
    private void parseData(String result) {
        Gson gson = new Gson();
        PhotosData photosData = gson.fromJson(result, PhotosData.class);
        PhotosInfo photosInfo = photosData.getData();
        List<PhotoInfo> photoInfos=photosInfo.getNews();
        if (photoInfos!=null){
            photosAdapter=new PhotosAdapter(mActivity,photoInfos);
            mLayoutManager=new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false);
            rvPhotos.setLayoutManager(mLayoutManager);
            rvPhotos.setAdapter(photosAdapter);
        }
    }
    private boolean listOrGird;//记录当前是列表还是表格
    /**
     * 切换网格或者列表布局
     */
    private void changeView(){
        if (!listOrGird){
            listOrGird=true;
            mLayoutManager=new GridLayoutManager(mActivity,2);
            rvPhotos.setLayoutManager(mLayoutManager);
            ibChangeList.setImageResource(R.drawable.icon_pic_list_type);
        }else{
            listOrGird=false;
            mLayoutManager=new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false);
            rvPhotos.setLayoutManager(mLayoutManager);
            ibChangeList.setImageResource(R.drawable.icon_pic_grid_type);
        }

    }


}
