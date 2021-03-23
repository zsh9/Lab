package com.example.map.mylocation.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.map.mylocation.R;
import com.example.map.mylocation.bean.Newslist;
import com.example.map.mylocation.ui.WebOnlyGetUlrActivity;

import java.util.ArrayList;
import java.util.List;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {
    private List<Newslist> list = new ArrayList<>();
    private Context context;

    public AllAdapter(Context context, List<Newslist> typeList) {
        this.context = context;
        this.list = typeList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Newslist bean = list.get(position);

//        Glide.with(context).load(bean.getPicUrl()).into(holder.tuijianImg);
        holder.des.setText(bean.getTitle());
        holder.price.setText(bean.getCtime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebOnlyGetUlrActivity.startWebActivity((Activity) context, bean.getUrl(), bean.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView des, price;

        public ViewHolder(View view) {
            super(view);

            des = view.findViewById(R.id.des);
            price = view.findViewById(R.id.price);
        }
    }
}
