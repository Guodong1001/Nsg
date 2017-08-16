package com.bwie.dianshang.presenter;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/12
 */
public class BasePresenter<V> {
    private V mV;
    public void attachView(V v){
        this.mV = v;
    }
    public V getMvpView(){
        return mV;
    }
    public void dettachView(){
        mV = null;
    }
}
