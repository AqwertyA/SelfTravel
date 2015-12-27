package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.selftravel.R;
import org.selftravel.adapter.MoreMsgListViewAdapter;
import org.selftravel.adapter.MsgListViewAdapter;
import org.selftravel.beans.ContentMsgBeans;
import org.selftravel.http.HttpUrl;
import org.selftravel.view.TitleBar;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/12/4.
 */
@ContentView(R.layout.activity_more_msg)
public class MoreMsgActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.more_msg_title)
    private TitleBar title;
    @ViewInject(R.id.send_btn)
    private Button sendBtn;
    @ViewInject(R.id.msg_et)
    private EditText msgEt;
    @ViewInject(R.id.more_msg_lv)
    private ListView listView;

    private Intent fromIntent;

    private int start = 0;

    private List<ContentMsgBeans.DataEntity.DataListEntity> datas;

    private MoreMsgListViewAdapter adapter;

    //ListView 尾部 翻页按钮
    private TextView netx,last;

    private View footView;

    private ProgressBar progressBar;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    start += 10;
                    initData(start);
                    break;
                case 1:
                    start -= 10;
                    initData(start);
                    break;
            }
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
        fromIntent = getIntent();
        title.setTitle(fromIntent.getStringExtra("title"));
        adapter = new MoreMsgListViewAdapter(datas,this);
        listView.setAdapter(adapter);
        footView = LayoutInflater.from(this).inflate(R.layout.layout_foot_more_msg,null);
        listView.addFooterView(footView);
        netx = (TextView) footView.findViewById(R.id.next);
        last = (TextView) footView.findViewById(R.id.last);
        progressBar = (ProgressBar) footView.findViewById(R.id.wait_bar);
    }

    private void initData(final int page) {
        RequestParams params = new RequestParams(HttpUrl.CONTENT_MESSAGE_START+fromIntent.getStringExtra("id")
        +HttpUrl.CONTENT_MESSAGE_MID+page+HttpUrl.CONTENT_MESSAGE_END_MORE);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ContentMsgBeans beans = new ContentMsgBeans();
                Gson gson = new Gson();
                beans = gson.fromJson(result,ContentMsgBeans.class);
                ContentMsgBeans.DataEntity entity = beans.getData();
                datas = entity.getDataList();
                if(datas.size() != 0){
                    adapter.upData(datas);
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
                listView.setSelection(0);
                progressBar.setVisibility(View.GONE);
                netx.setVisibility(View.VISIBLE);
                if(page != 0){
                    last.setVisibility(View.VISIBLE);
                }
                if(datas.size() < 10){
                    Toast.makeText(MoreMsgActivity.this,"最后一页了",Toast.LENGTH_SHORT).show();
                    if(datas.size() == 0){
                        start -= 10;
                    }
                    netx.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initListener() {
        netx.setOnClickListener(this);
        last.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next:
                Message msg = Message.obtain();
                msg.what = 0;
                netx.setVisibility(View.GONE);
                last.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                handler.sendEmptyMessage(msg.what);
                break;
            case R.id.last:
                Message msg1 = Message.obtain();
                msg1.what = 1;
                netx.setVisibility(View.GONE);
                last.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                handler.sendEmptyMessage(msg1.what);
                break;
        }
    }
}
