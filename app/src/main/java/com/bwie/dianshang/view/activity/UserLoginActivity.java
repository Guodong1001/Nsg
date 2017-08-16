package com.bwie.dianshang.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwie.dianshang.R;
import com.bwie.dianshang.model.bean.RegisterandLoginBean;
import com.bwie.dianshang.presenter.MainPresenter;
import com.bwie.dianshang.utils.ConstantsUtil;
import com.bwie.dianshang.utils.ToastUtil;
import com.bwie.dianshang.utils.Urls;
import com.bwie.dianshang.view.IView.IMainView;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class UserLoginActivity extends BaseActivity implements IMainView<RegisterandLoginBean> {
    private ImageView imageBack;
    private EditText mEditUsername;
    private EditText mEditPassword;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private Button mBtnFindPassword;
    private String mUsername;
    private MainPresenter mainPresenter;
    public static boolean LOGIN_CODE;


    /**
     * 初始化控件
     */
    @Override
    void initView() {
        imageBack = (ImageView) findViewById(R.id.img_back);
        mEditUsername = (EditText) findViewById(R.id.login_edit_username);
        mEditPassword = (EditText) findViewById(R.id.login_edit_password);
        mBtnLogin = (Button) findViewById(R.id.login_btn_login);
        mBtnRegister = (Button) findViewById(R.id.login_btn_register);
        mBtnFindPassword = (Button) findViewById(R.id.login_btn_find_pwd);
    }

    @Override
    void createEvent() {
        //左上角返回按钮的点击事件
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //立即注册按钮的点击事件
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到用户注册页面
                Intent intent = new Intent(UserLoginActivity.this, UserRegisterActivity.class);
                startActivity(intent);
            }
        });
        //登录按钮的点击事件
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnLogin.setText("登录中...");
                login();
            }
        });

    }

    /**
     * 登录按钮的点击事件
     * 请求服务器
     */
    private void login() {
        //获取输入框中的数据
        String username = mEditUsername.getText().toString();
        String password = mEditPassword.getText().toString();
        //对获取到的值进行判断
        if(TextUtils.isEmpty(username)){
            ToastUtil.showShort(UserLoginActivity.this,"用户名不能为空！");
            mBtnLogin.setText("登录");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort(UserLoginActivity.this, "密码不能为空!");
            mBtnLogin.setText("登录");
            return;
        }
        //因为post请求需要一个RequestBody来向服务器发送表单
        RequestBody requestBody = new FormBody.Builder()
                .add("act", "login")
                .add("username", username)
                .add("password", password)
                .add("client", "android")
                .build();
        //通过P层调用网络请求
        mainPresenter.loadDataFromPostServer(Urls.LINK_MOBILE_LOGIN, RegisterandLoginBean.class, 1, requestBody);

    }

    @Override
    int setMyContentView() {
        return R.layout.activity_user_login;
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
        Intent intent = getIntent();
        if (intent != null) {
            mUsername = intent.getStringExtra(UserRegisterActivity.USERNAME);
            mEditUsername.setText(mUsername);
        }
    }

    @Override
    public void success(final RegisterandLoginBean registerandLoginBean, int code) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int beanCode = registerandLoginBean.getCode();
                //判断状态码是否为200
                if (beanCode == 200) {
                    LOGIN_CODE = true;
                    ToastUtil.showShort(UserLoginActivity.this, "登录成功！");
                    Intent intent = new Intent(UserLoginActivity.this,MainActivity.class);
                    intent.putExtra(ConstantsUtil.FLAG,"userlogin");
                    intent.putExtra(ConstantsUtil.USERNAME,registerandLoginBean.getDatas().getUsername());
                    intent.putExtra(ConstantsUtil.GOODS,"商品：0");
                    intent.putExtra(ConstantsUtil.STORE,"店铺：0");
                    startActivity(intent);
                }else{
                    ToastUtil.showShort(UserLoginActivity.this, "登录失败！");
                    mBtnLogin.setText("登录");
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
