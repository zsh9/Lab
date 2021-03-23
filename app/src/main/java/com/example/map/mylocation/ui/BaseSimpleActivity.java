package com.example.map.mylocation.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


/**
 * Created by Android开发 on 2019/4/2.
 */

public abstract class BaseSimpleActivity extends AppCompatActivity {

    Bundle mbundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        mbundle = savedInstanceState;
        Log.e("BaseActivity", getClass().getSimpleName());
        initView();
    }


    public Bundle getSaveInstance() {
        return mbundle;
    }

    /**
     * 返回布局
     *
     * @return
     */
    public abstract int getContentViewResId();

    /**
     * 初始化操作
     *
     * @return
     */
    public abstract void initView();


}
