package com.bwie.dianshang.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/18
 */
public abstract class BaseFragment extends Fragment {
    private View view;
    private Context mContext;

    public Context getMyContext(){

        return mContext;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayout(),container,false);
        mContext = getActivity();
        return view;
    }
    abstract int setLayout();
}
