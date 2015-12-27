package org.selftravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.selftravel.beans.PhotoListBeans;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 15-12-4.
 */
public class PhotoListAdapter extends BaseAdapter {
    private static final String TAG = PhotoListAdapter.class.getSimpleName();

    private List<PhotoListBeans> data;
    private LayoutInflater inflater;

    public PhotoListAdapter(List<PhotoListBeans> data, Context context) {
        this.data = data;
        inflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){

        }
        return convertView;
    }

    public class ViewHolder{
        TextView date;

    }
}
