package org.selftravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.beans.ContentPicBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 */
public class PicGradViewAdapter extends BaseAdapter {
    private List<ContentPicBeans.ContentPic> datas;

    private LayoutInflater inflater;

    public PicGradViewAdapter(List<ContentPicBeans.ContentPic> datas,Context context) {
        super();
        if (datas != null){
            this.datas = datas;
        }else{
            this.datas = new ArrayList<>();
        }

        inflater = LayoutInflater.from(context);
    }

    public void addDatas(List<ContentPicBeans.ContentPic> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView =inflater.inflate(R.layout.layout_contene_gv_item,null);

            holder.ivBg = (ImageView) convertView.findViewById(R.id.content_pic);
            holder.ivUser = (ImageView) convertView.findViewById(R.id.user_pic);
            holder.tvName = (TextView) convertView.findViewById(R.id.name);
            holder.tvDate = (TextView) convertView.findViewById(R.id.dates);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
            holder.tvName.setText(datas.get(position).getUsername());
            holder.tvDate.setText(datas.get(position).getShowtime());
            x.image().bind(holder.ivBg, HttpUrl.PIC_BASE_URL + datas.get(position).getImg()
                    , new ImageOptions.Builder()
                    .setFailureDrawableId(R.drawable.default_detial_image)
                    .setLoadingDrawableId(R.drawable.default_detial_image)
                    .build());
            x.image().bind(holder.ivUser,HttpUrl.PIC_BASE_URL+datas.get(position).getHead_s()
                    ,new ImageOptions.Builder()
                    .setRadius(50)
                    .build());

        return convertView;
    }

    class ViewHolder{
        private ImageView ivBg,ivUser;
        private TextView tvName,tvDate;

    }
}
