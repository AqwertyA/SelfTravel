package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.selftravel.R;
import org.selftravel.adapter.MorePicViewPageAdapter;
import org.selftravel.beans.MorePicBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 */
@ContentView(R.layout.activity_more_pic_detail)
public class MorePicDetailActivity extends BaseActivity implements ViewPager.OnPageChangeListener,View.OnClickListener {

    @ViewInject(R.id.content_back)
    private ImageView back;
    @ViewInject(R.id.content_title)
    private TextView title;
    @ViewInject(R.id.user_pic)
    private ImageView userPic;
    @ViewInject(R.id.pic_vp)
    private ViewPager viewPager;
    @ViewInject(R.id.img_title)
    private TextView imgTitle;

    private Intent fromIntent;

    private List<MorePicBeans.DataEntity> datas;

    private MorePicViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        x.view().inject(this);
        fromIntent = getIntent();
        title.setText(fromIntent.getIntExtra("position", 0) + "/" + fromIntent.getIntExtra("total", 0));
        adapter = new MorePicViewPageAdapter(this,datas);
        viewPager.setAdapter(adapter);
    }

    private void initData() {
        RequestParams params = new RequestParams(HttpUrl.MORE_PIC_START+fromIntent.getStringExtra("id")
                +HttpUrl.MORE_PIC_DETAIL_MID+fromIntent.getIntExtra("total",3)+HttpUrl.MORE_PIC_DETAIL_END);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                MorePicBeans beans = new MorePicBeans();
                Gson gson = new Gson();
                beans = gson.fromJson(result, MorePicBeans.class);
                datas = beans.getData();
                adapter.addDatas(datas);
                if(datas !=null){
                    viewPager.setCurrentItem(fromIntent.getIntExtra("position", 1) - 1);
                    imgTitle.setText(datas.get(fromIntent.getIntExtra("position", 1) - 1).getImgtitle());
                    x.image().bind(userPic, HttpUrl.PIC_BASE_URL + datas.get(fromIntent.getIntExtra("position", 1) - 1).getHead_s()
                                        ,new ImageOptions.Builder()
                                        .setRadius(50)
                                        .build());
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
        viewPager.setOnPageChangeListener(this);
        back.setOnClickListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        title.setText((position+1)+"/"+datas.size());
        x.image().bind(userPic, HttpUrl.PIC_BASE_URL + datas.get(position).getHead_s());
        imgTitle.setText(datas.get(position).getImgtitle());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.content_back:
                finish();
                break;
        }
    }
}
