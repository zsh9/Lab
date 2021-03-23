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
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Persion;
import com.example.map.mylocation.bean.RoomBean;
import com.example.map.mylocation.ui.RoomDetailActivity;
import com.example.map.mylocation.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private List<RoomBean> list = new ArrayList<>();
    private Context context;

    public RoomAdapter(Context context, List<RoomBean> typeList) {
        this.context = context;
        this.list = typeList;
    }


    @Override
    public RoomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        return new RoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RoomAdapter.ViewHolder holder, int position) {
        final RoomBean bean = list.get(position);

        holder.des.setText("教室名称：" + bean.getRoomname());
        holder.price.setText("教室位置：" + bean.getLoaction());
        if (bean.getUsed() == 1) {
            holder.type.setText("已使用");
            holder.type.setTextColor(context.getResources().getColor(R.color.error_red));
        } else if (bean.getUsed() == 0) {
            holder.type.setText("未使用");
            holder.type.setTextColor(context.getResources().getColor(R.color.colorBlue));
        }
//        holder.content.setText("实验目标：" + bean.getMubiao());
//        if (bean.getStatus() == 0) {
//            holder.type.setText("申请中");
//            holder.type.setTextColor(context.getResources().getColor(R.color.error_red));
//        } else if (bean.getStatus() == 1) {
//            holder.type.setText("同意");
//            holder.type.setTextColor(context.getResources().getColor(R.color.colorBlue));
//        } else if (bean.getStatus() == 2) {
//            holder.type.setText("拒绝");
//            holder.type.setTextColor(context.getResources().getColor(R.color.error_red));
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RoomDetailActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });
        if(BmobUser.getCurrentUser(Persion.class).getFlag()!=0){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setTitle("删除");
                    dialog.setMessage("是否删除？");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            bean.delete(bean.getObjectId(), new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        ToastUtils.showShort("删除成功");
                                        list.remove(position);
                                        notifyDataSetChanged();
                                    } else {

                                    }
                                }
                            });
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                    return false;
                }
            });
        }

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
