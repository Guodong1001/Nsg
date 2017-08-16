package com.bwie.dianshang.view.IView;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/12
 */
public interface IMainView<T> {
    void success(T t,int code);
    void error(String message);
}
