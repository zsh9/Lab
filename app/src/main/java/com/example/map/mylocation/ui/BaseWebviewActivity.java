package com.example.map.mylocation.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.BarUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.utils.Event;
import com.example.map.mylocation.utils.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by Android开发 on 2019/4/2.
 */

public abstract class BaseWebviewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());

        initView();
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        Log.d("BaseActivity", getClass().getSimpleName());

        //设置状态栏
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.white),0);
        BarUtils.setStatusBarLightMode(this, true);
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

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }

    }

}
