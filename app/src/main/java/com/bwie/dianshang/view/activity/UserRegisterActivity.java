package com.bwie.dianshang.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwie.dianshang.R;
import com.bwie.dianshang.model.bean.RegisterandLoginBean;
import com.bwie.dianshang.presenter.MainPresenter;
import com.bwie.dianshang.utils.CheckTextUtil;
import com.bwie.dianshang.utils.ToastUtil;
import com.bwie.dianshang.utils.Urls;
import com.bwie.dianshang.view.IView.IMainView;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class UserRegisterActivity extends BaseActivity implements IMainView<RegisterandLoginBean> {
    private ImageView imageBack;
    private EditText username;
    private EditText password;
    private EditText againpassword;
    private EditText email;
    private Button btnRegister;
    private MainPresenter mainPresenter;
    private String susername;
    private String spassword;
    private String sagainpassword;
    private String semail;
    private UserRegisterActivity registerActivity = this;
    public static final String USERNAME = "username";


    /**
     * 初始化控件
     */
    @Override
    void initView() {
        imageBack = (ImageView) findViewById(R.id.register_back);
        username = (EditText) findViewById(R.id.register_username);
        password = (EditText) findViewById(R.id.register_password);
        againpassword = (EditText) findViewById(R.id.register_password_again);
        email = (EditText) findViewById(R.id.register_email);
        btnRegister = (Button) findViewById(R.id.register_btn);

    }

    @Override
    void createEvent() {


        //左上角返回按钮的点击事件
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出一个对话框
                new AlertDialog.Builder(UserRegisterActivity.this)
                        .setTitle("确认您的选择")
                        .setMessage("返回将清空您正在输入的内容")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create()
                        .show();
            }
        });
        //注册按钮的点击事件
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                susername = username.getText().toString();
                spassword = password.getText().toString();
                sagainpassword = againpassword.getText().toString();
                semail = email.getText().toString();
                if (TextUtils.isEmpty(susername)) {
                    ToastUtil.showShort(registerActivity, "用户名不能为空");
                    return;
                }

                if (TextUtils.isEmpty(spassword)) {
                    ToastUtil.showShort(registerActivity, "密码不能为空");
                    return;
                }

                if (TextUtils.isEmpty(sagainpassword)) {
                    ToastUtil.showShort(registerActivity, "请再次输入密码");
                    return;
                }

                if (TextUtils.isEmpty(semail)) {
                    ToastUtil.showShort(registerActivity, "请输入邮箱地址");
                    return;
                }

                if (!spassword.equals(sagainpassword)) {
                    ToastUtil.showShort(registerActivity, "密码不一样");
                    return;
                }

                if (!sagainpassword.equals(spassword)) {
                    ToastUtil.showShort(registerActivity, "密码不一样");
                    return;
                }

                if (!CheckTextUtil.isEmail(semail)) {
                    ToastUtil.showShort(registerActivity, "邮箱格式不正确");
                    return;
                }

                register();
            }
        });

    }


    private void register() {
        RequestBody requestBody = new FormBody.Builder()
                .add("act", "login")
                .add("op", "register")
                .add("username", susername)
                .add("password", spassword)
                .add("password_confirm", sagainpassword)
                .add("email", semail)
                .add("client", "android")
                .build();
        mainPresenter.loadDataFromPostServer(Urls.LINK_MOBILE_REG, RegisterandLoginBean.class, 0, requestBody);
    }

    @Override
    int setMyContentView() {
        return R.layout.activity_user_register;
    }

    /**
     * 从服务器获取数据
     */
    @Override
    void initDataFromServer() {

    }

    /**
     * 初始化数据
     */
    @Override
    void initData() {
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
    }

    @Override
    public void success(final RegisterandLoginBean bean, int code) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int beanCode = bean.getCode();
                //判断返回的code值是不是等于200  如果等于200就带着用户名跳转到登录页面
                if (beanCode == 200) {
                    ToastUtil.showShort(registerActivity,"注册成功！");
                    Intent intent = new Intent(UserRegisterActivity.this, UserLoginActivity.class);
                    intent.putExtra(USERNAME,susername);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void error(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.dettachView();
    }
}
