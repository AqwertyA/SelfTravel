package org.selftravel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.callback.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MineAdapter extends RecyclerView.Adapter<MineAdapter.MyViewHolder> implements View.OnClickListener {
    private List<Data> datas;
    private LayoutInflater inflater;
    private RecyclerView recyclerView;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public MineAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
        // GridView的数据为固定值，在初始化时添加
        datas.add(new Data(R.drawable.selector_my_bt_voice, "语音"));
        datas.add(new Data(R.drawable.selector_my_bt_attention, "关注"));
        datas.add(new Data(R.drawable.selector_my_bt_album, "相册"));
        datas.add(new Data(R.drawable.selector_my_bt_message, "消息"));
        datas.add(new Data(R.drawable.selector_my_bt_like, "喜欢"));
        datas.add(new Data(R.drawable.selector_my_bt_footprint, "足迹"));
        datas.add(new Data(R.drawable.selector_my_bt_about, "关于"));
        datas.add(new Data(R.drawable.selector_my_bt_setup, "设置"));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_my_rv, parent, false);
        root.setOnClickListener(this);
        return new MyViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.iv.setImageResource(datas.get(position).imgRes);
        holder.tv.setText(datas.get(position).text);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        if (recyclerView != null && listener != null) {
            int position = recyclerView.getChildLayoutPosition(v);
            listener.onItemClick(v, position);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.image_my_rv);
            tv = (TextView) itemView.findViewById(R.id.text_my_rv);
        }
    }

    private class Data {
        private int imgRes;
        private String text;

        public Data(int imgRes, String text) {
            this.imgRes = imgRes;
            this.text = text;
        }
    }


}
