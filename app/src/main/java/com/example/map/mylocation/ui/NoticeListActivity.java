package com.example.map.mylocation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.adapter.NoticeAdapter;
import com.example.map.mylocation.adapter.RecommmendAdapter;
import com.example.map.mylocation.bean.Apply;
import com.example.map.mylocation.bean.Notice;
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

/**
 * 通知列表
 */
public class NoticeListActivity extends BaseSimpleActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_notice_list;
    }

    private NormalToolbar topbar;
    private XRecyclerView brvList;
    private ImageView post;


    NoticeAdapter adapter;
    List<Notice> mlist = new ArrayList<>();


    @Override
    public void initView() {
        topbar = findViewById(R.id.topbar);
        topbar.setMainTitle("通知");
        topbar.setLeftTitleText("返回");
        topbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        brvList = findViewById(R.id.brv_list);
        post = findViewById(R.id.post);
        if (BmobUser.getCurrentUser(Persion.class).getFlag() == 0) {
            post.setVisibility(View.INVISIBLE);
        }
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoticeListActivity.this, NoticeActivity.class));
            }
        });
        adapter = new NoticeAdapter(this, mlist);
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
        BmobQuery<Notice> bmobQuery = new BmobQuery<>();
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
//        bmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser(Persion.class));
        bmobQuery.findObjects(new FindListener<Notice>() {
            @Override
            public void done(List<Notice> list, BmobException e) {
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
        BmobQuery<Notice> bmobQuery = new BmobQuery<>();
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
//        bmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser(Persion.class));
        bmobQuery.findObjects(new FindListener<Notice>() {
            @Override
            public void done(List<Notice> list, BmobException e) {
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