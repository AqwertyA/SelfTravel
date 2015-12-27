package org.selftravel.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.selftravel.R;
import org.selftravel.adapter.Destination_GridViewAdapter;
import org.selftravel.beans.DestinationBeans;
import org.selftravel.http.HttpUrl;
import org.selftravel.view.DestinationGridView;
import org.selftravel.view.SearchTitle;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.List;

public class DestinationFragment extends BaseFragment  {

    private DestinationGridView recommend,hotcity,hotspot;
    private SearchTitle title;
    @Nullable
     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_destination, container, false);

        title = (SearchTitle)root.findViewById(R.id.destination_top);

        recommend = (DestinationGridView)root.findViewById(R.id.destination_recommend);
        hotcity = (DestinationGridView)root.findViewById(R.id.destination_hotcity);
        hotspot = (DestinationGridView)root.findViewById(R.id.destination_hotspot);


       getGridViewData(HttpUrl.DESTINATION_RECOMMEND, recommend);
       getGridViewData(HttpUrl.HOT_CITY,hotcity);
       getGridViewData(HttpUrl.HOT_SPOT,hotspot);


        return root;
    }

    private void getGridViewData(String url, final GridView view) {
        RequestParams params = new RequestParams(url);
        final Destination_GridViewAdapter gridViewAdapter = new Destination_GridViewAdapter(getActivity(),null);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray data = new JSONObject(result).getJSONArray("data");
                    Gson gson = new Gson();
                    Type typeList = new TypeToken<List<DestinationBeans.DataEntity>>(){}.getType();
                    List<DestinationBeans.DataEntity> dataEntity = gson.fromJson(data.toString(),typeList);
                    gridViewAdapter.updateRes(dataEntity);
                    view.setAdapter(gridViewAdapter);
                    gridViewAdapter.notify();

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
