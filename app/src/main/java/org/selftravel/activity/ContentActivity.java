package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.selftravel.R;
import org.selftravel.adapter.MsgListViewAdapter;
import org.selftravel.adapter.PicGradViewAdapter;
import org.selftravel.beans.ContentMsgBeans;
import org.selftravel.beans.ContentPicBeans;
import org.selftravel.http.HttpUrl;
import org.selftravel.utils.ListViewHeightBasedOnChildren;
import org.selftravel.view.DetailGradView;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 *
 * 启动该Activity,需要intent点击项的id(key = id )，还有点击项图片的URL（key = pic,不需要拼接）,title,go,been
 */
@ContentView(R.layout.activity_content)
public class ContentActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.content_head_iv)
    private ImageView imageView;
    @ViewInject(R.id.detail_btn)
    private ImageView detail;
    @ViewInject(R.id.location_btn)
    private ImageView location;
    @ViewInject(R.id.place_btn)
    private TextView place;
    @ViewInject(R.id.more_pic)
    private TextView morePic;
    @ViewInject(R.id.more_msg)
    private TextView moreMsg;
    @ViewInject(R.id.content_pic_gv)
    private DetailGradView picGradView;
    @ViewInject(R.id.content_msg_lv)
    private ListView msgListView;
    @ViewInject(R.id.content_back)
    private ImageView back;
    @ViewInject(R.id.content_title)
    private TextView title;
    @ViewInject(R.id.likes_btn)
    private ImageView likesBtn;
    @ViewInject(R.id.like_num)
    private TextView likeNum;
    @ViewInject(R.id.been_btn)
    private ImageView beenBtn;
    @ViewInject(R.id.been_num)
    private TextView beenNum;

    private String picUrl,msgUrl;

    private List<ContentPicBeans.ContentPic> datas;

    private List<ContentMsgBeans.DataEntity.DataListEntity> msgDatas;

    private PicGradViewAdapter adapter;

    private MsgListViewAdapter msgAdapter;

    private Intent fromIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
        initListener();
    }

    private void initView(){
        x.view().inject(this);
        fromIntent = getIntent();
        title.setText(fromIntent.getStringExtra("title"));
        likeNum.setText(fromIntent.getStringExtra("go"));
        beenNum.setText(fromIntent.getStringExtra("been"));
        String headPicUrl = HttpUrl.PIC_BASE_URL+fromIntent.getStringExtra("pic");
        picUrl = HttpUrl.CONTENT_PIC_START+fromIntent.getStringExtra("id")+HttpUrl.CONTENT_PIC_END;
        msgUrl = HttpUrl.CONTENT_MESSAGE_START+fromIntent.getStringExtra("id")+HttpUrl.CONTENT_MESSAGE_END;
        x.image().bind(imageView,headPicUrl);

        adapter = new PicGradViewAdapter(datas,this);
        msgAdapter = new MsgListViewAdapter(msgDatas,this);

        picGradView.setAdapter(adapter);
        msgListView.setAdapter(msgAdapter);
    }

    private void initData(){
        RequestParams picParams = new RequestParams(picUrl);
        x.http().get(picParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ContentPicBeans beans = new ContentPicBeans();
                Gson gson = new Gson();
                beans = gson.fromJson(result,ContentPicBeans.class);
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

        RequestParams msgParams = new RequestParams(msgUrl);
        x.http().get(msgParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ContentMsgBeans beans = new ContentMsgBeans();
                ContentMsgBeans.DataEntity dataEntity = new ContentMsgBeans.DataEntity();
                Gson gson = new Gson();
                beans = gson.fromJson(result, ContentMsgBeans.class);
                dataEntity = beans.getData();
                msgDatas = dataEntity.getDataList();
                msgAdapter.addData(msgDatas);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("list","评论信息获取出错");
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
        back.setOnClickListener(this);
        imageView.setOnClickListener(this);
        detail.setOnClickListener(this);
        place.setOnClickListener(this);
        morePic.setOnClickListener(this);
        moreMsg.setOnClickListener(this);

        picGradView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContentActivity.this, MorePicActivity.class);
                intent.putExtra("title", "景区相关照片");
                intent.putExtra("id", fromIntent.getStringExtra("id"));
                startActivity(intent);
            }
        });

        msgListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(ContentActivity.this,MoreMsgActivity.class);
                intent1.putExtra("title",fromIntent.getStringExtra("title")+"-评论");
                intent1.putExtra("id",fromIntent.getStringExtra("id"));
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.content_back:
                this.finish();
                break;
            case R.id.content_head_iv:
                Intent intent = new Intent(this,DetailPicActivity.class);
                intent.putExtra("id",fromIntent.getStringExtra("id"));
                startActivity(intent);
                break;
            case R.id.detail_btn:
                Intent intent1 = new Intent(this,WebViewActivity.class);
                intent1.putExtra("title","景区详情");
                intent1.putExtra("url",HttpUrl.CONTENT_INFO+fromIntent.getStringExtra("id"));
                startActivity(intent1);
                break;
            case R.id.place_btn:
                Intent intent2 = new Intent(this,PlaceActivity.class);
                intent2.putExtra("url",HttpUrl.CONTENT_PLACE_START+fromIntent.getStringExtra("id")+HttpUrl.CONTENT_PLACE_END);
                startActivity(intent2);
                break;
            case R.id.more_pic:
                Intent intent3 = new Intent(this,MorePicActivity.class);
                intent3.putExtra("title","景区相关照片");
                intent3.putExtra("id",fromIntent.getStringExtra("id"));
                startActivity(intent3);
                break;
            case R.id.more_msg:
                Intent intent4 = new Intent(this,MoreMsgActivity.class);
                intent4.putExtra("title",fromIntent.getStringExtra("title")+"-评论");
                intent4.putExtra("id",fromIntent.getStringExtra("id"));
                startActivity(intent4);
                break;
        }
    }
}
