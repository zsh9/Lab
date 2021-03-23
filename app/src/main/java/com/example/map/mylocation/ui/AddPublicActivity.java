package com.example.map.mylocation.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Notice;
import com.example.map.mylocation.bean.Public;
import com.example.map.mylocation.view.NormalToolbar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddPublicActivity extends BaseSimpleActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_add_public;
    }


    private NormalToolbar normalToolbarReg;
    private EditText beizhu;
    private EditText location;
    private EditText name;


    @Override
    public void initView() {
        normalToolbarReg = findViewById(R.id.normal_toolbar_reg);
        normalToolbarReg.setMainTitle("添加公告");
        normalToolbarReg.setLeftTitleText("返回");
        normalToolbarReg.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        normalToolbarReg.setRightTitleText("添加");
        normalToolbarReg.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
//        beizhu = findViewById(R.id.beizhu);
        location = findViewById(R.id.loaction);
        name = findViewById(R.id.name);


    }

    private void add() {
//        if (StringUtils.isEmpty(beizhu.getText().toString().trim())) {
//            ToastUtils.showShort("所有字段不可为空");
//            return;
//        }
        if (StringUtils.isEmpty(location.getText().toString().trim())) {
            ToastUtils.showShort("所有字段不可为空");
            return;
        }
        if (StringUtils.isEmpty(name.getText().toString().trim())) {
            ToastUtils.showShort("所有字段不可为空");
            return;
        }


        Public roomBean = new Public();
        roomBean.setName(name.getText().toString().trim());
        roomBean.setContent(location.getText().toString().trim());

        roomBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    ToastUtils.showShort("发布成功");
                    finish();
                } else {

                }
            }
        });

    }
}