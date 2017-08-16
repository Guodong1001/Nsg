package com.bwie.dianshang.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.dianshang.R;
import com.bwie.dianshang.model.bean.DetailsBean;
import com.bwie.dianshang.presenter.MainPresenter;
import com.bwie.dianshang.utils.Urls;
import com.bwie.dianshang.view.IView.IMainView;
import com.bwie.dianshang.view.IView.OnItemClickListener;
import com.bwie.dianshang.view.adapter.GoodsCommendAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends BaseActivity implements IMainView<DetailsBean> {

    private ImageView mImgBack;
    private TextView mTitleLogin;
    private ImageView mDetailsCollection;
    private ImageView mDetailsShare;
    private ImageView mDetailsImage;
    private TextView mDetailsName;
    private TextView mDetailsPrice;
    private TextView mDetailsSalenum;
    private TextView mDetailsTransport;
    private TextView mDetailsNationwide;
    private TextView mDetailsCargo;
    private TextView mDetailsFreeShipping;
    private TextView mDetailsEvaluate;
    private TextView mDetailsGoodReputation;
    private TextView mDetailsEvaluatenum;
    private LinearLayout mDetailsComment;
    private TextView mDetailsGoodsIntroduce;
    private TextView mDetailsGoodsV5;
    private TextView mDetailsDescribe;
    private TextView mDetailsServe;
    private TextView mDetailsLogistics;
    private RecyclerView mDetailsRecycler;
    private TextView mDetailsTvService;
    private TextView mDetailsTvCart;
    private TextView mDetailsTvAddCart;
    private TextView mDetailsTvStore;
    private ScrollView mScrollView;
    private String mGoodsId;
    private MainPresenter mMainPresenter;
    private List<DetailsBean.DatasBean.GoodsCommendListBean> mList;
    private GoodsCommendAdapter adapter;
    private DetailsBean.DatasBean.GoodsInfoBean mGoodsInfo;

    @Override
    int setMyContentView() {
        return R.layout.activity_details;
    }

    @Override
    void initDataFromServer() {
        if (!TextUtils.isEmpty(mGoodsId)) {
            getNet(mGoodsId);
        }
    }
    public void getNet(String mGoodsId){
        mMainPresenter.loadDataFromGetServer(Urls.GOODS_DETAIL + mGoodsId, DetailsBean.class, 0);
    }

    @Override
    void initData() {
        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);
        Intent intent = getIntent();
        mGoodsId = intent.getStringExtra("goodsId");
        mList = new ArrayList<>();
        adapter = new GoodsCommendAdapter(mList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mDetailsRecycler.setLayoutManager(layoutManager);
        mDetailsRecycler.setAdapter(adapter);
    }

    @Override
    void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_back);
        mTitleLogin = (TextView) findViewById(R.id.title_login);
        mDetailsCollection = (ImageView) findViewById(R.id.details_collection);
        mDetailsShare = (ImageView) findViewById(R.id.details_share);
        mDetailsImage = (ImageView) findViewById(R.id.details_image);
        mDetailsName = (TextView) findViewById(R.id.details_name);
        mDetailsPrice = (TextView) findViewById(R.id.details_price);
        mDetailsSalenum = (TextView) findViewById(R.id.details_salenum);
        mDetailsTransport = (TextView) findViewById(R.id.details_transport);
        mDetailsNationwide = (TextView) findViewById(R.id.details_nationwide);
        mDetailsCargo = (TextView) findViewById(R.id.details_cargo);
        mDetailsFreeShipping = (TextView) findViewById(R.id.details_free_shipping);
        mDetailsEvaluate = (TextView) findViewById(R.id.details_evaluate);
        mDetailsGoodReputation = (TextView) findViewById(R.id.details_good_reputation);
        mDetailsEvaluatenum = (TextView) findViewById(R.id.details_evaluatenum);
        mDetailsComment = (LinearLayout) findViewById(R.id.details_comment);
        mDetailsGoodsIntroduce = (TextView) findViewById(R.id.details_goods_introduce);
        mDetailsGoodsV5 = (TextView) findViewById(R.id.details_goods_v5);
        mDetailsDescribe = (TextView) findViewById(R.id.details_describe);
        mDetailsServe = (TextView) findViewById(R.id.details_serve);
        mDetailsLogistics = (TextView) findViewById(R.id.details_logistics);
        mDetailsRecycler = (RecyclerView) findViewById(R.id.details_recycler);
        mDetailsTvService = (TextView) findViewById(R.id.details_tv_service);
        mDetailsTvCart = (TextView) findViewById(R.id.details_tv_cart);
        mDetailsTvAddCart = (TextView) findViewById(R.id.details_tv_add_cart);
        mDetailsTvStore = (TextView) findViewById(R.id.details_tv_store);
        mScrollView = (ScrollView) findViewById(R.id.scrollview);
    }

    @Override
    void createEvent() {
        /**
         * 左上角返回按钮点击事件
         */
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                String goodsId = mList.get(postion).getGoods_id();
                Intent intent = new Intent(DetailsActivity.this,DetailsActivity.class);
                intent.putExtra("goodsId",goodsId);
                startActivity(intent);

//                mList.clear();
//                getNet(goodsId);
//                mScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
        /**
         * 商品介绍的点击事件
         */
        mDetailsGoodsIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goodsUrl = mGoodsInfo.getGoods_url();
//                String mUrl = goodsUrl.replace("localhost", "10.0.2.2");
                Intent intent = new Intent(DetailsActivity.this,GoodsRecommendActivity.class);
                intent.putExtra("url",goodsUrl);
                startActivity(intent);
            }
        });

    }

    @Override
    public void success(DetailsBean detailsBean, int code) {
        int code1 = detailsBean.getCode();
        if (code1 == 200) {
            initSetData(detailsBean);
        }

    }

    private void initSetData(DetailsBean detailsBean) {
        final DetailsBean.DatasBean datas = detailsBean.getDatas();
        mGoodsInfo = datas.getGoods_info();
        final DetailsBean.DatasBean.GoodsHairInfoBean goodsHairInfo = datas.getGoods_hair_info();
        final DetailsBean.DatasBean.StoreInfoBean storeInfo = datas.getStore_info();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(DetailsActivity.this).load(datas.getGoods_image()).into(mDetailsImage);
                mDetailsName.setText(mGoodsInfo.getGoods_name());
                mDetailsPrice.setText("￥" + mGoodsInfo.getGoods_promotion_price());
                mDetailsSalenum.setText("已售：" + mGoodsInfo.getGoods_salenum() + "件");

                mDetailsNationwide.setText(goodsHairInfo.getArea_name());
                mDetailsCargo.setText(goodsHairInfo.getIf_store_cn());
                mDetailsFreeShipping.setText(goodsHairInfo.getContent());

                mDetailsDescribe.setText(storeInfo.getStore_credit().getStore_desccredit().getText() + ":" + storeInfo.getStore_credit().getStore_desccredit().getCredit());
                mDetailsServe.setText(storeInfo.getStore_credit().getStore_servicecredit().getText() + ":" + storeInfo.getStore_credit().getStore_servicecredit().getCredit());
                mDetailsLogistics.setText(storeInfo.getStore_credit().getStore_deliverycredit().getText() + ":" + storeInfo.getStore_credit().getStore_deliverycredit().getCredit());
                mDetailsGoodsV5.setText(storeInfo.getStore_name());

                mList.addAll(datas.getGoods_commend_list());
                adapter.notifyDataSetChanged();


            }
        });
    }

    @Override
    public void error(String message) {

    }
}
