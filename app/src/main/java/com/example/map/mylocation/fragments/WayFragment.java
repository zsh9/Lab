package com.example.map.mylocation.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.map.mylocation.R;
import com.example.map.mylocation.adapter.ImageAdapter;
import com.example.map.mylocation.adapter.RecommmendAdapter;
import com.example.map.mylocation.bean.Recom;
import com.example.map.mylocation.ui.ApplyRoomActivity;
import com.example.map.mylocation.ui.ApplyRoomListActivity;
import com.example.map.mylocation.ui.ManagerQicaiActivity;
import com.example.map.mylocation.ui.ManagerRoomActivity;
import com.example.map.mylocation.ui.NoticeListActivity;
import com.example.map.mylocation.ui.PublicListActivity;
import com.example.map.mylocation.ui.ReportListActivity;
import com.example.map.mylocation.utils.ToastUtil;
import com.example.map.mylocation.view.NormalToolbar;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


public class WayFragment extends BaseFragment implements View.OnClickListener {

    NormalToolbar normal_toolbar_reg;


    private Button finduser;
    private Button findLeave;
    private Button guanligongzi;
    private Button fabugonggao, jiaoshiguanli, qicaiguanli;
    ;


    @Override
    public int getLayoutResId() {
        return R.layout.way_layout;
    }

    @Override
    public void initView() {
        normal_toolbar_reg = getRootview().findViewById(R.id.normal_toolbar_reg);
        normal_toolbar_reg.setMainTitle("学生首页");

        finduser = getRootview().findViewById(R.id.finduser);
        finduser.setOnClickListener(this::onClick);
        findLeave = getRootview().findViewById(R.id.find_leave);
        findLeave.setOnClickListener(this::onClick);
        guanligongzi = getRootview().findViewById(R.id.guanligongzi);
        guanligongzi.setOnClickListener(this::onClick);
        fabugonggao = getRootview().findViewById(R.id.fabugonggao);
        fabugonggao.setOnClickListener(this::onClick);


        jiaoshiguanli = getRootview().findViewById(R.id.jiaoshiguanli);
        jiaoshiguanli.setOnClickListener(this::onClick);
        qicaiguanli = getRootview().findViewById(R.id.qicaiguanli);
        qicaiguanli.setOnClickListener(this::onClick);
        useBanner();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /**
             * 申请实验室
             */
            case R.id.finduser:
                startActivity(new Intent(getActivity(), ApplyRoomListActivity.class));
                break;
            /**
             * 报告
             */
            case R.id.find_leave:
                startActivity(new Intent(getActivity(), ReportListActivity.class));
                break;
            /**
             * 查看通知
             */
            case R.id.guanligongzi:
                startActivity(new Intent(getActivity(), NoticeListActivity.class));
                break;
            /**
             * 查看公告
             */
            case R.id.fabugonggao:
                startActivity(new Intent(getActivity(), PublicListActivity.class));
                break;
            /**
             * 教师管理
             */
            case R.id.jiaoshiguanli:
                startActivity(new Intent(getActivity(), ManagerRoomActivity.class));
                break;
            /**
             * 器材管理
             */
            case R.id.qicaiguanli:
                startActivity(new Intent(getActivity(), ManagerQicaiActivity.class));
                break;

            default:
                break;
        }
    }

    public void useBanner() {

        Banner banner = getRootview().findViewById(R.id.banner);
        //默认直接设置adapter就行了
        List<Integer> mlist = new ArrayList<>();
        mlist.add(R.drawable.top_one);
        mlist.add(R.drawable.top_two);
        mlist.add(R.drawable.timg_3s);
        mlist.add(R.drawable.timg_4s);
        banner.setAdapter(new ImageAdapter(mlist));
    }
//    private void adddata() {
//        Recom recomBean = new Recom();
//        recomBean.setTitle("颠三倒四");
//        recomBean.setShortdes("主要是ddd干啥的自己写");
//        recomBean.setContent("睡大觉点击后");
//        recomBean.setPicurl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1587872472&di=2733ce925e7733ad0e7254b45d357ba5&src=http://dik.img.kttpdq.com/pic/142/99386/5bdc6bc2302e4423_1440x900.jpg");
//        recomBean.setVideourl("https://media.w3.org/2010/05/sintel/trailer.mp4");
//
//        recomBean.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if (e == null) {
//                    ToastUtil.showTextToast("success");
//                } else {
//                    ToastUtil.showTextToast(e.getMessage());
//                }
//            }
//        });
//        recyclerView.refreshComplete();
//    }

//    private void getdata() {
//        BmobQuery<Recom> recomBmobQuery = new BmobQuery<>();
//        recomBmobQuery.findObjects(new FindListener<Recom>() {
//            @Override
//            public void done(List<Recom> list, BmobException e) {
//                if (e == null) {
//                    mlist.clear();
//                    mlist.addAll(list);
//                    allAdapter.notifyDataSetChanged();
//                } else {
//
//                }
//                recyclerView.refreshComplete();
//            }
//        });
//
//    }

}
