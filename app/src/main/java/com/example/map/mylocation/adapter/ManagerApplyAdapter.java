package com.example.map.mylocation.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Apply;
import com.example.map.mylocation.ui.ApplyRoomDetailActivity2;
import com.example.map.mylocation.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class ManagerApplyAdapter extends RecyclerView.Adapter<ManagerApplyAdapter.ViewHolder> {
    private List<Apply> list = new ArrayList<>();
    private Context context;

    public ManagerApplyAdapter(Context context, List<Apply> typeList) {
        this.context = context;
        this.list = typeList;
    }


    @Override
    public ManagerApplyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recomend, parent, false);
        return new ManagerApplyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ManagerApplyAdapter.ViewHolder holder, int position) {
        final Apply bean = list.get(position);
        Glide.with(context).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1054245116,1487307134&fm=26&gp=0.jpg").into(holder.tuijianImg);
        holder.des.setText("实验名称：" + bean.getTitle());
        holder.price.setText("申请人：" + bean.getUsername());
        holder.content.setText("实验目标：" + bean.getMubiao());
        if (bean.getStatus() == 0) {
            holder.type.setText("申请中");
            holder.type.setTextColor(context.getResources().getColor(R.color.error_red));
        } else if (bean.getStatus() == 1) {
            holder.type.setText("同意");
            holder.type.setTextColor(context.getResources().getColor(R.color.colorBlue));
        } else if (bean.getStatus() == 2) {
            holder.type.setText("拒绝");
            holder.type.setTextColor(context.getResources().getColor(R.color.error_red));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ApplyRoomDetailActivity2.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//                dialog.setTitle("取消申请");
//                dialog.setMessage("是否取消教室申请？");
//                dialog.setCancelable(true);
//                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        bean.delete(bean.getObjectId(), new UpdateListener() {
//                            @Override
//                            public void done(BmobException e) {
//                                if (e == null) {
//                                    ToastUtils.showShort("取消成功");
//                                    list.remove(position);
//                                    notifyDataSetChanged();
//                                } else {
//
//                                }
//                            }
//                        });
//                    }
//                });
//                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                dialog.show();
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView tuijianImg;
        TextView des, price, content, type;

        public ViewHolder(View view) {
            super(view);
            tuijianImg = view.findViewById(R.id.iv_activity_cover);
            des = view.findViewById(R.id.des);
            price = view.findViewById(R.id.price);
            type = view.findViewById(R.id.type);
            content = view.findViewById(R.id.content);
        }
    }
}
