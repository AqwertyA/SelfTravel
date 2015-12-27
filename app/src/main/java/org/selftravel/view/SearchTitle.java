package org.selftravel.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.selftravel.R;
import org.selftravel.activity.PositionActivity;
import org.selftravel.activity.SearchAcitivity;

/**
 * Created by Administrator on 2015/11/30.
 */
public class SearchTitle extends LinearLayout implements View.OnClickListener{

    private EditText search;
    private ImageView location;
    private boolean click = true;
    private LinearLayout linearLayout;

    public SearchTitle(Context context) {
        super(context);
        initView(context);
    }

    public SearchTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SearchTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);

        inflater.inflate(R.layout.layout_searchtitle,this);

        search = (EditText) findViewById(R.id.search);
        location = (ImageView) findViewById(R.id.location_btn);
        linearLayout = (LinearLayout) findViewById(R.id.all_bg);

        location.setOnClickListener(this);
        search.setOnClickListener(this);
        search.setCursorVisible(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:
                if(click == true){
                    Intent intent = new Intent(getContext(), SearchAcitivity.class);
                    getContext().startActivity(intent);
                }
                break;
            case R.id.location_btn:
                Intent intent = new Intent(getContext(), PositionActivity.class);
                getContext().startActivity(intent);
                break;
        }
    }

    public void setSearch(){
        search.setCursorVisible(true);
        click = false;
    }

    public void setBgColor(){
        linearLayout.setBackgroundColor(getResources().getColor(R.color.touming));

    }


}
