package org.selftravel.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.selftravel.R;

public class TextButton extends RelativeLayout {

    private TextView title;
    private ImageView arrow;

    public TextButton(Context context) {
        this(context, null);
    }

    public TextButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View root = View.inflate(getContext(), R.layout.layout_item_text_button, this);
        title = (TextView) root.findViewById(R.id.tv_title);
        arrow = (ImageView) root.findViewById(R.id.iv_arrow);
    }

    public void setTitle(CharSequence text) {
        title.setText(text);
    }

    public void changeImageView(int resId) {
        arrow.setImageResource(resId);
    }


}
