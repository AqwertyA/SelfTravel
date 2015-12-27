package org.selftravel.activity;

import android.os.Bundle;

import org.selftravel.R;
import org.selftravel.view.TitleBar;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_about)
public class AboutActivity extends BaseActivity {
    @ViewInject(R.id.title_bar)
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        titleBar.setTitle("关于我们");
    }
}
