package com.example.map.mylocation.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.RoomBean;
import com.example.map.mylocation.view.NormalToolbar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RoomDetailActivity extends BaseSimpleActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_room_detail;
    }


    private NormalToolbar normalToolbarReg;
    private TextView beizhu;
    private TextView location;
    private TextView name;

    RoomBean roomBean;

    @Override
    public void initView() {
        roomBean = (RoomBean) getIntent().getSerializableExtra("bean");
        normalToolbarReg = findViewById(R.id.normal_toolbar_reg);
        normalToolbarReg.setMainTitle("增加实验室");
        normalToolbarReg.setLeftTitleText("返回");
        normalToolbarReg.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        normalToolbarReg.setRightTitleText("添加");
//        normalToolbarReg.setRightTitleClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                add();
//            }
//        });
        beizhu = findViewById(R.id.beizhu);
        location = findViewById(R.id.loaction);
        name = findViewById(R.id.name);
        name.setText(roomBean.getRoomname());
        location.setText(roomBean.getLoaction());
        beizhu.setText(roomBean.getBeizhu());

    }


}