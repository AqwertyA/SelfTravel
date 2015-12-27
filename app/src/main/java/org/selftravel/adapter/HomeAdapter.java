package org.selftravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.beans.HomeBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class HomeAdapter extends BaseAdapter{
    private List<HomeBeans.Datas> datas;

    private LayoutInflater inflater;

    private ImageOptions options;

    public HomeAdapter(Context context,List<HomeBeans.Datas> datas) {
        super();
        inflater = LayoutInflater.from(context);
        if(datas == null){
            this.datas = new ArrayList<>();
        }else{
            this.datas = datas;
        }
        options =  new ImageOptions.Builder()
                .setFadeIn(true)
                .setRadius(5)
                .setLoadingDrawableId(R.drawable.default_detial_image)
                .setFailureDrawableId(R.drawable.default_detial_image)
                .build();

    }

    public void addDatas(List<HomeBeans.Datas> datas){
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
            convertView = inflater.inflate(R.layout.layout_home_item,null);

            holder.imageView = (ImageView) convertView.findViewById(R.id.bg_pic);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.distance = (TextView) convertView.findViewById(R.id.distance);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
            holder.name.setText(datas.get(position).getTitle());
            String distance = datas.get(position).getDistance()/1000+"km";
            holder.distance.setText(distance);
            x.image().bind(holder.imageView, HttpUrl.PIC_BASE_URL+datas.get(position).getImg(),options);

        return convertView;
    }

    class ViewHolder{
        private ImageView imageView;
        private TextView name;
        private TextView distance;
    }
}
