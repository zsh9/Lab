package com.example.map.mylocation.ui;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.adapter.RoomAdapter;
import com.example.map.mylocation.bean.Persion;
import com.example.map.mylocation.bean.RoomBean;
import com.example.map.mylocation.utils.ToastUtil;
import com.example.map.mylocation.view.NormalToolbar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 教室管理
 */
public class ManagerRoomActivity extends BaseSimpleActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_manager_room;
    }

    private NormalToolbar topbar;
    private XRecyclerView brvList;
    private ImageView post;


    RoomAdapter adapter;
    List<RoomBean> mlist = new ArrayList<>();
    SearchView mSearchView;

    @Override
    public void initView() {
        mSearchView = findViewById(R.id.search);

        topbar = findViewById(R.id.topbar);
        topbar.setMainTitle("教室管理");
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
                startActivity(new Intent(ManagerRoomActivity.this, AddRoomActivity.class));
            }
        });
        adapter = new RoomAdapter(this, mlist);
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
        initData();
        setListener();
    }

    private void initData() {
        mSearchView.setIconifiedByDefault(false);//设置搜索图标是否显示在搜索框内
        //1:回车
        //2:前往
        //3:搜索
        //4:发送
        //5:下一項
        //6:完成
        mSearchView.setImeOptions(2);//设置输入法搜索选项字段，默认是搜索，可以是：下一页、发送、完成等
//        mSearchView.setInputType(1);//设置输入类型
//        mSearchView.setMaxWidth(200);//设置最大宽度
        mSearchView.setQueryHint("输入教室名称");//设置查询提示字符串
//        mSearchView.setSubmitButtonEnabled(true);//设置是否显示搜索框展开时的提交按钮
        //设置SearchView下划线透明
        setUnderLinetransparent(mSearchView);
    }

    private void setListener() {
        mSearchView.setSubmitButtonEnabled(true);
        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
//                LogUtil.e(MainActivity.class, "=====query=" + query);
                find(query);
                return false;
            }

            //当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
//                LogUtil.e(MainActivity.class, "=====newText=" + newText);
                return false;
            }
        });
    }


    private void find(String msg) {
        if (StringUtils.isEmpty(msg)) {
            ToastUtil.showTextToast("搜索结果不能为空");
            return;
        }
        BmobQuery<RoomBean> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("roomname", msg);
        bmobQuery.findObjects(new FindListener<RoomBean>() {
            @Override
            public void done(List<RoomBean> list, BmobException e) {
                if (e == null) {
                    if (list != null && list.size() > 0) {
                        mlist.clear();
                        mlist.addAll(list);
                        Collections.reverse(mlist);
                        adapter.notifyDataSetChanged();
                        brvList.refreshComplete();
                    } else {
                        ToastUtil.showTextToast("尚未查到");
                    }

                } else {
                    ToastUtils.showShort(e.getMessage());
                    brvList.refreshComplete();
                }

            }
        });
    }

    /**
     * 设置SearchView下划线透明
     **/
    private void setUnderLinetransparent(SearchView searchView) {
        try {
            Class<?> argClass = searchView.getClass();
            // mSearchPlate是SearchView父布局的名字
            Field ownField = argClass.getDeclaredField("mSearchPlate");
            ownField.setAccessible(true);
            View mView = (View) ownField.get(searchView);
            mView.setBackgroundColor(Color.TRANSPARENT);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadDatas2();
    }

    private void loadDatas2() {
        BmobQuery<RoomBean> bmobQuery = new BmobQuery<>();
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
//        bmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser(Persion.class));
        bmobQuery.findObjects(new FindListener<RoomBean>() {
            @Override
            public void done(List<RoomBean> list, BmobException e) {
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
        BmobQuery<RoomBean> bmobQuery = new BmobQuery<>();
        bmobQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
//        bmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser(Persion.class));
        bmobQuery.findObjects(new FindListener<RoomBean>() {
            @Override
            public void done(List<RoomBean> list, BmobException e) {
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