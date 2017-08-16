package com.bwie.dianshang.utils;

import android.content.Context;
import android.content.Intent;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/20
 */
public class IntentUtil {
    public static <T> void setIntent(Context context,Class<T> activityClass) {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }
}
