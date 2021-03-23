package com.example.map.mylocation.fragments;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;

import com.example.map.mylocation.LoginUserManager;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Persion;
import com.example.map.mylocation.dialog.InputNumberDiaolg;
import com.example.map.mylocation.ui.LoginActivity;
import com.example.map.mylocation.utils.Event;
import com.example.map.mylocation.view.NormalToolbar;

import cn.bmob.v3.BmobUser;


public class MineFragment extends BaseFragment implements View.OnClickListener {
    Button login_out;
    NormalToolbar normal_toolbar_reg;


    LinearLayout nicheng_layout, qianming_layout, place_layout;


    RadioButton man, woman;


    TextView text_place, nick_name, sign_name;

    @Override
    public int getLayoutResId() {
        return R.layout.mine_layout;
    }

    @Override
    public void initView() {
        login_out = getRootview().findViewById(R.id.login_out);
        login_out.setOnClickListener(this);
        normal_toolbar_reg = getRootview().findViewById(R.id.normal_toolbar_reg);
        normal_toolbar_reg.setMainTitle("我的");
        text_place = getRootview().findViewById(R.id.text_place);
        nicheng_layout = getRootview().findViewById(R.id.nicheng_layout);
        nicheng_layout.setOnClickListener(this);

        qianming_layout = getRootview().findViewById(R.id.qianming_layout);
        qianming_layout.setOnClickListener(this);
        place_layout = getRootview().findViewById(R.id.place_layout);
        place_layout.setOnClickListener(this);

        man = getRootview().findViewById(R.id.man);
        woman = getRootview().findViewById(R.id.woman);
        man.setOnClickListener(this);
        woman.setOnClickListener(this);

        nick_name = getRootview().findViewById(R.id.nick_name);

        sign_name = getRootview().findViewById(R.id.sign_name);

        text_place = getRootview().findViewById(R.id.text_place);

        /**
         * 男人
         */
        if (LoginUserManager.getUserSex().equals("男")) {
            man.setChecked(true);
            woman.setChecked(false);
        } else {
            man.setChecked(false);
            woman.setChecked(true);
        }

        if (!StringUtils.isEmpty(LoginUserManager.getNickName())) {
            nick_name.setText(LoginUserManager.getNickName());
        } else {

        }

        if (!StringUtils.isEmpty(LoginUserManager.getSign())) {
            sign_name.setText(LoginUserManager.getSign());
        } else {

        }

        if (!StringUtils.isEmpty(LoginUserManager.getWhere())) {
            text_place.setText(LoginUserManager.getWhere());
        } else {

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_out:
                LoginUserManager.loginOut();
                BmobUser.getCurrentUser(Persion.class).logOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            /**
             * 修改昵称
             */
            case R.id.nicheng_layout:
                showDailog(0);
                break;
            /**
             * 签名
             */
            case R.id.qianming_layout:
                showDailog(1);
                break;
            /**
             * 所在地
             */
            case R.id.place_layout:
                showDailog(2);
                break;
            case R.id.man:
                man.setChecked(true);
                woman.setChecked(false);
                LoginUserManager.setSex("男");
                break;
            case R.id.woman:
                man.setChecked(false);
                woman.setChecked(true);
                LoginUserManager.setSex("女");
                break;
            default:
                break;
        }
    }

    private void showDailog(int type) {
        InputNumberDiaolg inputNumberDiaolg = new InputNumberDiaolg(getActivity(), R.style.dialog_vip, type);
        inputNumberDiaolg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        inputNumberDiaolg.show();

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(Event event) {
        super.receiveEvent(event);
        switch (event.getCode()) {
            case 0:
                LoginUserManager.setNickName((String) event.getData());
                nick_name.setText((String) event.getData());
                break;
            case 1:
                LoginUserManager.setSign((String) event.getData());
                sign_name.setText((String) event.getData());
                break;
            case 2:
                LoginUserManager.setWhere((String) event.getData());
                text_place.setText((String) event.getData());
                break;
            default:
                break;
        }
    }
}
