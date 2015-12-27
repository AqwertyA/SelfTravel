package org.selftravel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.selftravel.R;
import org.selftravel.adapter.SearchAroundAdapter;
import org.selftravel.adapter.SearchHistoryAdapter;
import org.selftravel.adapter.SearchHotSearchAdapter;
import org.selftravel.beans.DestinationBeans;
import org.selftravel.beans.HomeBeans;
import org.selftravel.http.HttpUrl;
import org.selftravel.view.SearchTitle;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-12-4.
 */
public class SearchAcitivity extends BaseActivity implements View.OnClickListener {
    private SearchTitle title;
    private GridView history,hot,around;
    private SearchAroundAdapter aroundAdapter;

    private RelativeLayout historyLayout;
    private ImageView searchImg;
    private TextView searchText,cancle;
    private List<String> historyContent = null;
    private SearchHistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
    }

    private void initView() {

       getHotSearchData();

        history = (GridView)findViewById(R.id.searchActivity_history);
        hot = (GridView)findViewById(R.id.searchActivity_hot);
        around = (GridView)findViewById(R.id.searchActivity_around);
        List<HomeBeans.Datas> homeData = MainActivity.homeData;
        aroundAdapter = new SearchAroundAdapter(homeData,this);
        around.setAdapter(aroundAdapter);

        historyLayout = (RelativeLayout) findViewById(R.id.searchActivity_layout_history);
        if (history==null) {
            historyLayout.setVisibility(View.INVISIBLE);
        }else {
            historyLayout.setVisibility(View.VISIBLE);
            historyAdapter = new SearchHistoryAdapter(historyContent,this);
            history.setAdapter(historyAdapter);
        }
        searchImg = (ImageView)findViewById(R.id.searchActivity_searchImg);
        searchImg.setOnClickListener(this);
        searchText =(TextView)findViewById(R.id.searchActivity_searchText);
        searchText.setOnClickListener(this);
        cancle = (TextView)findViewById(R.id.searchActivity_cancle);

    }
    private void getHotSearchData() {
        RequestParams params = new RequestParams(HttpUrl.HOT_ATTRACTIONS);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                try {
                    JSONArray data = new JSONObject(result).getJSONArray("data");
                    Gson gson = new Gson();
                    Type typeList = new TypeToken<List<DestinationBeans.DataEntity>>() {}.getType();
                    List<DestinationBeans.DataEntity> dataEntity = gson.fromJson(data.toString(), typeList);

                    SearchHotSearchAdapter adapter = new SearchHotSearchAdapter(dataEntity,SearchAcitivity.this );
                    hot.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
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
    //搜索，取消的监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchActivity_searchImg:
                String text = searchText.getText().toString();
                historyContent.add(text);
                break;
            case R.id.searchActivity_cancle:
               historyContent.clear();
                break;
        }
    }
}
