package org.selftravel.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.selftravel.R;
import org.selftravel.view.TitleBar;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_version)
public class VersionActivity extends BaseActivity {
    @ViewInject(R.id.title_bar)
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        titleBar.setTitle("版本");
    }


    @Event(value = R.id.btn_update)
    private void onClick(View v) {
        Toast.makeText(this, "已是最新版本", Toast.LENGTH_SHORT).show();
    }
}
