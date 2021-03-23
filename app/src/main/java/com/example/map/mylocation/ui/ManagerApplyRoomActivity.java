package com.example.map.mylocation.ui;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.adapter.ManagerApplyAdapter;
import com.example.map.mylocation.adapter.RecommmendAdapter;
import com.example.map.mylocation.bean.Apply;
import com.example.map.mylocation.view.NormalToolbar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ManagerApplyRoomActivity extends BaseSimpleActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_manager_apply_room;
    }

    private NormalToolbar topbar;
    private XRecyclerView brvList;


    ManagerApplyAdapter adapter;
    List<Apply> mlist = new ArrayList<>();


    @Override
    public void initView() {
        topbar = findViewById(R.id.topbar);
        topbar.setMainTitle("申请列表");
        topbar.setLeftTitleText("返回");
        topbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        brvList = findViewById(R.id.brv_list);

        adapter = new ManagerApplyAdapter(this, mlist);
        brvList.setLayoutManager(new LinearLayoutManager(this));
        brvList.setAdapter(adapter);
        brvList.setLoadingMoreEnabled(false);
        brvList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadDatas2();
            }

            @Override
            public void onLoadMore() {

            }
        });
        loadDatas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDatas2();
    }
    private void loadDatas2() {
        BmobQuery<Apply> bmobQuery = new BmobQuery<>();
//        bmobQuery.addWhereEqualTo("status", 0);
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        bmobQuery.findObjects(new FindListener<Apply>() {
            @Override
            public void done(List<Apply> list, BmobException e) {
                if (e == null) {
                    mlist.clear();
                    mlist.addAll(list);
                    Collections.reverse(mlist);
                    adapter.notifyDataSetChanged();
                    brvList.refreshComplete();
                } else {
                    ToastUtils.showShort(e.getMessage());
                    brvList.refreshComplete();
                }

            }
        });
    }
    private void loadDatas() {
        BmobQuery<Apply> bmobQuery = new BmobQuery<>();
//        bmobQuery.addWhereEqualTo("status", 0);
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);   // 先从缓存获取数据，如果没有，再从网络获取。
        bmobQuery.findObjects(new FindListener<Apply>() {
            @Override
            public void done(List<Apply> list, BmobException e) {
                if (e == null) {
                    mlist.clear();
                    mlist.addAll(list);
                    Collections.reverse(mlist);
                    adapter.notifyDataSetChanged();
                    brvList.refreshComplete();
                } else {
                    ToastUtils.showShort(e.getMessage());
                    brvList.refreshComplete();
                }

            }
        });
    }
}