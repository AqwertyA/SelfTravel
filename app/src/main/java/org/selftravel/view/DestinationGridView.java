package org.selftravel.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 15-12-1.
 */
public class DestinationGridView extends GridView {
    public DestinationGridView(Context context) {
        super(context,null,0);
    }

    public DestinationGridView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public DestinationGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec;
        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT){
            expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        } else {
            expandSpec = heightMeasureSpec;
        }
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
