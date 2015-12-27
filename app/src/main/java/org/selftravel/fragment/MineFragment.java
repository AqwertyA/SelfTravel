package org.selftravel.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.selftravel.R;
import org.selftravel.activity.AboutActivity;
import org.selftravel.activity.AlbumActivity;
import org.selftravel.activity.AttentionActivity;
import org.selftravel.activity.FootPrintActivity;
import org.selftravel.activity.LikeActivity;
import org.selftravel.activity.LoginActivity;
import org.selftravel.activity.MainActivity;
import org.selftravel.activity.MessageActivity;
import org.selftravel.activity.MyInfoActivity;
import org.selftravel.activity.SettingsActivity;
import org.selftravel.activity.VoiceActivity;
import org.selftravel.adapter.MineAdapter;
import org.selftravel.app.SelfTravelApp;
import org.selftravel.callback.OnItemClickListener;

public class MineFragment extends BaseFragment implements View.OnClickListener, OnItemClickListener {

    private static final String TAG = MineFragment.class.getSimpleName();
    private MineAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private TextView login;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mine, container, false);
        init(root);
        return root;
    }

    public void init(View view) {
        recyclerView = ((RecyclerView) view.findViewById(R.id.my_rv));
        manager = new GridLayoutManager(getActivity(), 4);
        recyclerView.setLayoutManager(manager);
        adapter = new MineAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        login = ((TextView) view.findViewById(R.id.head));
        login.setOnClickListener(this);


        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head:
                if (SelfTravelApp.isLoggedIn()) {
                    jump(MyInfoActivity.class);
                } else {
                    getActivity().startActivityForResult(new Intent(getActivity(), LoginActivity.class), MainActivity.REQUEST_LOGIN);
                }
                break;
        }
    }

    @Override
    public void onItemClick(View v, int position) {
        switch (position) {
            case 0:
                Toast.makeText(getActivity(), "第" + (position + 1) + "个被点了", Toast.LENGTH_SHORT).show();
                jump(VoiceActivity.class);
                break;
            case 1:
                Toast.makeText(getActivity(), "第" + (position + 1) + "个被点了", Toast.LENGTH_SHORT).show();
                jump(AttentionActivity.class);
                break;
            case 2:
                Toast.makeText(getActivity(), "第" + (position + 1) + "个被点了", Toast.LENGTH_SHORT).show();
                jump(AlbumActivity.class);
                break;
            case 3:
                Toast.makeText(getActivity(), "第" + (position + 1) + "个被点了", Toast.LENGTH_SHORT).show();
                jump(MessageActivity.class);
                break;
            case 4:
                Toast.makeText(getActivity(), "第" + (position + 1) + "个被点了", Toast.LENGTH_SHORT).show();
                jump(LikeActivity.class);
                break;
            case 5:
                Toast.makeText(getActivity(), "第" + (position + 1) + "个被点了", Toast.LENGTH_SHORT).show();
                jump(FootPrintActivity.class);
                break;
            case 6:
                Toast.makeText(getActivity(), "第" + (position + 1) + "个被点了", Toast.LENGTH_SHORT).show();
                jump(AboutActivity.class);
                break;
            case 7:
                Toast.makeText(getActivity(), "第" + (position + 1) + "个被点了", Toast.LENGTH_SHORT).show();
                jump(SettingsActivity.class);
                break;
        }
    }

    private void jump(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SelfTravelApp.isLoggedIn()) {
            login.setText(SelfTravelApp.getUserInfo().getAccount());
        } else {
            login.setText("登录");
        }
    }
}
