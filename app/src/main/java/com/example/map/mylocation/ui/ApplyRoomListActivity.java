package com.example.map.mylocation.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.adapter.RecommmendAdapter;
import com.example.map.mylocation.bean.Apply;
import com.example.map.mylocation.bean.Persion;
import com.example.map.mylocation.view.NormalToolbar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ApplyRoomListActivity extends BaseSimpleActivity {

    private NormalToolbar topbar;
    private XRecyclerView brvList;
    private ImageView post;


    RecommmendAdapter adapter;
    List<Apply> mlist = new ArrayList<>();

    @Override
    public int getContentViewResId() {
        return R.layout.activity_apply_room_list;
    }

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
        post = findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ApplyRoomListActivity.this, ApplyRoomActivity.class));
            }
        });
        adapter = new RecommmendAdapter(this, mlist);
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
        bmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser(Persion.class));
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
        bmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser(Persion.class));
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
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