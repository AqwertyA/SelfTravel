package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import org.selftravel.R;
import org.selftravel.adapter.SettingsAdapter;
import org.selftravel.app.SelfTravelApp;
import org.selftravel.callback.OnItemClickListener;
import org.selftravel.view.TitleBar;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2015/12/1.
 */
@ContentView(R.layout.activity_settings)
public class SettingsActivity extends BaseActivity implements OnItemClickListener, CompoundButton.OnCheckedChangeListener {
    @ViewInject(R.id.title_bar)
    private TitleBar titleBar;
    @ViewInject(R.id.list_view_settings)
    private ListView listView;
    @ViewInject(R.id.switch_msg_push)
    private Switch msgPushSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
        initData();
    }

    private void setListener() {
        msgPushSwitch.setOnCheckedChangeListener(this);
    }

    private void initData() {
        titleBar.setTitle("设置");
        SettingsAdapter adapter = new SettingsAdapter(this);
        listView.setAdapter(adapter);
        adapter.setListener(this);
    }

    private void initView() {
        x.view().inject(this);
    }

    @Override
    public void onItemClick(View v, int position) {
        switch (position) {
            case SettingsAdapter.SettingType.CHANGE_PWD:
                startActivity(new Intent(this, ChangePwdActivity.class));
                break;
            case SettingsAdapter.SettingType.VERSION:
                startActivity(new Intent(this, VersionActivity.class));
                break;
            case SettingsAdapter.SettingType.ABOUT_US:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case SettingsAdapter.SettingType.LOG_OUT:
                SelfTravelApp.logOut();
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            Toast.makeText(this, "已开启消息推送", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "已关闭消息推送", Toast.LENGTH_SHORT).show();
        }
    }
}
