package com.example.map.mylocation.ui;

import android.view.View;
import android.widget.TextView;

import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.QiCai;
import com.example.map.mylocation.view.NormalToolbar;

public class QiCaiDetailActivity extends BaseSimpleActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_qi_cai_detail;
    }

    private NormalToolbar normalToolbarReg;
    private TextView beizhu;
    private TextView location;
    private TextView name, number;

    QiCai roomBean;

    @Override
    public void initView() {
        roomBean = (QiCai) getIntent().getSerializableExtra("bean");
        normalToolbarReg = findViewById(R.id.normal_toolbar_reg);
        normalToolbarReg.setMainTitle("器材详情");
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

        number = findViewById(R.id.number);


        name.setText(roomBean.getName());
        location.setText(roomBean.getSize());
        beizhu.setText(roomBean.getBeizhu());
        number.setText(roomBean.getNumber() + "台");
    }

}