package com.bwie.dianshang.model.net;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/6
 */
public interface NetDataCallBack<T> {
    void success(T t);
    void error(String error);
}
