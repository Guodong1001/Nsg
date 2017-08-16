package com.bwie.dianshang.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.dianshang.R;
import com.bwie.dianshang.model.bean.CartGoodsBean;
import com.bwie.dianshang.utils.MessageEvent;
import com.bwie.dianshang.view.activity.UserLoginActivity;
import com.bwie.dianshang.view.adapter.CartAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.bwie.dianshang.view.activity.UserLoginActivity.LOGIN_CODE;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/12
 */
public class CartFragment extends BaseFragment{
    private List<CartGoodsBean> mList;
    private CheckBox mTotalCheck;


    @Override
    int setLayout() {
        if (LOGIN_CODE){
            return R.layout.fragment_cart;
        }else {
            return R.layout.fragment_cart_no_login;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initView();
    }

    protected void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(new CartGoodsBean("商品"+i,100+i,1,false));
        }
        EventBus.getDefault().register(this);
    }

    protected void initView() {
        if (!LOGIN_CODE){
            TextView txtLogin = (TextView) getView().findViewById(R.id.go_login);
            txtLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), UserLoginActivity.class));
                    return;
                }
            });
        }else {
            mTotalCheck = (CheckBox) getView().findViewById(R.id.cart_total_check);
            TextView totalSum = (TextView) getView().findViewById(R.id.cart_tv_total_sum);
            final Button pushBtn = (Button) getView().findViewById(R.id.cart_btn_pushsum);
            ListView cartLv = (ListView) getView().findViewById(R.id.cart_fragment_lv);
            final CartAdapter adapter = new CartAdapter(getActivity());
            adapter.setData(mList);
            cartLv.setAdapter(adapter);

            mTotalCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        for (CartGoodsBean cartGoodsBean : mList) {
                            cartGoodsBean.setChecked(isChecked);
                        }
                        pushBtn.setText("结算（"+mList.size()+"）");
                        adapter.notifyDataSetChanged();
                    }else {
                        for (CartGoodsBean cartGoodsBean : mList) {
//                            cartGoodsBean.isChecked();
                            cartGoodsBean.setChecked(isChecked);
                        }
                        pushBtn.setText("结算（"+0+"）");
                        adapter.notifyDataSetChanged();
                    }

                }
            });
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent){
        if (messageEvent.isCheckAll()){
            mTotalCheck.setChecked(true);
        }else{
            mTotalCheck.setChecked(false);
        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
