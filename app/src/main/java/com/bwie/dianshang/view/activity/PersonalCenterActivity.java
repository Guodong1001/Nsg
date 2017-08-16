package com.bwie.dianshang.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bwie.dianshang.R;
import com.bwie.dianshang.utils.ConstantsUtil;

public class PersonalCenterActivity extends BaseActivity {
    private ImageView personBackimage;
    private Button personExitLogin;


    @Override
    int setMyContentView() {
        return R.layout.activity_personal_center;
    }

    @Override
    void initDataFromServer() {

    }

    @Override
    void initData() {

    }

    @Override
    void initView() {
        personBackimage = (ImageView) findViewById(R.id.person_back);
        personExitLogin = (Button) findViewById(R.id.person_exit_login);
    }

    @Override
    void createEvent() {
        /**
         * 右上角返回按钮的点击事件
         */
        personBackimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**
         * 退出按钮的点击事件
         * 弹出一个dialog提示用户是否确认退出登录
         */
        personExitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PersonalCenterActivity.this)
                        .setTitle("确认您的选择")
                        .setMessage("退出登录？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //把判断是否登录的一个值改为false   代表没有登录
                                UserLoginActivity.LOGIN_CODE = false;
                                //跳转到mainActivity   并且传一些相应的值用来修改用户界面的布局文字
                                Intent intent = new Intent(PersonalCenterActivity.this, MainActivity.class);
                                intent.putExtra(ConstantsUtil.FLAG, "userexitlogin");
                                intent.putExtra(ConstantsUtil.USERNAME, "点击登录");
                                intent.putExtra(ConstantsUtil.GOODS, "商品收藏");
                                intent.putExtra(ConstantsUtil.STORE, "商品店铺");
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create()
                        .show();
            }
        });
    }
}
