package org.selftravel.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.activity.MorePicDetailActivity;
import org.selftravel.beans.MorePicBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 */
public class MorePicAdapter extends BaseAdapter {

    private List<MorePicBeans.DataEntity> datas;

    private LayoutInflater inflater;

    private Context context;

    public MorePicAdapter(List<MorePicBeans.DataEntity> datas,Context context) {
        super();
        this.context = context;
        if(datas != null){
            this.datas = datas;
        }else{
            this.datas = new ArrayList<>();
        }

        inflater = LayoutInflater.from(context);
    }

    public void addDatas(List<MorePicBeans.DataEntity> datas){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_contene_gv_item,null);

            holder.bgPic = (ImageView) convertView.findViewById(R.id.content_pic);
            holder.userPic = (ImageView) convertView.findViewById(R.id.user_pic);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.date = (TextView) convertView.findViewById(R.id.dates);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

            holder.name.setText(datas.get(position).getUsername());
            holder.date.setText(datas.get(position).getShowtime());
            x.image().bind(holder.bgPic, HttpUrl.PIC_BASE_URL + datas.get(position).getImg(),
                    new ImageOptions.Builder()
                            .setLoadingDrawableId(R.drawable.default_detial_image)
                            .build());
            x.image().bind(holder.userPic, HttpUrl.PIC_BASE_URL + datas.get(position).getHead_s(),
                    new ImageOptions.Builder()
                            .setRadius(50)
                            .build());
        return convertView;
    }

    class ViewHolder{
        private ImageView bgPic,userPic;
        private TextView name,date;
    }
}
