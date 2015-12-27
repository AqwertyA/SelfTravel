package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import org.selftravel.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 15-12-1.
 */
@ContentView(R.layout.activity_album)
public class AlbumActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = AlbumActivity.class.getSimpleName();

    @ViewInject(R.id.iv_back)
    private ImageView back;
    @ViewInject(R.id.add)
    private ImageView add;
    @ViewInject(R.id.listview_album)
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }
    public void initView(){
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.add:
                Intent intent = new Intent(this,AlbumAddActivity.class);
                startActivity(intent);
                break;

        }
    }



}
