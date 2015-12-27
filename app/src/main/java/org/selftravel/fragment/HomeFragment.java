package org.selftravel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import org.selftravel.R;
import org.selftravel.activity.ContentActivity;
import org.selftravel.activity.MainActivity;
import org.selftravel.activity.WebViewActivity;
import org.selftravel.adapter.HomeAdapter;
import org.selftravel.beans.HomeBeans;
import org.selftravel.beans.HomeHeadBeans;
import org.selftravel.http.HttpUrl;
import org.selftravel.view.SearchTitle;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private static final String TAG = HomeFragment.class.getSimpleName();

    private ListView listView;

    private HomeAdapter adapter;

    private List<HomeBeans.Datas> datas;

    private List<HomeHeadBeans.HeadDatas> headDatas;

    private View headView;

    private ImageView adImageView;

    private SearchTitle title;


    private View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        initView();
        initDatas();
        initListener();
        return root;
    }

    private void initView() {
        listView = (ListView) root.findViewById(R.id.lv_home);
        adapter = new HomeAdapter(getContext(), datas);
        listView.setAdapter(adapter);
        title = (SearchTitle) root.findViewById(R.id.home_title);

        headView = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_head, null);

        adImageView = (ImageView) headView.findViewById(R.id.ad_img);
        //给搜索栏设置背景颜色
        title.setBgColor();
    }

    private void initDatas() {
        RequestParams adParams = new RequestParams(HttpUrl.HOME_HEADER);
        x.http().get(adParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                HomeHeadBeans beans = gson.fromJson(s, HomeHeadBeans.class);
                headDatas = beans.getData();
                //给头部广告设置图片
                x.image().bind(adImageView, HttpUrl.PIC_BASE_URL + headDatas.get(0).getWapimg(), new ImageOptions.Builder()
                        .build());
                listView.addHeaderView(headView);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Log.e(TAG, "数据获取发送错误");
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {
                Log.e(TAG, "数据获取发送完毕");
            }
        });

        RequestParams params = new RequestParams(HttpUrl.HOME_DETAIL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "数据获取发送成功111");
                HomeBeans beans = new HomeBeans();
                Gson gson = new Gson();
                beans = gson.fromJson(result, HomeBeans.class);
                datas = beans.getData();
                MainActivity.homeData = datas;
                adapter.addDatas(datas);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "数据获取发送错误11");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "数据获取发送取消");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "数据获取发送完毕");
            }
        });
    }


    private void initListener() {
        adImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", headDatas.get(0).getWapurl());
                intent.putExtra("title", "专题活动");
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), ContentActivity.class);
        intent.putExtra("pic", datas.get(position - 1).getImg());
        intent.putExtra("id", datas.get(position - 1).getId());
        intent.putExtra("title", datas.get(position - 1).getTitle());
        intent.putExtra("go", datas.get(position - 1).getGo());
        intent.putExtra("been", datas.get(position - 1).getBeen());
        startActivity(intent);

    }
}
