package org.selftravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.activity.ContentActivity;
import org.selftravel.beans.DestinationBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-11-30.
 */
public class Destination_GridViewAdapter extends BaseAdapter  {
    private Context context;
    private List<DestinationBeans.DataEntity> data;
    private ImageOptions options ;

    public Destination_GridViewAdapter(Context context, List<DestinationBeans.DataEntity> data) {
        this.context = context;
        if (data == null){
            this.data = new ArrayList<>();
        }else {
            this.data = data;
        }
        options = new ImageOptions.Builder().setFadeIn(true)
                                            .setFailureDrawableId(R.drawable.ic_launcher)
                                            .setLoadingDrawableId(R.drawable.ic_launcher)
                                            .setRadius(20).build();
    }

    public void addRes(List<DestinationBeans.DataEntity>data){
        if (data!=null){
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
    public void updateRes(List<DestinationBeans.DataEntity>data){
        if (data!=null){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_destination, parent, false);
            holder.item_bg = (ImageView)convertView.findViewById(R.id.destination_recommend_item_bg);
            holder.item_title = (TextView)convertView.findViewById(R.id.destination_recommend_item_title);
            holder.item_voice = (ImageView)convertView.findViewById(R.id.destination_recommend_item_voice);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.item_title.setText(data.get(position).getTitle());
        x.image().bind(holder.item_bg, HttpUrl.PIC_BASE_URL+data.get(position).getImg(),options);
        if (data.get(position).getVoiceCount()>0){
            holder.item_voice.setImageResource(R.drawable.general_bt_voice);
        }
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("id",data.get(position).getId());
                intent.putExtra("title", data.get(position).getTitle());
                intent.putExtra("go",data.get(position).getGo()+"");
                intent.putExtra("been",data.get(position).getBeen()+"");
                intent.putExtra("pic",data.get(position).getImg());
                (context).startActivity(intent);
            }
        });
        return convertView;
    }


    class ViewHolder{
        private ImageView item_bg;
        private ImageView item_voice;
        private TextView item_title;
    }

}
