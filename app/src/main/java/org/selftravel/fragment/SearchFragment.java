package org.selftravel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.selftravel.R;
import org.selftravel.activity.MainActivity;
import org.selftravel.adapter.SearchAroundAdapter;
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

public class SearchFragment extends BaseFragment {
    private SearchTitle title;

    private GridView hotSearch,around;
    private SearchAroundAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        title = (SearchTitle)root.findViewById(R.id.search_top);
        title.setSearch();


        hotSearch = (GridView)root.findViewById(R.id.search_hot);
        around = (GridView)root.findViewById(R.id.search_around);

        List<HomeBeans.Datas> homeData = MainActivity.homeData;
        adapter = new SearchAroundAdapter(homeData,getActivity());
        around.setAdapter(adapter);

        getHotSearchData();
        return root;
    }

    private void getHotSearchData() {
        RequestParams params = new RequestParams(HttpUrl.HOT_ATTRACTIONS);
        x.http().get(params, new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {

                try {
                    JSONArray  data = new JSONObject(result).getJSONArray("data");
                    Gson gson = new Gson();
                    Type typeList = new TypeToken<List<DestinationBeans.DataEntity>>(){}.getType();
                    List<DestinationBeans.DataEntity> dataEntity = gson.fromJson(data.toString(), typeList);

                    SearchHotSearchAdapter adapter = new SearchHotSearchAdapter(dataEntity,getActivity());
                    hotSearch.setAdapter(adapter);
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


}