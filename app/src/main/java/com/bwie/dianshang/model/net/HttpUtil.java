package com.bwie.dianshang.model.net;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/6
 */
public class HttpUtil {
    private NetDataCallBack mNetDataCallBack;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mNetDataCallBack.success(msg.obj);
                    break;
                case 1:
                    mNetDataCallBack.success(msg.obj);
                    break;
            }

        }
    };

    public <T> void initDatafromGetServer(String url, NetDataCallBack netDataCallBack, final Class<T> tClass) {
        mNetDataCallBack = netDataCallBack;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(4000, TimeUnit.MILLISECONDS)
                .readTimeout(4000, TimeUnit.MILLISECONDS)
                .writeTimeout(4000, TimeUnit.MILLISECONDS)
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mNetDataCallBack.error(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Message message = handler.obtainMessage();
                message.what = 0;
                Gson gson = new Gson();
                String result = response.body().string();
                T t = gson.fromJson(result, tClass);
                message.obj = t;
                handler.sendMessage(message);
            }
        });
    }


    public <T> void initDatafromPostServer(String url, NetDataCallBack netDataCallBack, final Class<T> tClass, RequestBody requestBody) {
        mNetDataCallBack = netDataCallBack;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(4000, TimeUnit.MILLISECONDS)
                .readTimeout(4000, TimeUnit.MILLISECONDS)
                .writeTimeout(4000, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mNetDataCallBack.error(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = handler.obtainMessage();
                message.what = 1;
                Gson gson = new Gson();
                String result = response.body().string();
                T t = gson.fromJson(result, tClass);
                message.obj = t;
                handler.sendMessage(message);
            }
        });


    }


}
