package com.example.map.mylocation.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Apply;
import com.example.map.mylocation.bean.Persion;
import com.example.map.mylocation.bean.RoomBean;
import com.example.map.mylocation.utils.ToastUtil;
import com.example.map.mylocation.view.NormalToolbar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ApplyRoomDetailActivity2 extends BaseSimpleActivity {

    private NormalToolbar normalToolbarReg;

    private TextView kecheng;
    private TextView name;
    private TextView teacher;
    private TextView mubiao;
    private TextView qicai;
    private TextView time;
    private TextView username;
    private TextView status;
    private TextView room;

    Apply apply;


    private LinearLayout bottom, choosejiaoshi;
    private Button aggree;
    private Button disaggerr;


    @Override
    public int getContentViewResId() {
        return R.layout.activity_apply_room_detail2;
    }

    @Override
    public void initView() {
        apply = (Apply) getIntent().getSerializableExtra("bean");
        choosejiaoshi = findViewById(R.id.choosejiaoshi);
        bottom = findViewById(R.id.bottom);
        aggree = findViewById(R.id.aggree);
        disaggerr = findViewById(R.id.disaggerr);
        /**
         * 管理员进行展示
         */
        if (BmobUser.getCurrentUser(Persion.class).getFlag() == 0) {
            bottom.setVisibility(View.INVISIBLE);
        } else {
            bottom.setVisibility(View.VISIBLE);
        }
        aggree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aggree(1);
            }
        });
        disaggerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updata(2);
            }
        });
        normalToolbarReg = findViewById(R.id.topbar);
        normalToolbarReg.setMainTitle("申请实验室详情");
        normalToolbarReg.setLeftTitleText("返回");
        normalToolbarReg.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        kecheng = findViewById(R.id.kecheng);
        name = findViewById(R.id.name);
        teacher = findViewById(R.id.teacher);
        mubiao = findViewById(R.id.mubiao);
        qicai = findViewById(R.id.qicai);
        time = findViewById(R.id.time);
        username = findViewById(R.id.username);
        status = findViewById(R.id.status);
        room = findViewById(R.id.room);
        setdata();

        choosejiaoshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //第二个参数为请求码，可以根据业务需求自己编号
                startActivityForResult(new Intent(ApplyRoomDetailActivity2.this, ChooseRoomActivity.class), 1);

            }
        });
    }

    /**
     * 返回来的roobean
     */
    RoomBean roomBeans;

    /**
     * 为了得到传回的数据，必须在前面的Activity中（指MainActivity类）重写onActivityResult方法
     * <p>
     * requestCode 请求码，即调用startActivityForResult()传递过去的值
     * resultCode 结果码，结果码用于标识返回数据来自哪个新Activity
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        String result = data.getExtras().getString("result");//得到新Activity 关闭后返回的数据

        if (data != null) {
            RoomBean roomBean = (RoomBean) data.getSerializableExtra("result");
            roomBeans = roomBean;
            room.setText(roomBean.getRoomname());
        }

    }

    /**
     * 拒绝
     *
     * @param status
     */
    private void updata(int status) {
        apply.setStatus(status);
        apply.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastUtil.showTextToast("修改成功");
                    finish();
                } else {

                }
            }
        });
    }

    /**
     * 同意
     *
     * @param status
     */
    private void aggree(int status) {
        apply.setStatus(status);
        if (status == 1 && roomBeans == null) {
            ToastUtil.showTextToast("请选择教室");
            return;
        }
        apply.setRoomname(roomBeans.getRoomname());
        apply.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    uproomstatus(roomBeans);
                } else {

                }
            }
        });
    }

    private void uproomstatus(RoomBean roomBean) {
        roomBean.setUsed(1);
        roomBean.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastUtil.showTextToast("修改成功");
                    finish();
                } else {

                }
            }
        });
    }

    private void setdata() {
        kecheng.setText(apply.getKecheng());
        name.setText(apply.getTitle());
        teacher.setText(apply.getTeacher());
        mubiao.setText(apply.getMubiao());
        qicai.setText(apply.getQicai());
        time.setText(apply.getTime());
        username.setText(apply.getUsername());
        if (apply.getStatus() == 0) {
            status.setText("申请中");
            status.setTextColor(getResources().getColor(R.color.error_red));
        } else if (apply.getStatus() == 1) {
            status.setText("同意");
            status.setTextColor(getResources().getColor(R.color.colorBlue));
        } else if (apply.getStatus() == 2) {
            status.setText("拒绝");
            status.setTextColor(getResources().getColor(R.color.error_red));
        }
        if (StringUtils.isEmpty(apply.getRoomname())) {
            room.setText("暂无");
        } else {
            room.setText(apply.getRoomname());
        }


    }


}