package com.example.map.mylocation.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Notice;
import com.example.map.mylocation.bean.Persion;
import com.example.map.mylocation.bean.Public;
import com.example.map.mylocation.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class PublishAdapter extends RecyclerView.Adapter<PublishAdapter.ViewHolder> {
    private List<Public> list = new ArrayList<>();
    private Context context;

    public PublishAdapter(Context context, List<Public> typeList) {
        this.context = context;
        this.list = typeList;
    }


    @Override
    public PublishAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.public_item, parent, false);
        return new PublishAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PublishAdapter.ViewHolder holder, int position) {
        final Public bean = list.get(position);
//        Glide.with(context).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1054245116,1487307134&fm=26&gp=0.jpg").into(holder.tuijianImg);
        holder.des.setText("公告主题：" + bean.getName());
        holder.content.setText("公告内容：" + bean.getContent());
        holder.type.setText("公告时间：" + bean.getCreatedAt());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (BmobUser.getCurrentUser(Persion.class).getFlag() == 0) {

                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setTitle("确定删除");
                    dialog.setMessage("是否删除该通知？");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            bean.delete(bean.getObjectId(), new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        ToastUtils.showShort("取消成功");
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
                }

                return false;
            }
        });
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

            des = view.findViewById(R.id.des);

            type = view.findViewById(R.id.type);
            content = view.findViewById(R.id.content);
        }
    }
}
