package com.bwie.dianshang.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/12
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setMyContentView());
        initView();
        initData();
        initDataFromServer();
        addFragment();
        createEvent();
    }
    abstract int setMyContentView();

    abstract void initDataFromServer();

    abstract void initData();


    abstract void initView();

    abstract void createEvent();

    public void addFragment(){

    }

}
