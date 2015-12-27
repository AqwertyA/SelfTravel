package org.selftravel.activity;

import android.os.Bundle;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.view.TitleBar;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_voice)
public class VoiceActivity extends BaseActivity{

    @ViewInject(R.id.title_bar)
    private TitleBar titleBar;
    @ViewInject(R.id.text)
    private TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    private void initView(){
        titleBar.setTitle("语音包管理");

    }

}
