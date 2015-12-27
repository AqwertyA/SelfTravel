package org.selftravel.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.activity.ContentActivity;
import org.selftravel.beans.HomeBeans;

import java.util.List;

/**
 * Created by Administrator on 15-12-2.
 */
public class SearchAroundAdapter extends BaseAdapter{
    private List<HomeBeans.Datas> data;
    private Context context;

    public SearchAroundAdapter(List<HomeBeans.Datas> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_search,null);
            holder.item_title = (TextView)convertView.findViewById(R.id.item_title);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
         holder.item_title.setText(data.get(position).getTitle());
         holder.item_title.setOnClickListener(new View.OnClickListener() {
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
        TextView item_title;
    }
}
