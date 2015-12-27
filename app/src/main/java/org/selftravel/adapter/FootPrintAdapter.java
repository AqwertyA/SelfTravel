package org.selftravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.beans.DestinationBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/12/4.
 */
public class FootPrintAdapter extends BaseAdapter {
    private Context context;
    private List<DestinationBeans.DataEntity> data;
    private ImageOptions options;

    public FootPrintAdapter(Context context, List<DestinationBeans.DataEntity> data) {
        this.context = context;
        this.data = data;
        options = new ImageOptions.Builder().setFadeIn(true)
                .setFailureDrawableId(R.drawable.ic_launcher)
                .setLoadingDrawableId(R.drawable.ic_launcher)
                .setRadius(20).build();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public DestinationBeans.DataEntity getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_footprint, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String imgUrl = HttpUrl.PIC_BASE_URL + data.get(position).getImg();
        x.image().bind(holder.imageView, imgUrl, options);
        holder.title.setText(data.get(position).getTitle());
        String numText = data.get(position).getGo() + "个人喜欢\t" + data.get(position).getBeen() + "个人去过";
        holder.num.setText(numText);

        return convertView;
    }

    public static class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView num;

        public ViewHolder(View convertView) {
            imageView = (ImageView) convertView.findViewById(R.id.iv_footprint);
            title = (TextView) convertView.findViewById(R.id.tv_title);
            num = (TextView) convertView.findViewById(R.id.tv_num);
            convertView.setTag(this);
        }
    }

}
