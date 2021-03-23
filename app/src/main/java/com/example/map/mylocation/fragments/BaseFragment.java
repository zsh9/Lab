package com.example.map.mylocation.fragments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.BarUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.utils.Event;
import com.example.map.mylocation.utils.EventBusUtil;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by Android开发 on 2018/8/27.
 */

public abstract class BaseFragment extends Fragment implements View.OnTouchListener {
    public static final String TAG = BaseFragment.class.getSimpleName();
    private View mRootView;

//    /**
//     * 是否对用户可见
//     */
//    protected boolean mIsVisible;
//    /**
//     * 是否加载完成
//     * 当执行完oncreatview,View的初始化方法后方法后即为true
//     */
//    protected boolean mIsPrepare;
    /**
     * 贴附的activity
     */
    protected FragmentActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = getActivity();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;//消费掉点击事件,防止跑到下一层去
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutResId(), container, false);

        initView();

        if (isShowWhite()) {
            BarUtils.setStatusBarAlpha(getActivity(), 0);
            BarUtils.setStatusBarLightMode(getActivity(), false);
        } else {
            BarUtils.setStatusBarAlpha(getActivity(), 0);
            BarUtils.setStatusBarLightMode(getActivity(), true);
        }

        //设置状态栏
        BarUtils.setStatusBarAlpha(getActivity(), 0);
        BarUtils.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.white), 0);
        BarUtils.setStatusBarLightMode(getActivity(), true);
        return mRootView;
    }


    public View getRootview() {
        return mRootView;
    }

    /**
     * 返回布局
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 初始化
     *
     * @return
     */
    public abstract void initView();


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//
//        this.mIsVisible = isVisibleToUser;
//
//        if (isVisibleToUser) {
//            onVisibleToUser();
//        }
//    }

//    /**
//     * 用户可见时执行的操作
//     *
//     * @author 漆可
//     * @date 2016-5-26 下午4:09:39
//     */
//    protected void onVisibleToUser() {
//        if (mIsPrepare && mIsVisible) {
//            onLazyLoad();
//        }
//    }
//    /**
//     * 懒加载，仅当用户可见切view初始化结束后才会执行
//     */
//    protected void onLazyLoad()
//    {
//
//    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    /**
     * 是否显示高亮模式
     */
    protected boolean isShowWhite() {
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
