package org.selftravel.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.selftravel.R;
import org.selftravel.callback.TitleBarClickListener;

public class TitleBar extends RelativeLayout {

    private TitleBarClickListener listener;
    private TitleBarClickListener listener2;
    private ImageView backIv;
    private TextView titleTv;
    private TextView rightTv;


    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View root = View.inflate(getContext(), R.layout.layout_titlebar, this);
        backIv = (ImageView) root.findViewById(R.id.iv_back);
        titleTv = (TextView) root.findViewById(R.id.tv_title);
        rightTv = ((TextView) root.findViewById(R.id.tv_right));

        backIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) {
                    ((Activity) getContext()).finish();
                } else {
                    listener.onClick(v);
                }
            }
        });
        rightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener2 == null) {
                    ((Activity) getContext()).finish();
                } else {
                    listener2.onClick(v);
                }
            }
        });

    }

    public void setTitle(CharSequence title) {
        titleTv.setText(title);
    }


    public void setTitleBarBackClickListener(TitleBarClickListener listener) {
        this.listener = listener;
    }
    public void setsetRightTvClickListener(TitleBarClickListener listener2) {
        this.listener2 = listener2;
    }
    public void setRightBkg(int resId){
        if (rightTv.getVisibility() == View.GONE){
            rightTv.setVisibility(VISIBLE);
        }
        rightTv.setBackgroundResource(resId);
    }
    public void removeRightBkg(){
        if (rightTv.getVisibility() == View.GONE){
            rightTv.setVisibility(VISIBLE);
        }
        rightTv.setBackgroundResource(0);
    }
    public void setRightText(String text){
        if (rightTv.getVisibility() == View.GONE){
            rightTv.setVisibility(VISIBLE);
        }
        rightTv.setText(text);
    }

}
