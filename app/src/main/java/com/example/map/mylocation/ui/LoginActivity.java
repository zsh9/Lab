package com.example.map.mylocation.ui;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.LoginUserManager;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Persion;
import com.example.map.mylocation.utils.ToastUtil;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends BaseSimpleActivity implements View.OnClickListener {

    /**
     * 注册
     */
    TextView regiset_text;

    /**
     * 账号和密码
     */
    EditText user_phone, password_text;
    /**
     * 登陆
     */
    Button button_login;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        /**
         * 绑定控件设置点击事件
         */
        regiset_text = findViewById(R.id.login_forgetpass);
        regiset_text.setOnClickListener(this);
        user_phone = findViewById(R.id.login_tel);
        password_text = findViewById(R.id.login_password);
        button_login = findViewById(R.id.login_sure);
        button_login.setOnClickListener(this);
        /**
         * 首先去申请定位权限  负责无法获得定位位置
         */
        PermissionUtils.permission(PermissionConstants.LOCATION)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {

                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {

                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {

                    }
                })
                .theme(new PermissionUtils.ThemeCallback() {
                    @Override
                    public void onActivityCreate(Activity activity) {
                        ScreenUtils.setFullScreen(activity);
                    }
                })
                .request();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 注册
             */
            case R.id.login_forgetpass:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            /**
             * 登陆
             */
            case R.id.login_sure:
                if (StringUtils.isEmpty(user_phone.getText().toString().trim())) {
                    ToastUtils.showShort("账号错误");
                    return;
                }
                if (StringUtils.isEmpty(password_text.getText().toString().trim())) {
                    ToastUtils.showShort("密码错误");
                    return;
                }
                setButton_login(user_phone.getText().toString().trim(), password_text.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    /**
     * 去请求数据 验证是否可以登陆
     *
     * @param name
     * @param pwd
     */
    private void setButton_login(String name, String pwd) {
        Persion user = new Persion();
        user.setUsername(name);
        user.setPassword(pwd);
        user.login(new SaveListener<Persion>() {

            @Override
            public void done(Persion s, BmobException e) {
                if (e == null) {
                    LoginUserManager.login();
                    LoginUserManager.setUserIsTeOrStu(s.getFlag());
                    LoginUserManager.setuserId(s.getUsername());
                    if (s.getFlag() == 0) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(LoginActivity.this, ManagerMainActivity.class));
                    }

                    finish();
                } else {
                    ToastUtil.showTextToast("登录失败：" + e.getMessage());
                    Log.e("fail", e.getMessage());
                }
            }
        });


//        RxHttp.postForm(Commons.Baseurl + Commons.login)
//                .add("userName", name)
//                .add("userPassword", pwd)
//                .asString()
//                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
//                //成功回调
//                .subscribe(s -> {
//                    Log.e("login", s);
//                    /**
//                     * 解析返回数据 使用gson的封装类
//                     */
//                    LoginResponse regResponse = (LoginResponse) JsonUtils.fromJson(s, LoginResponse.class);
//                    /**
//                     * 结果是0 则表示登陆成功
//                     */
//                    if (regResponse.getResultcode() == 0) {
//                        LoginUserManager.login();
//                        startActivity(new Intent(this, MainActivity.class));
//                        finish();
//                    } else {
//                        ToastUtils.showShort(regResponse.getResultmsg());
//                    }
//
//
//                }, (OnError) -> {
//                    ToastUtils.showShort("接口异常");
//                });

    }
}
