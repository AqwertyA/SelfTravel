package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.selftravel.R;
import org.selftravel.adapter.FootPrintAdapter;
import org.selftravel.app.SelfTravelApp;
import org.selftravel.beans.DestinationBeans;
import org.selftravel.view.TitleBar;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(R.layout.activity_like)
public class LikeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_bar)
    private TitleBar titleBar;
    @ViewInject(R.id.list_view_attention)
    private ListView listView;
    private List<DestinationBeans.DataEntity> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
        initData();

    }

    private void initView() {
        x.view().inject(this);
    }

    private void setListener() {
        listView.setOnItemClickListener(this);
    }

    private void initData() {
        titleBar.setTitle("喜欢");
        try {
            data = x.getDb(SelfTravelApp.daoConfig).selector(DestinationBeans.DataEntity.class).findAll();
            FootPrintAdapter adapter = new FootPrintAdapter(this, data);
            listView.setAdapter(adapter);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("id", data.get(position).getId());
        intent.putExtra("title", data.get(position).getTitle());
        intent.putExtra("go", data.get(position).getGo() + "");
        intent.putExtra("been", data.get(position).getBeen() + "");
        intent.putExtra("pic", data.get(position).getImg());
        (this).startActivity(intent);
    }
}
