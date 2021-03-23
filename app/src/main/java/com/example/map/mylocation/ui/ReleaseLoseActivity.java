package com.example.map.mylocation.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Apply;
import com.example.map.mylocation.bean.Report;
import com.example.map.mylocation.utils.Completion;
import com.example.map.mylocation.utils.PictureSelectorUtils;
import com.example.map.mylocation.utils.QiniuUpload;
import com.example.map.mylocation.utils.ToastUtil;
import com.example.map.mylocation.view.NormalToolbar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ReleaseLoseActivity extends BaseSimpleActivity implements View.OnClickListener {


    Button choose_pic;

    EditText editText, des_goods, shiyanjieguo;
    ImageView pic;

    Button choose;


    NormalToolbar top_bar;
    Apply apply;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_release_lose;
    }

    public void initView() {
        apply = (Apply) getIntent().getSerializableExtra("bean");
        top_bar = findViewById(R.id.top_bar);

        top_bar.setMainTitle("提交报告");
        top_bar.setLeftTitleText("返回");
        top_bar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        top_bar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 0) {
                    putToy(path);
                } else if (type == 1) {
                    update(path);
                }

            }
        });
        top_bar.setRightTitleText("提交");
        pic = findViewById(R.id.pic);
        choose = findViewById(R.id.choose);
        choose.setOnClickListener(this);

        editText = findViewById(R.id.title);
        des_goods = findViewById(R.id.des_goods);
//        goods_location = findViewById(R.id.goods_location);
        shiyanjieguo = findViewById(R.id.shiyanjieguo);

        editText.setText(apply.getTitle());


        choose_pic = findViewById(R.id.choose_pic);
        choose_pic.setOnClickListener(this);

        find();
    }

    int type = 0;
    Report report;

    /**
     * 查找
     */
    private void find() {
        BmobQuery<Report> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("apply", apply);
        bmobQuery.findObjects(new FindListener<Report>() {
            @Override
            public void done(List<Report> list, BmobException e) {
                if (e == null) {
                    /**
                     * 表示已经提交过了
                     */
                    if (list != null && list.size() > 0) {
                        report = list.get(0);
                        editText.setText(list.get(0).getTitle());
                        des_goods.setText(list.get(0).getDes());
                        shiyanjieguo.setText(list.get(0).getFinallys());
                        type = 1;
                        top_bar.setRightTitleText("修改");
                        path = list.get(0).getPicurl();
                        Glide.with(ReleaseLoseActivity.this).load(path).into(pic);
                    } else {
                        type = 0;
                    }
                } else {

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            /**
             * shoucang
             */
            case R.id.choose_pic:

                break;

            /**
             *
             */
            case R.id.choose:
                PictureSelectorUtils.selectImg(PictureSelector.create(this), 1);

                break;
            default:
                break;
        }
    }

    String path = "";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() > 0) {
                    path = selectList.get(0).getCompressPath();
                    Glide.with(this).load(path).into(pic);
                }
            }
        }
    }

    /**
     * des_goods.getText().toString().trim(), where, price, send, phone
     */
    private void update(String imagePath) {

        if (StringUtils.isEmpty(editText.getText().toString().trim())) {
            ToastUtil.showTextToast("实验标题不能为空");
            return;
        }
        if (StringUtils.isEmpty(des_goods.getText().toString().trim())) {
            ToastUtil.showTextToast("实验过程描述不能为空");
            return;
        }
        if (StringUtils.isEmpty(shiyanjieguo.getText().toString().trim())) {
            ToastUtil.showTextToast("实验过程描述不能为空");
            return;
        }
        if (StringUtils.isEmpty(path)) {
            ToastUtil.showTextToast("图片不能为空");
            return;
        }
        if (!StringUtils.isEmpty(imagePath) && !imagePath.contains("http")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    QiniuUpload.UploadPic(imagePath, FileUtils.getFileName(imagePath), new Completion() {
                        @Override
                        public void complete(String imageurl) {
                            if (!StringUtils.isEmpty(imageurl)) {
                                updateurl(imageurl);
                            } else {

                            }
                        }
                    });
                }
            }).start();
        } else {
            updateurl(imagePath);
        }


    }


    private void updateurl(String picurl) {
        /**
         * 保存数据到报告中
         */
        report.setApply(apply);
        report.setTitle(editText.getText().toString().trim());
        report.setDes(des_goods.getText().toString().trim());
        report.setFinallys(shiyanjieguo.getText().toString().trim());
        report.setPicurl(picurl);
        report.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastUtil.showTextToast("修改成功");
                    finish();
                } else {
                    ToastUtil.showTextToast(e.getMessage());
                }
            }
        });

    }

    /**
     * des_goods.getText().toString().trim(), where, price, send, phone
     */
    private void putToy(String imagePath) {

        if (StringUtils.isEmpty(editText.getText().toString().trim())) {
            ToastUtil.showTextToast("实验标题不能为空");
            return;
        }
        if (StringUtils.isEmpty(des_goods.getText().toString().trim())) {
            ToastUtil.showTextToast("实验过程描述不能为空");
            return;
        }
        if (StringUtils.isEmpty(shiyanjieguo.getText().toString().trim())) {
            ToastUtil.showTextToast("实验过程描述不能为空");
            return;
        }
        if (StringUtils.isEmpty(path)) {
            ToastUtil.showTextToast("图片不能为空");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                QiniuUpload.UploadPic(imagePath, FileUtils.getFileName(imagePath), new Completion() {
                    @Override
                    public void complete(String imageurl) {
                        if (!StringUtils.isEmpty(imageurl)) {
                            send(imageurl);
                        } else {

                        }
                    }
                });
            }
        }).start();

    }

    private void send(String picurl) {

        /**
         * 保存数据到报告中
         */
        Report postTie = new Report();
        postTie.setApply(apply);
        postTie.setTitle(editText.getText().toString().trim());
        postTie.setDes(des_goods.getText().toString().trim());
        postTie.setFinallys(shiyanjieguo.getText().toString().trim());
        postTie.setPicurl(picurl);
        postTie.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    ToastUtil.showTextToast("提交成功");
                    finish();
                } else {
                    ToastUtil.showTextToast(e.getMessage());
                }
            }
        });
    }
}