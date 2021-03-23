package com.example.map.mylocation.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Persion;
import com.example.map.mylocation.bean.Report;
import com.example.map.mylocation.bean.TeacherSay;
import com.example.map.mylocation.utils.ToastUtil;
import com.example.map.mylocation.view.NormalToolbar;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 查看报告 并经性评价
 */
public class FindReportDetailActivity extends BaseSimpleActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_find_report_detail;
    }

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

    Report apply;
    private EditText pingjianeirong;
    LinearLayout bottom;
    TextView editText, des_goods, shiyanjieguo;
    ImageView pic;

    @Override
    public void initView() {
        apply = (Report) getIntent().getSerializableExtra("bean");
        pingjianeirong = findViewById(R.id.pingjianeirong);
        editText = findViewById(R.id.title);
        des_goods = findViewById(R.id.des_goods);
        shiyanjieguo = findViewById(R.id.shiyanjieguo);
        pic = findViewById(R.id.pic);
        bottom = findViewById(R.id.bottom);
        bottom.setVisibility(View.VISIBLE);

        normalToolbarReg = findViewById(R.id.topbar);
        normalToolbarReg.setMainTitle("报告详情单子");
        normalToolbarReg.setLeftTitleText("返回");
        normalToolbarReg.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        normalToolbarReg.setRightTitleText("评价");
        if (BmobUser.getCurrentUser(Persion.class).getFlag() == 0) {
            normalToolbarReg.setRightTitleText("");
        }
        normalToolbarReg.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pingjia();
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
        find();
    }

    /**
     * 评价
     */
    private void pingjia() {

        if (StringUtils.isEmpty(pingjianeirong.getText().toString().trim())) {
            ToastUtil.showTextToast("输入评价内容");
            return;
        }
        if (type == 0) {
            TeacherSay teacherSay = new TeacherSay();
            teacherSay.setMessage(pingjianeirong.getText().toString().trim());
            teacherSay.setReport(apply);
            teacherSay.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        ToastUtil.showTextToast("评价成功");
                    } else {
                        ToastUtil.showTextToast(e.getMessage());
                    }
                }
            });

        } else {
            teacherSay.setMessage(pingjianeirong.getText().toString().trim());
            teacherSay.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        ToastUtil.showTextToast("评价成功");

                    } else {
                        ToastUtil.showTextToast(e.getMessage());
                    }
                }
            });
        }
        apply.setStatus(1);
        apply.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    finish();
                } else {
                    ToastUtil.showTextToast(e.getMessage());
                }
            }
        });
    }

    /**
     * 0 标识 空白 回复过
     */
    int type = 0;
    TeacherSay teacherSay;

    private void find() {
        BmobQuery<TeacherSay> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("report", apply);
        bmobQuery.findObjects(new FindListener<TeacherSay>() {
            @Override
            public void done(List<TeacherSay> list, BmobException e) {
                if (e == null) {
                    /**
                     * 表示已经提交过了
                     */
                    if (list != null && list.size() > 0) {
                        type = 1;
                        teacherSay = list.get(0);
                        pingjianeirong.setText(list.get(0).getMessage());
                    } else {
                        type = 0;
                    }
                } else {

                }
            }
        });

    }


    private void setdata() {
        kecheng.setText(apply.getApply().getKecheng());
        name.setText(apply.getApply().getTitle());
        teacher.setText(apply.getApply().getTeacher());
        mubiao.setText(apply.getApply().getMubiao());
        qicai.setText(apply.getApply().getQicai());
        time.setText(apply.getApply().getTime());
        username.setText(apply.getApply().getUsername());

        if (StringUtils.isEmpty(apply.getApply().getRoomname())) {
            room.setText("暂无");
        } else {
            room.setText(apply.getApply().getRoomname());
        }

        editText.setText(apply.getApply().getTitle());
        des_goods.setText(apply.getDes());
        shiyanjieguo.setText(apply.getFinallys());
        Glide.with(FindReportDetailActivity.this).load(apply.getPicurl()).into(pic);
        if (type == 1) {
            pingjianeirong.setText(teacherSay.getMessage());
        } else {
            pingjianeirong.setHint("尚未评价");
        }


    }
}