package com.example.map.mylocation.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Persion;
import com.example.map.mylocation.utils.ToastUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class RegisterActivity extends BaseSimpleActivity implements View.OnClickListener {


    Button register_btn;

    EditText phone_num, password;

    RadioButton yezhu, wuye;
    /**
     * 默认学生注册
     */
    int type = 0;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_reg;
    }

    @Override
    public void initView() {

        register_btn = findViewById(R.id.register_sure);
        register_btn.setOnClickListener(this);
        phone_num = findViewById(R.id.register_tel);
        password = findViewById(R.id.register_password);

        yezhu = findViewById(R.id.man);
        wuye = findViewById(R.id.woman);
        yezhu.setOnClickListener(this);
        wuye.setOnClickListener(this);

        yezhu.setChecked(true);
        wuye.setChecked(false);
        type = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 注册
             */
            case R.id.register_sure:
                /**
                 * 判断账号和密码是否为空
                 */
                if (StringUtils.isEmpty(phone_num.getText().toString())) {
                    ToastUtils.showShort("请输入账号");
                    return;
                }
                if (StringUtils.isEmpty(password.getText().toString())) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                if (!StringUtils.isEmpty(phone_num.getText().toString()) && !StringUtils.isEmpty(password.getText().toString())) {

                    regsiter(phone_num.getText().toString().trim(), password.getText().toString().trim());
                }


                break;
            /**
             * 学生
             */
            case R.id.man:
                yezhu.setChecked(true);
                wuye.setChecked(false);
                type = 0;
                break;
            /**
             * 老师
             */
            case R.id.woman:
                yezhu.setChecked(false);
                wuye.setChecked(true);
                type = 1;
                break;
            default:
                break;
        }
    }

    /**
     * 去登陆请求
     *
     * @param name
     * @param pwd  这个是第三方的http请求框架
     */

    private void regsiter(String name, String pwd) {
        Persion user = new Persion();
        user.setUsername(name);
        user.setPassword(pwd);
        user.setFlag(type);
        //注意：不能用save方法进行注册

        user.signUp(new SaveListener<Persion>() {
            @Override
            public void done(Persion s, BmobException e) {
                if (e == null) {
                    ToastUtils.showShort("注册成功请登录");
                    finish();

                } else {
                    ToastUtil.showTextToast(e.getMessage());
                }
            }


        });
//        RxHttp.postForm(Commons.Baseurl + Commons.reg)
//                .add("userName", name)
//                .add("userPassword", pwd)
//                .asString()
//                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
//                //成功回调
//                .subscribe(s -> {
//                    /**
//                     * 解析返回数据 使用gson的封装类
//                     */
//                    RegResponse regResponse = (RegResponse) JsonUtils.fromJson(s, RegResponse.class);
//                    if (regResponse.getResultcode() == 0) {
//                        ToastUtils.showShort("注册成功请登录");
//                        finish();
//                    } else {
//                        ToastUtils.showShort(regResponse.getResultmsg());
//                    }
//
//
//                }, (OnError) -> {
//                    ToastUtils.showShort("接口错误");
//                });

    }
}
