package com.bwie.dianshang.view.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.dianshang.R;
import com.bwie.dianshang.model.bean.GoodsContentBean;
import com.bwie.dianshang.presenter.MainPresenter;
import com.bwie.dianshang.utils.Urls;
import com.bwie.dianshang.view.IView.IMainView;
import com.bwie.dianshang.view.IView.OnItemClickListener;
import com.bwie.dianshang.view.adapter.GoodsConnectAdapter;

import java.util.ArrayList;
import java.util.List;

public class GoodsContentActivity extends BaseActivity implements IMainView<GoodsContentBean> {


    private String mGcId;
    private MainPresenter mMainPresenter;
    private GoodsConnectAdapter adapter;
    private List<GoodsContentBean.DatasBean.GoodsListBean> mGoodsConnectList;
    private RecyclerView connectRecyclerView;
    private ImageView imgBack;
    private ImageView imgSearch;
    private TextView goodsSort;
    private TextView goodsPriority;
    private TextView goodsScreen;
    private CheckBox checkSwitchList;

    @Override
    int setMyContentView() {
        return R.layout.activity_goods_content;
    }

    @Override
    void initView() {
        connectRecyclerView = (RecyclerView) findViewById(R.id.goods_content_recycler);
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgSearch = (ImageView) findViewById(R.id.goods_content_search);
        goodsSort = (TextView) findViewById(R.id.goods_content_sort);
        goodsPriority = (TextView) findViewById(R.id.goods_content_priority);
        goodsScreen = (TextView) findViewById(R.id.goods_content_screen);
        checkSwitchList = (CheckBox) findViewById(R.id.goods_content_switch_list);
    }

    @Override
    void initDataFromServer() {
        if (!TextUtils.isEmpty(mGcId)) {
            if (mGcId.equals("587")) {
                mMainPresenter.loadDataFromGetServer(Urls.GOODS_CONNECT, GoodsContentBean.class, 0);
            }
        }

    }

    @Override
    void initData() {
        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);
        Intent intent = getIntent();
        mGcId = intent.getStringExtra("gc_id");
        mGoodsConnectList = new ArrayList<>();
        adapter = new GoodsConnectAdapter(mGoodsConnectList, this);
        connectRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        connectRecyclerView.setAdapter(adapter);
    }

    @Override
    void createEvent() {


        //左上角点击关闭当前页面
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //切换视图的点击事件
        checkSwitchList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /**
                 * 在这里点击切换视图的时候会复用holder
                 * 解决方法是先把recyclerview的adapter置空 然后再给它设置一次adapter
                 */
                if (isChecked) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(GoodsContentActivity.this,3);
                    connectRecyclerView.setLayoutManager(null);
                    connectRecyclerView.setAdapter(null);
                    connectRecyclerView.setAdapter(adapter);
                    connectRecyclerView.setLayoutManager(gridLayoutManager);
                    adapter.setData(isChecked);
                } else {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(GoodsContentActivity.this, LinearLayoutManager.VERTICAL, false);
                    connectRecyclerView.setLayoutManager(null);
                    connectRecyclerView.setAdapter(null);
                    connectRecyclerView.setAdapter(adapter);
                    connectRecyclerView.setLayoutManager(layoutManager);
                    adapter.setData(isChecked);
                }
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Intent intent = new Intent(GoodsContentActivity.this,DetailsActivity.class);
                intent.putExtra("goodsId",mGoodsConnectList.get(postion).getGoods_id());
                startActivity(intent);

            }
        });
    }

    @Override
    public void success(GoodsContentBean goodsContentBean, int code) {
        int code1 = goodsContentBean.getCode();
        if (code1 == 200 && code == 0) {
            mGoodsConnectList.addAll(goodsContentBean.getDatas().getGoods_list());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(String message) {

    }
}
