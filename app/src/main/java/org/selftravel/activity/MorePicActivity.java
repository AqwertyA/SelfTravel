package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.selftravel.R;
import org.selftravel.adapter.MorePicAdapter;
import org.selftravel.adapter.PicGradViewAdapter;
import org.selftravel.beans.MorePicBeans;
import org.selftravel.http.HttpUrl;
import org.selftravel.view.TitleBar;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 */
@ContentView(R.layout.activity_more_pic)
public class MorePicActivity extends BaseActivity {

    @ViewInject(R.id.more_pic_title)
    private TitleBar title;
    @ViewInject(R.id.more_pic_gv)
    private PullToRefreshGridView picGridView;

    private MorePicAdapter adapter;

    private List<MorePicBeans.DataEntity> datas;

    private Intent fromIntent;

    private int start = 0;

    //是否上拉加载完成的标识
    private boolean flag = false;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            start = start+16;
            if(datas.size() == 0){
                Toast.makeText(MorePicActivity.this,"这是最后一张了~",Toast.LENGTH_SHORT).show();
            }
            initData(start);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData(start);
        initListener();
    }

    private void initView() {
        x.view().inject(this);
        picGridView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        adapter = new MorePicAdapter(datas,this);
        picGridView.setAdapter(adapter);
        fromIntent = getIntent();
        title.setTitle(fromIntent.getStringExtra("title"));
    }

    private void initData(int page) {
        RequestParams params = new RequestParams(HttpUrl.MORE_PIC_START+fromIntent.getStringExtra("id")
                +HttpUrl.MORE_PIC_MID+page+HttpUrl.MORE_PIC_END);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                MorePicBeans beans = new MorePicBeans();
                Gson gson = new Gson();
                beans = gson.fromJson(result, MorePicBeans.class);
                datas = beans.getData();
                adapter.addDatas(datas);

                if(flag){
                    flag = false;
                    picGridView.onRefreshComplete();
                }

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

    private void initListener() {
        picGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                flag = true;
                Message msg = new Message().obtain();
                msg.what = 0;
                handler.sendEmptyMessage(msg.what);
            }
        });

        GridView gridView = picGridView.getRefreshableView();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MorePicActivity.this, MorePicDetailActivity.class);
                intent.putExtra("position",position+1);
                intent.putExtra("total",adapter.getCount());
                intent.putExtra("id",fromIntent.getStringExtra("id"));
                startActivity(intent);
            }
        });
    }
}
