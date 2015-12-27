package org.selftravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.app.SelfTravelApp;
import org.selftravel.callback.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class SettingsAdapter extends BaseAdapter implements View.OnClickListener {
    private static final String CHANGE_PWD = "密码修改";
    private static final String VERSION = "版本";
    private static final String ABOUT_US = "关于我们";
    private static final String LOG_OUT = "退出登录";

    public static class SettingType {
        public static final int CHANGE_PWD = 1;
        public static final int VERSION = 2;
        public static final int ABOUT_US = 3;
        public static final int LOG_OUT = 4;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    private LayoutInflater inflater;
    private final List<Data> data;

    public SettingsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
        data.add(new Data(CHANGE_PWD));
        data.add(new Data(VERSION));
        data.add(new Data(ABOUT_US));
        data.add(new Data(LOG_OUT));
        if (!SelfTravelApp.isLoggedIn()) {
            data.remove(3);
            data.remove(0);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Data getItem(int position) {
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
            convertView = inflater.inflate(R.layout.layout_item_text_button, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(data.get(position).imgRes);
        holder.textView.setText(data.get(position).text);
        convertView.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        String text = ((ViewHolder) v.getTag()).textView.getText().toString();
        switch (text) {
            case CHANGE_PWD:
                if (listener != null) {
                    listener.onItemClick(v, SettingType.CHANGE_PWD);
                }
                break;
            case ABOUT_US:
                if (listener != null) {
                    listener.onItemClick(v, SettingType.ABOUT_US);
                }
                break;
            case VERSION:
                if (listener != null) {
                    listener.onItemClick(v, SettingType.VERSION);
                }
                break;
            case LOG_OUT:
                if (listener != null) {
                    listener.onItemClick(v, SettingType.LOG_OUT);
                }
                break;
            default:
                break;
        }

    }

    public static class Data {
        String text;
        int imgRes;

        Data(String text) {
            this(text, R.drawable.list_icon);
        }

        Data(String text, int imgRes) {
            this.text = text;
            this.imgRes = imgRes;
        }
    }

    public static class ViewHolder {
        TextView textView;
        ImageView imageView;

        ViewHolder(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.tv_text);
            imageView = (ImageView) convertView.findViewById(R.id.iv_arrow);
        }
    }

}
