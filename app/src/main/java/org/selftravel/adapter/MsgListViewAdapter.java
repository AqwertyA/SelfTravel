package org.selftravel.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.beans.ContentMsgBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public class MsgListViewAdapter extends BaseAdapter {

    private List<ContentMsgBeans.DataEntity.DataListEntity> datas;

    private LayoutInflater inflater;

    public MsgListViewAdapter(List<ContentMsgBeans.DataEntity.DataListEntity> datas,Context context) {
        super();
        if(datas != null){
            this.datas = datas;
        }else{
            this.datas = new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<ContentMsgBeans.DataEntity.DataListEntity> datas){
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
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_content_lv_item,null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.user_pic);
            holder.msg = (TextView) convertView.findViewById(R.id.msg_tv);
            holder.userName = (TextView) convertView.findViewById(R.id.user_name);
            holder.time = (TextView) convertView.findViewById(R.id.msg_time);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
            holder.userName.setText(datas.get(position).getName());
            holder.time.setText(datas.get(position).getAdd_time());
            holder.msg.setText(datas.get(position).getContent());
            x.image().bind(holder.imageView, HttpUrl.PIC_BASE_URL + datas.get(position).getHead_m()
                    , new ImageOptions.Builder()
                    .setRadius(50)
                    .setFailureDrawableId(R.drawable.default_user)
                    .build());
        return convertView;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView userName,time,msg;
    }
}
