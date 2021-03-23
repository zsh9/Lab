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

import com.bumptech.glide.Glide;
import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Apply;
import com.example.map.mylocation.bean.Report;
import com.example.map.mylocation.ui.ApplyRoomDetailActivity2;
import com.example.map.mylocation.ui.FindReportDetailActivity;
import com.example.map.mylocation.ui.ReleaseLoseActivity;
import com.example.map.mylocation.utils.ToastUtil;
import com.example.map.mylocation.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private List<Apply> list = new ArrayList<>();
    private Context context;

    public ReportAdapter(Context context, List<Apply> typeList) {
        this.context = context;
        this.list = typeList;
    }


    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item, parent, false);
        return new ReportAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportAdapter.ViewHolder holder, int position) {
        final Apply bean = list.get(position);
        Glide.with(context).load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1054245116,1487307134&fm=26&gp=0.jpg").into(holder.tuijianImg);
        holder.des.setText("实验名称：" + bean.getTitle());
        holder.price.setText("申请人：" + bean.getUsername());
        holder.content.setText("实验目标：" + bean.getMubiao());
        holder.type.setText("创建时间：" + bean.getCreatedAt());

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
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("报告");
                dialog.setMessage("报告的提交和查看");
                dialog.setCancelable(true);
                dialog.setPositiveButton("提交报告", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, ReleaseLoseActivity.class);
                        intent.putExtra("bean", bean);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("查看报告", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getreport(bean);
                    }
                });
                dialog.show();


            }
        });
    }

    /**
     * 查勘报告
     *
     * @param bean
     */
    private void getreport(Apply bean) {
        ToastUtil.showTextToast("请稍后");
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Report> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("apply", bean);
                bmobQuery.findObjects(new FindListener<Report>() {
                    @Override
                    public void done(List<Report> list, BmobException e) {
                        if (e == null) {
                            if (list != null && list.size() > 0) {
                                Intent intent = new Intent(context, FindReportDetailActivity.class);
                                intent.putExtra("bean", list.get(0));
                                startActivity(intent);
                            } else {
                                ToastUtil.showTextToast("尚未提交报告，请去提交");
                            }

                        } else {

                        }
                    }
                });
            }
        }).start();


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
