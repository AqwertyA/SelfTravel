package org.selftravel.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.selftravel.beans.ContentHeadPicsBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public class ContentDetailPicsAdapter extends PagerAdapter {

    private List<ContentHeadPicsBeans.DataEntity.ImgListEntity> datas;

    private Context context;

    public ContentDetailPicsAdapter(Context context, List<ContentHeadPicsBeans.DataEntity.ImgListEntity> datas) {
        this.context = context;
        if(datas != null){
            this.datas = datas;
        }else{
            this.datas = new ArrayList<>();
        }
    }

    public void addDatas(List<ContentHeadPicsBeans.DataEntity.ImgListEntity> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        x.image().bind(imageView, HttpUrl.PIC_BASE_URL+datas.get(position).getImg());
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}
