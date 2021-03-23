package com.example.map.mylocation.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Report;
import com.example.map.mylocation.ui.FindReportDetailActivity;
import com.example.map.mylocation.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class ManagerReportAdapter extends RecyclerView.Adapter<ManagerReportAdapter.ViewHolder> {
    private List<Report> list = new ArrayList<>();
    private Context context;

    public ManagerReportAdapter(Context context, List<Report> typeList) {
        this.context = context;
        this.list = typeList;
    }


    @Override
    public ManagerReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item, parent, false);
        return new ManagerReportAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ManagerReportAdapter.ViewHolder holder, int position) {
        final Report bean = list.get(position);
        if (StringUtils.isEmpty(bean.getPicurl())) {
            Glide.with(context).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1054245116,1487307134&fm=26&gp=0.jpg").into(holder.tuijianImg);
        } else {
            Glide.with(context).load(bean.getPicurl()).into(holder.tuijianImg);
        }
        holder.des.setText("实验名称：" + bean.getTitle());
        holder.price.setText("申请人：" + bean.getApply().getUsername());
        holder.content.setText("实验目标：" + bean.getApply().getMubiao());
        holder.type.setText("时间：" + bean.getCreatedAt());
        if (bean.getStatus() == 1) {
            holder.ever.setText("已评价");
            holder.ever.setTextColor(context.getResources().getColor(R.color.colorBlue));
        } else {
            holder.ever.setText("尚未评价");
            holder.ever.setTextColor(context.getResources().getColor(R.color.error_red));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FindReportDetailActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView tuijianImg;
        TextView des, price, content, type, ever;

        public ViewHolder(View view) {
            super(view);
            tuijianImg = view.findViewById(R.id.iv_activity_cover);
            des = view.findViewById(R.id.des);
            price = view.findViewById(R.id.price);
            type = view.findViewById(R.id.type);
            content = view.findViewById(R.id.content);
            ever = view.findViewById(R.id.ever);
        }
    }
}
