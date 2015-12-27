package org.selftravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.beans.PlacesBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public class PlaceAdapter extends BaseAdapter {

    private List<PlacesBeans.DataEntity> datas;

    private LayoutInflater inflater;

    public PlaceAdapter(List<PlacesBeans.DataEntity> datas,Context context) {
        super();
        if(datas != null){
            this.datas = datas;
        }else{
            this.datas = new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
    }

    public void addDatas(List<PlacesBeans.DataEntity> datas){
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
            convertView = inflater.inflate(R.layout.layout_place_item,null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.pic_iv);
            holder.textView = (TextView) convertView.findViewById(R.id.place_name);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
            holder.textView.setText(datas.get(position).getTitle());
            x.image().bind(holder.imageView, HttpUrl.PIC_BASE_URL+datas.get(position).getImg());

        return convertView;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView textView;
    }
}
