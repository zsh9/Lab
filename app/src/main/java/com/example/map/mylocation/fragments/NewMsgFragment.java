package com.example.map.mylocation.fragments;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.map.mylocation.R;
import com.example.map.mylocation.adapter.AllAdapter;
import com.example.map.mylocation.bean.NewResponse;
import com.example.map.mylocation.bean.Newslist;
import com.example.map.mylocation.common.Commons;
import com.example.map.mylocation.utils.JsonUtils;
import com.example.map.mylocation.utils.ToastUtil;
import com.example.map.mylocation.view.NormalToolbar;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;

import rxhttp.wrapper.param.RxHttp;

/**
 * 新闻
 */
public class NewMsgFragment extends BaseFragment {

    NormalToolbar normal_toolbar_reg;
    XRecyclerView top_recycleview;

    int page = 1;
    AllAdapter allAdapter;
    List<Newslist> mlist = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_newmsg;
    }

    @Override
    public void initView() {
        normal_toolbar_reg = getRootview().findViewById(R.id.normal_toolbar_reg);
        normal_toolbar_reg.setMainTitle("新闻资讯");
        allAdapter = new AllAdapter(getActivity(), mlist);
        top_recycleview = getRootview().findViewById(R.id.top_recycleview);
        top_recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        top_recycleview.setAdapter(allAdapter);
        top_recycleview.setLoadingMoreEnabled(false);
        top_recycleview.setPullRefreshEnabled(true);
        top_recycleview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        top_recycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                getdata();

            }

            @Override
            public void onLoadMore() {

            }
        });
        getdata();
    }

    /**
     * 获取新闻自信信息 框架
     */
    private void getdata() {
        RxHttp.postForm(Commons.msgurl)
                .add("key", Commons.MSGKEY)
                .add("num", 10)
                .add("page", page)
                .asString()
                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
                .subscribe(s -> {
                    Log.e("result", s);
                    /**
                     * 解析数据
                     */
                    NewResponse regResponse = (NewResponse) JsonUtils.fromJson(s, NewResponse.class);
                    if (regResponse.getCode() == 200) {
                        page = page + 1;
                        mlist.clear();
                        mlist.addAll(regResponse.getNewslist());
                        allAdapter.notifyDataSetChanged();
                    } else {
                        page = 1;
                        ToastUtil.showTextToast(regResponse.getMsg());
                    }
                    top_recycleview.refreshComplete();

                    //成功回调
                }, (OnError) -> {
                    ToastUtil.showTextToast("接口错误");
                    top_recycleview.refreshComplete();
                });

    }
}
