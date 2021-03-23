package com.example.map.mylocation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.RoomBean;
import com.example.map.mylocation.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class ChooseRoomAdapter extends RecyclerView.Adapter<ChooseRoomAdapter.ViewHolder> {
    private List<RoomBean> list = new ArrayList<>();
    private Context context;

    public ChooseRoomAdapter(Context context, List<RoomBean> typeList) {
        this.context = context;
        this.list = typeList;
    }


    @Override
    public ChooseRoomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        return new ChooseRoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChooseRoomAdapter.ViewHolder holder, int position) {
        final RoomBean bean = list.get(position);

        holder.des.setText("教室名称：" + bean.getRoomname());
        holder.price.setText("教室位置：" + bean.getLoaction());
//        holder.content.setText("实验目标：" + bean.getMubiao());
        if (bean.getUsed() == 1) {
            holder.type.setText("已使用");
            holder.type.setTextColor(context.getResources().getColor(R.color.error_red));
        } else if (bean.getUsed() == 0) {
            holder.type.setText("未使用");
            holder.type.setTextColor(context.getResources().getColor(R.color.colorBlue));
        }
        //点击事假，首先判断是否为空，不为空的时候再进行操作
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.OnItemClick(holder.itemView, position);
                    //关闭Activity

                }
            });
        }


    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
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
