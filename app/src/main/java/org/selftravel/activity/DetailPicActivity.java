package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.selftravel.R;
import org.selftravel.adapter.ContentDetailPicsAdapter;
import org.selftravel.beans.ContentHeadPicsBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
@ContentView(R.layout.activity_detail_pic)
public class DetailPicActivity extends BaseActivity {

    @ViewInject(R.id.content_back)
    private ImageView back;
    @ViewInject(R.id.content_title)
    private TextView title;
    @ViewInject(R.id.pic_vp)
    private ViewPager viewPager;

    private ContentDetailPicsAdapter adapter;

    private List<ContentHeadPicsBeans.DataEntity.ImgListEntity> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
        initListener();
    }

    private void initView(){
        x.view().inject(this);
        adapter = new ContentDetailPicsAdapter(this,datas);
        datas = new ArrayList<>();
        viewPager.setAdapter(adapter);
    }

    private void initData(){
        Intent intent = getIntent();
        RequestParams params = new RequestParams(HttpUrl.CONTENT_HEAD_PIC+intent.getStringExtra("id"));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ContentHeadPicsBeans beans = new ContentHeadPicsBeans();
                Gson gson = new Gson();
                beans = gson.fromJson(result,ContentHeadPicsBeans.class);
                ContentHeadPicsBeans.DataEntity dataEntity = beans.getData();
                datas = dataEntity.getImgList();
                title.setText("1/"+datas.size());
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                title.setText(position+1+"/"+datas.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
