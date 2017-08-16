package com.bwie.dianshang.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bwie.dianshang.R;
import com.bwie.dianshang.utils.ConstantsUtil;
import com.bwie.dianshang.utils.IntentUtil;
import com.bwie.dianshang.view.activity.PersonalCenterActivity;
import com.bwie.dianshang.view.activity.UserLoginActivity;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/12
 */
public class UserFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private TextView headimage;
    private TextView collectionGoods;
    private TextView collectionStore;
    private TextView collectionFootprint;
    private TextView allOrder;
    private TextView orderWaitPay;
    private TextView orderWaitDrive;
    private TextView orderWaitReceipt;
    private TextView orderWaitRefund;
    private TextView orderWaitComment;
    private TextView property;
    private TextView propertyMoney;
    private TextView propertyCard;
    private TextView propertyVouchers;
    private TextView propertyRed;
    private TextView propertyIntegral;
    private TextView address;
    private TextView setting;
    private TextView[] textviews = new TextView[]{headimage, collectionGoods, collectionStore, collectionFootprint, allOrder
            , orderWaitPay, orderWaitDrive, orderWaitReceipt, orderWaitComment, orderWaitRefund, property
            , propertyMoney, propertyCard, propertyVouchers, propertyRed, propertyIntegral, address, setting};
    private int[] ids = new int[]{R.id.user_head_image, R.id.user_collection_goods, R.id.user_collection_store
            , R.id.user_collection_footprint, R.id.user_order, R.id.user_order_wait_pay, R.id.user_order_wait_drive
            , R.id.user_order_wait_receipt, R.id.user_order_wait_comment, R.id.user_order_wait_refund, R.id.user_property
            , R.id.user_property_money, R.id.user_property_card, R.id.user_property_vouchers, R.id.user_property_red, R.id.user_property_integral
            , R.id.user_address, R.id.user_setting};
    private String mUsername;
    private String mStore;
    private String mGoods;


    @Override
    int setLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = getView();
        initDataIntent();
        initView();
        initData();
    }

    private void initDataIntent() {
        Bundle bundle = getArguments();
        mUsername = bundle.getString(ConstantsUtil.USERNAME);
        mStore = bundle.getString(ConstantsUtil.STORE);
        mGoods = bundle.getString(ConstantsUtil.GOODS);
    }

    private void initData() {

    }

    private void initView() {
        /**
         * 把控件和id分别放入textviews和ids数组中
         * 通过遍历数组findViewById找到每个控件
         * 并且直接给这个控件设置点击事件
         */
        for (int i = 0; i < textviews.length; i++) {
            textviews[i] = (TextView) view.findViewById(ids[i]);
            switch (i){
                case 0:
                    textviews[i].setText(mUsername);
                    if(TextUtils.isEmpty(mUsername)){
                        textviews[i].setText("点击登录");
                    }
                    break;
                case 1:
                    textviews[i].setText(mGoods);
                    if(TextUtils.isEmpty(mUsername)){
                        textviews[i].setText("商品收藏");
                    }
                    break;
                case 2:
                    textviews[i].setText(mStore);
                    if(TextUtils.isEmpty(mUsername)){
                        textviews[i].setText("商品店铺");
                    }
                    break;
                default:
                    break;
            }
            textviews[i].setOnClickListener(this);
        }
    }

    /**
     * 所有控件的点击事件  用switch区分
     *
     * @param v
     */
    public void onClick(View v) {
        //判断用户是否登录
        //如果没有登录就全部的点击事件都跳转到登录界面
        //并且return出点击事件
        if (!UserLoginActivity.LOGIN_CODE) {
            IntentUtil.setIntent(getMyContext(),UserLoginActivity.class);
            return;
        }
        //根据点击的控件不同做相应的点击事件
        switch (v.getId()) {
            case R.id.user_head_image://用户头像
                IntentUtil.setIntent(getMyContext(),PersonalCenterActivity.class);
                break;
            case R.id.user_collection_goods://商品收藏

                break;
            case R.id.user_collection_store://商品店铺

                break;
            case R.id.user_collection_footprint://我的足迹

                break;
            case R.id.user_order://全部订单

                break;
            case R.id.user_order_wait_pay://未付款

                break;
            case R.id.user_order_wait_drive://待发货

                break;
            case R.id.user_order_wait_receipt://待收货

                break;
            case R.id.user_order_wait_comment://待评价

                break;
            case R.id.user_order_wait_refund://退款、货

                break;
            case R.id.user_property://我的财产

                break;
            case R.id.user_property_money://预存款

                break;
            case R.id.user_property_card://充值卡

                break;
            case R.id.user_property_vouchers://代金券

                break;
            case R.id.user_property_red://红包

                break;
            case R.id.user_property_integral://积分

                break;
            case R.id.user_address://收货地址

                break;
            case R.id.user_setting://系统设置

                break;

        }
    }
}
