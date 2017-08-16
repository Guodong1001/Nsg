package com.bwie.dianshang.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bwie.dianshang.R;

public class GoodsRecommendActivity extends BaseActivity {

    private WebView mWebView;
    private ImageView imgBack;
    private String mUrl;

    @Override
    int setMyContentView() {
        return R.layout.activity_goods_recommend;
    }

    @Override
    void initDataFromServer() {
        if(!TextUtils.isEmpty(mUrl)){
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.loadUrl(mUrl);
        }

    }

    @Override
    void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");

    }

    @Override
    void initView() {
        mWebView = (WebView) findViewById(R.id.goods_recommend_webview);
        imgBack = (ImageView) findViewById(R.id.img_recommend_back);
    }

    @Override
    void createEvent() {

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
