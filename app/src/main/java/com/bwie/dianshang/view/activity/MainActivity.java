package com.bwie.dianshang.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bwie.dianshang.R;
import com.bwie.dianshang.utils.ConstantsUtil;
import com.bwie.dianshang.view.fragment.CartFragment;
import com.bwie.dianshang.view.fragment.ClassFragment;
import com.bwie.dianshang.view.fragment.HomeFragment;
import com.bwie.dianshang.view.fragment.UserFragment;

public class MainActivity extends BaseActivity {


    private RadioGroup mRadioGroup;
    private FragmentManager fm;
    private Fragment[] fragments = new Fragment[4];
    //记录用户首次点击返回键的时间
    private long firstTime = 0;
    private String mGoods;
    private String mStore;
    private String mUsername;

    @Override
    int setMyContentView() {
        return R.layout.activity_main;
    }

    @Override
    void initDataFromServer() {
    }

    @Override
    void initData() {
        //获取到intent传过来的flag值判断是谁传过来的数据
        Intent intent = getIntent();
        //首先获取传过来的flag值
        String flag = intent.getStringExtra(ConstantsUtil.FLAG);
        //判断flag值   看是哪个activity传过来的  然后分别做不同的获取值操作
        if (!TextUtils.isEmpty(flag)) {
            if (flag.equals("userlogin")) {
                mGoods = intent.getStringExtra(ConstantsUtil.GOODS);
                mStore = intent.getStringExtra(ConstantsUtil.STORE);
                mUsername = intent.getStringExtra(ConstantsUtil.USERNAME);
            } else if (flag.equals("userexitlogin")) {
                mGoods = intent.getStringExtra(ConstantsUtil.GOODS);
                mStore = intent.getStringExtra(ConstantsUtil.STORE);
                mUsername = intent.getStringExtra(ConstantsUtil.USERNAME);
            }
        }

    }

    /**
     * 初始化控件的方法
     */
    @Override
    void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        //RadioButton的点击事件   切换fragment
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                int i = Integer.parseInt(rb.getTag().toString());
                //判断如果点击的按钮下标为3时  再判断是否添加过当前fragment
                //如果添加过再传值的时候就会报错
                if (i == 3) {
                    if (!fragments[i].isAdded()) {
                        Bundle bundle = new Bundle();
                        bundle.putString(ConstantsUtil.GOODS, mGoods);
                        bundle.putString(ConstantsUtil.STORE, mStore);
                        bundle.putString(ConstantsUtil.USERNAME, mUsername);
                        fragments[i].setArguments(bundle);
                    }
                }
                hideFragment(i);
            }
        });
    }

    @Override
    void createEvent() {

    }


    /**
     * 添加fragment
     */
    @Override
    public void addFragment() {
        fragments[0] = new HomeFragment();
        fragments[1] = new ClassFragment();
        fragments[2] = new CartFragment();
        fragments[3] = new UserFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_framelayout, fragments[0], "0");
        ft.commit();
    }

    /**
     * 判断fragment是否添加过并且隐藏不需要显示的
     *
     * @param i
     */
    public void hideFragment(int i) {
        FragmentTransaction ft = fm.beginTransaction();
        //如果fragment没有添加过就添加进去
        if (!fragments[i].isAdded()) {
            ft.add(R.id.main_framelayout, fragments[i], "" + i);
        }
        for (int j = 0; j < fragments.length; j++) {
            //找到radiogroup里的子控件
            RadioButton rb = (RadioButton) mRadioGroup.getChildAt(j);
            //给这个子控件的文字设置背景颜色    黑色  （没有选择的时候）
            rb.setTextColor(Color.BLACK);
            //通过循环隐藏所有的fragment
            ft.hide(fragments[j]);
            //如果当前radiobutton是选中状态就把文字颜色设置成红色的
            if (rb.isChecked()) {
                rb.setTextColor(Color.RED);
            }
            //如果点击的为用户，那么就传值到用户的fragment
        }
        //显示当前点击按钮相对应的fragment
        ft.show(fragments[i]);
        ft.commit();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //再按一次退出程序
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
