package com.scottfu.sflibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by fujindong on 2017/8/1.
 */

public class HeightGirdView extends GridView {


    public HeightGirdView(Context context) {
        super(context);
    }

    public HeightGirdView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public HeightGirdView(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
