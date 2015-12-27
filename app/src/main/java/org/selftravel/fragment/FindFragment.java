package org.selftravel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.selftravel.R;
import org.selftravel.activity.WebViewActivity;
import org.selftravel.adapter.FindAdapter;
import org.selftravel.app.SelfTravelApp;
import org.selftravel.beans.FindBeans;
import org.selftravel.http.HttpUrl;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private static final String TAG = FindFragment.class.getSimpleName();
    private ListView findListView;
    private List<FindBeans.DataEntity> data;
    private FindAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_find, container, false);
        initView(root);
        initData();
        setListener();
        return root;
    }

    private void initView(View root) {
        findListView = (ListView) root.findViewById(R.id.list_view_find);
    }

    private void initData() {
        Toast.makeText(getContext(), "initData", Toast.LENGTH_SHORT).show();
        int type = 0;
        int limit = 10;
        String url = String.format(HttpUrl.FIND, limit, type);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                FindBeans beans = new Gson().fromJson(result, FindBeans.class);
                if (beans.getStatus() == 0) {
                    Log.d(TAG, result);
                    data = beans.getData();
                } else {
                    data = new ArrayList<>();
                }
                adapter = new FindAdapter(data, getActivity());
                findListView.setAdapter(adapter);
//                for (FindBeans.DataEntity d : data) {
//                    try {
//                        x.getDb(SelfTravelApp.daoConfig).save(d);
//                    } catch (DbException e) {
//                        Toast.makeText(getActivity(), "db error", Toast.LENGTH_SHORT).show();
//                    }
//                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "cancelled", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void setListener() {
        findListView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url = adapter.getWapUrl(position);
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", "专题活动");
        startActivity(intent);
    }
}
