package com.bwie.dianshang.presenter;

import com.bwie.dianshang.model.net.HttpUtil;
import com.bwie.dianshang.model.net.NetDataCallBack;
import com.bwie.dianshang.view.IView.IMainView;

import okhttp3.RequestBody;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/12
 */
public class MainPresenter extends BasePresenter<IMainView> implements NetDataCallBack {

    private int code;
    private HttpUtil httpUtil;

    public MainPresenter() {
        httpUtil = new HttpUtil();
    }

    public <T> void loadDataFromGetServer(String url, Class<T> t, int code) {
        this.code = code;
        httpUtil.initDatafromGetServer(url,this,t);
    }


    public <T> void loadDataFromPostServer(String url, Class<T> t, int code, RequestBody requestBody) {
        this.code = code;
        httpUtil.initDatafromPostServer(url,this,t,requestBody);
    }

    @Override
    public void success(Object o) {
        getMvpView().success(o,code);
    }

    @Override
    public void error(String error) {

    }
}
