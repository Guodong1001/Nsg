package com.bwie.dianshang.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.dianshang.R;
import com.bwie.dianshang.model.bean.DataBean;
import com.bwie.dianshang.model.bean.DatasBean;
import com.bwie.dianshang.model.bean.GoodsClassBean;
import com.bwie.dianshang.presenter.MainPresenter;
import com.bwie.dianshang.utils.Urls;
import com.bwie.dianshang.view.IView.IMainView;
import com.bwie.dianshang.view.adapter.ClassOneAdapter;
import com.bwie.dianshang.view.adapter.ClassThreeAdapter;
import com.bwie.dianshang.view.adapter.ClassTwoAdapter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/12
 */
public class ClassFragment extends BaseFragment implements IMainView<DataBean> {
    private View view;
    private ListView listviewOne;
    private ListView listviewTwo;
    private List<GoodsClassBean> mGoodsClassBeanList = new ArrayList<>();
    private List<GoodsClassBean> mGoodsClassBeanList2 = new ArrayList<>();
    private List<GoodsClassBean> mGoodsClassBeanList3 = new ArrayList<>();
    private ClassOneAdapter classOneAdapter;
    private ClassTwoAdapter classTwoAdapter;
    private ClassThreeAdapter classThreeAdapter;
    private MainPresenter mMainPresenter;
    private ImageView zXingimage;
    private final int LIST1 = 1;
    private final int LIST2 = 2;
    private final int LIST3 = 3;
    private final int REQUEST_CODE = 5;
    private int positions;
    

    @Override
    int setLayout() {
        return R.layout.fragment_class;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = getView();
        initDataFromServer();
        initView();
        initData();

    }

    private void initData() {
        //一级列表的listview，并且设置adapter
        classOneAdapter = new ClassOneAdapter(getContext(), mGoodsClassBeanList);
        listviewOne.setAdapter(classOneAdapter);
        //二级列表的listview，并且设置adapter
        classTwoAdapter = new ClassTwoAdapter(getContext(), mGoodsClassBeanList2);
        listviewTwo.setAdapter(classTwoAdapter);
    }

    private void initView() {
        listviewOne = (ListView) view.findViewById(R.id.listview_one);
        listviewTwo = (ListView) view.findViewById(R.id.listview_two);
        zXingimage = (ImageView) view.findViewById(R.id.class_search_title).findViewById(R.id.zxing_img);
        //一级列表点击item事件
        listviewOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                classTwoAdapter.setData(mGoodsClassBeanList3, -1);
                initDataFromServerClass2(position);
                positions = position;
            }
        });
        //二级列表点击item事件
        listviewTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                classThreeAdapter = new ClassThreeAdapter(getContext(), mGoodsClassBeanList3);
                initDataFromServerClass3(position, positions);
                classTwoAdapter.setData(mGoodsClassBeanList3, position);

            }
        });

        //扫描二维码
        zXingimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMyContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    //三级列表获取数据
    private void initDataFromServerClass3(int position, int positions) {
        GoodsClassBean goodsClassBean = mGoodsClassBeanList.get(positions);
        GoodsClassBean goodsClassBean1 = mGoodsClassBeanList2.get(position);
        String gc_id = goodsClassBean.getGc_id();
        String gc_id1 = goodsClassBean1.getGc_id();
        mGoodsClassBeanList3.clear();
        mMainPresenter.loadDataFromGetServer(Urls.LINK_MOBILE_CLASS + "&gc_id=" + gc_id + "&gc_id=" + gc_id1, DataBean.class, LIST3);
    }

    //一级列表获取数据
    private void initDataFromServer() {
        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);
        mGoodsClassBeanList2.clear();
        mMainPresenter.loadDataFromGetServer(Urls.LINK_MOBILE_CLASS, DataBean.class, LIST1);
    }

    //二级列表获取数据
    public void initDataFromServerClass2(int position) {
        GoodsClassBean goodsClassBean = mGoodsClassBeanList.get(position);
        String gc_id = goodsClassBean.getGc_id();
        mGoodsClassBeanList2.clear();
        mMainPresenter.loadDataFromGetServer(Urls.LINK_MOBILE_CLASS + "&gc_id=" + gc_id, DataBean.class, LIST2);

    }


    @Override
    public void success(DataBean dataBean, int code) {
        if (dataBean != null) {
            DatasBean datas = dataBean.getDatas();
            if (datas != null) {
                switch (code) {
                    case LIST1:
                        mGoodsClassBeanList.addAll(datas.getClass_list());
                        classOneAdapter.notifyDataSetChanged();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mMainPresenter.loadDataFromGetServer(Urls.LINK_MOBILE_CLASS + "&gc_id=" + 1, DataBean.class, LIST2);
                            }
                        });

                        break;
                    case LIST2:
                        mGoodsClassBeanList2.addAll(datas.getClass_list());
                        classTwoAdapter.notifyDataSetChanged();
                        break;
                    case LIST3:
                        mGoodsClassBeanList3.addAll(datas.getClass_list());
                        classTwoAdapter.notifyDataSetChanged();
                        break;
                }
            }
        }
    }

    @Override
    public void error(String message) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getMyContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getMyContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMainPresenter.dettachView();
    }
}
