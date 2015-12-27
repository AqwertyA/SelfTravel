package org.selftravel.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.selftravel.R;
import org.selftravel.beans.FindBeans;
import org.selftravel.http.HttpUrl;
import org.w3c.dom.Text;
import org.xutils.image.ImageManagerImpl;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class FindAdapter extends BaseAdapter {
    private final List<FindBeans.DataEntity> data;
    private final LayoutInflater inflater;
    private ImageOptions options;

    public FindAdapter(List<FindBeans.DataEntity> data, Context context) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        options = new ImageOptions.Builder()
                .setFailureDrawableId(R.drawable.near_scenic_region_default)
                .setRadius(16)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position).getWapurl();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_item_find, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String imgUrl = HttpUrl.PIC_BASE_URL + data.get(position).getWapimg();
        x.image().bind(holder.findIv, imgUrl, options);
        holder.findTv.setText(data.get(position).getTitle());
        return convertView;
    }

    public String getWapUrl(int position) {
        return data.get(position).getWapurl();
    }

    public static class ViewHolder {
        ImageView findIv;
        TextView findTv;

        public ViewHolder(View convertView) {
            findIv = (ImageView) convertView.findViewById(R.id.iv_find);
            findTv = (TextView) convertView.findViewById(R.id.tv_find);
            convertView.setTag(this);
        }
    }
}
