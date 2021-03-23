package com.example.map.mylocation.ui;

import android.content.Intent;

import com.example.map.mylocation.LoginUserManager;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Persion;

import cn.bmob.v3.BmobUser;


public class WelcomeActivity extends BaseSimpleActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_weclome;
    }

    @Override
    public void initView() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {

                    Thread.sleep(3000);
                    if (LoginUserManager.isLogin()) {
                        if (BmobUser.getCurrentUser(Persion.class).getFlag() == 0) {
                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        } else {
                            startActivity(new Intent(WelcomeActivity.this, ManagerMainActivity.class));
                        }

                    } else {
                        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));

                    }
                    finish();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
