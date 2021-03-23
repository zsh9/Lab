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
import com.example.map.mylocation.bean.QiCai;
import com.example.map.mylocation.bean.RoomBean;
import com.example.map.mylocation.ui.QiCaiDetailActivity;
import com.example.map.mylocation.ui.RoomDetailActivity;
import com.example.map.mylocation.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class QiCaiAdapter extends RecyclerView.Adapter<QiCaiAdapter.ViewHolder> {
    private List<QiCai> list = new ArrayList<>();
    private Context context;

    public QiCaiAdapter(Context context, List<QiCai> typeList) {
        this.context = context;
        this.list = typeList;
    }


    @Override
    public QiCaiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qicai_item, parent, false);
        return new QiCaiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QiCaiAdapter.ViewHolder holder, int position) {
        final QiCai bean = list.get(position);

        holder.name.setText("仪器名称：" + bean.getName());
        holder.size.setText("仪器尺寸：" + bean.getSize());
        holder.number.setText("仪器数量：" + bean.getNumber() + "台");

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
                Intent intent = new Intent(context, QiCaiDetailActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });
        if (BmobUser.getCurrentUser(Persion.class).getFlag() != 0) {
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
        TextView name, size, number, beizhu;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            size = view.findViewById(R.id.size);
            number = view.findViewById(R.id.number);
            beizhu = view.findViewById(R.id.beizhu);
        }
    }
}