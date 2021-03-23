package com.example.map.mylocation.ui;

import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Apply;
import com.example.map.mylocation.bean.Persion;
import com.example.map.mylocation.utils.ToastUtil;
import com.example.map.mylocation.view.NormalToolbar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 申请实验室
 */
public class ApplyRoomActivity extends BaseSimpleActivity {

    private NormalToolbar normalToolbarReg;
    private EditText kecheng;
    private EditText teacher;
    private EditText name;
    private EditText mubiao;
    private EditText qicai;
    private EditText time;
    private EditText username;


    @Override
    public int getContentViewResId() {
        return R.layout.activity_apply_room;
    }

    @Override
    public void initView() {
        normalToolbarReg = findViewById(R.id.normal_toolbar_reg);
        normalToolbarReg.setMainTitle("申请实验室");
        normalToolbarReg.setLeftTitleText("返回");
        normalToolbarReg.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        normalToolbarReg.setRightTitleText("申请");
        normalToolbarReg.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        kecheng = findViewById(R.id.kecheng);
        teacher = findViewById(R.id.teacher);
        name = findViewById(R.id.name);
        mubiao = findViewById(R.id.mubiao);
        qicai = findViewById(R.id.qicai);
        time = findViewById(R.id.time);
        username = findViewById(R.id.username);


    }

    private void add() {
        if (StringUtils.isEmpty(kecheng.getText().toString().trim())) {
            ToastUtils.showShort("所有字段不可为空");
            return;
        }
        if (StringUtils.isEmpty(teacher.getText().toString().trim())) {
            ToastUtils.showShort("所有字段不可为空");
            return;
        }
        if (StringUtils.isEmpty(name.getText().toString().trim())) {
            ToastUtils.showShort("所有字段不可为空");
            return;
        }
        if (StringUtils.isEmpty(mubiao.getText().toString().trim())) {
            ToastUtils.showShort("所有字段不可为空");
            return;
        }
        if (StringUtils.isEmpty(qicai.getText().toString().trim())) {
            ToastUtils.showShort("所有字段不可为空");
            return;
        }
        if (StringUtils.isEmpty(time.getText().toString().trim())) {
            ToastUtils.showShort("所有字段不可为空");
            return;
        }
        if (StringUtils.isEmpty(username.getText().toString().trim())) {
            ToastUtils.showShort("所有字段不可为空");
            return;
        }


        Apply apply = new Apply();
        apply.setKecheng(kecheng.getText().toString());
        apply.setTeacher(teacher.getText().toString());

        apply.setMubiao(mubiao.getText().toString());
        apply.setQicai(qicai.getText().toString());
        apply.setTime(time.getText().toString());
        apply.setUsername(username.getText().toString());
        apply.setTitle(name.getText().toString());
        apply.setStatus(0);
        apply.setUser(BmobUser.getCurrentUser(Persion.class));
        apply.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    ToastUtils.showShort("申请成功");
                    finish();
                } else {

                }
            }
        });

    }
}