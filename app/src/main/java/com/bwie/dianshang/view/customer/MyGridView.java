package com.bwie.dianshang.view.customer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/19
 */
public class MyGridView extends GridView {
    public MyGridView(Context context) {
        this(context, null);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
