package com.example.map.mylocation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.fragments.MineFragment;
import com.example.map.mylocation.fragments.NewMsgFragment;
import com.example.map.mylocation.fragments.WayFragment;
import com.example.map.mylocation.fragments.WordListFragment;
import com.example.map.mylocation.utils.DialogHelper;
import com.example.map.mylocation.view.NewNormalView;

import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class ManagerMainActivity extends BaseSimpleActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.activity_manager_main;
    }

    PageNavigationView tab_main_fy;
    NavigationController navigationController;


    public FragmentManager mFragmentManager;
    private FragmentTransaction ft;



    private MineFragment mineFragment;



    private WordListFragment wordListFragment;



    @Override
    public void initView() {
        tab_main_fy = findViewById(R.id.bottom_tab);

        navigationController = tab_main_fy.custom()
                .addItem(newItem(R.drawable.first_un, R.drawable.first_select, "首页"))
//                .addItem(newItem(R.drawable.member_un, R.drawable.member_select_new, "答题测试"))
//                .addItem(newItem(R.drawable.serve_icon_unchoose, R.drawable.serve_ico_choose, "新闻资讯"))
                .addItem(newItem(R.drawable.mine_un, R.drawable.mine_select, "我的"))
                .build();
        mFragmentManager = getSupportFragmentManager();
        showFragment(0);
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                showFragment(index);
            }

            @Override
            public void onRepeat(int index) {

            }
        });
        navigationController.setSelect(0);
        getLocation();
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NewNormalView normalItemView = new NewNormalView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(getResources().getColor(R.color.bottom_item));
        return normalItemView;
    }

    //显示fragment  直加载一次  不重新加载
    public void showFragment(int index) {
        ft = mFragmentManager.beginTransaction();
        // 想要显示一个fragment,先隐藏所有fragment，防止重叠
        hideFragments(ft);
        switch (index) {
            case 0:
//                // 如果fragment1已经存在则将其显示出来
//                if (wayFragment != null)
//                    ft.show(wayFragment);
//                    // 否则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
//                else {
//                    wayFragment = new WayFragment();
//                    ft.add(R.id.new_fragment, wayFragment, "wayFragment");
//                }
                // 如果fragment1已经存在则将其显示出来
                if (wordListFragment != null)
                    ft.show(wordListFragment);
                    // 否则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                else {
                    wordListFragment = new WordListFragment();
                    ft.add(R.id.new_fragment, wordListFragment, "wordListFragment");
                }

                break;
            case 1:
                if (mineFragment != null)
                    ft.show(mineFragment);
                else {
                    mineFragment = new MineFragment();
                    ft.add(R.id.new_fragment, mineFragment, "mineFragment");
                }
                // 如果fragment1已经存在则将其显示出来
//                if (wordListFragment != null)
//                    ft.show(wordListFragment);
//                    // 否则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
//                else {
//                    wordListFragment = new WordListFragment();
//                    ft.add(R.id.new_fragment, wordListFragment, "wordListFragment");
//                }

//                break;
//            case 2:
//                if (newMsgFragment != null)
//                    ft.show(newMsgFragment);
//                else {
//                    newMsgFragment = new NewMsgFragment();
//                    ft.add(R.id.new_fragment, newMsgFragment, "newMsgFragment");
//                }
//                break;
//            case 3:
//                if (mineFragment != null)
//                    ft.show(mineFragment);
//                else {
//                    mineFragment = new MineFragment();
//                    ft.add(R.id.new_fragment, mineFragment, "mineFragment");
//                }
//                break;

            default:
                break;

        }
        ft.commit();
    }

    // 当fragment已被实例化，相当于发生过切换，就隐藏起来
    public void hideFragments(FragmentTransaction ft) {


//        if (wayFragment != null)
//            ft.hide(wayFragment);
        if (mineFragment != null)
            ft.hide(mineFragment);
//        if (newMsgFragment != null)
//            ft.hide(newMsgFragment);
        if (wordListFragment != null)
            ft.hide(wordListFragment);


    }

    private long mExitTime;

    //对返回键进行监听
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {

        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showShort("再按一次退出");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    private void getLocation() {


        /**
         * 首先去申请权限
         */
        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {

                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            /**
                             * 区设置界面
                             */
                            DialogHelper.showOpenAppSettingDialog();
                        }

                    }
                })
                .theme(new PermissionUtils.ThemeCallback() {
                    @Override
                    public void onActivityCreate(Activity activity) {
                        ScreenUtils.setFullScreen(activity);
                    }
                })
                .request();
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }
}