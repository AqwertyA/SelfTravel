package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import org.selftravel.R;
import org.selftravel.adapter.PlaceAdapter;
import org.selftravel.beans.PlacesBeans;
import org.selftravel.view.TitleBar;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
@ContentView(R.layout.activity_places)
public class PlaceActivity extends BaseActivity {

    @ViewInject(R.id.place_title)
    private TitleBar title;
    @ViewInject(R.id.place_lv)
    private ListView listView;

    private List<PlacesBeans.DataEntity> datas;

    private PlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
        initListener();
    }

    private void initView(){
        x.view().inject(this);
        adapter = new PlaceAdapter(datas,this);
        listView.setAdapter(adapter);
        title.setTitle("景点列表");
    }

    private void initData(){
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                PlacesBeans beans = new PlacesBeans();
                Gson gson = new Gson();
                beans = gson.fromJson(result,PlacesBeans.class);
                datas = beans.getData();
                adapter.addDatas(datas);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initListener(){

    }
}
