package org.selftravel.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置所有Activity为竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //修改Activity的跳转动画
        int enterAnim = android.R.anim.slide_in_left;
        int exitAnim = android.R.anim.slide_out_right;
        overridePendingTransition(enterAnim, exitAnim);
    }
}
