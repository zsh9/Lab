package com.example.map.mylocation.ui;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.adapter.ManagerReportAdapter;
import com.example.map.mylocation.bean.Apply;
import com.example.map.mylocation.bean.Report;
import com.example.map.mylocation.view.NormalToolbar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 *
 */
public class MaigerReportListActivity extends BaseSimpleActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_maiger_report_list;
    }


    private NormalToolbar topbar;
    private XRecyclerView brvList;


    ManagerReportAdapter adapter;
    List<Report> mlist = new ArrayList<>();


    @Override
    public void initView() {
        topbar = findViewById(R.id.topbar);
        topbar.setMainTitle("报告评价管理");
        topbar.setLeftTitleText("返回");
        topbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        brvList = findViewById(R.id.brv_list);

        adapter = new ManagerReportAdapter(this, mlist);
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
        BmobQuery<Report> bmobQuery = new BmobQuery<>();
        bmobQuery.include("apply");
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
//        bmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser(Persion.class));
        /**
         * 必须得同意的才能提交报告
         */
//        bmobQuery.addWhereEqualTo("status", 1);
        bmobQuery.findObjects(new FindListener<Report>() {
            @Override
            public void done(List<Report> list, BmobException e) {
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
        BmobQuery<Report> bmobQuery = new BmobQuery<>();
        bmobQuery.include("apply");
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
//        bmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser(Persion.class));
        /**
         * 必须得同意的才能提交报告
         */
//        bmobQuery.addWhereEqualTo("status", 1);
        bmobQuery.findObjects(new FindListener<Report>() {
            @Override
            public void done(List<Report> list, BmobException e) {
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