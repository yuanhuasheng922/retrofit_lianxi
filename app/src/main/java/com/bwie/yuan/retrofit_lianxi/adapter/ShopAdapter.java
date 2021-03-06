package com.bwie.yuan.retrofit_lianxi.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.yuan.retrofit_lianxi.R;
import com.bwie.yuan.retrofit_lianxi.bean.ShopBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{
        private List<ShopBean.ResultBean> mDatas;
        private Context mContext;

    public ShopAdapter(Context mContext) {
        this.mContext = mContext;
        mDatas=new ArrayList<>();
    }

    public void setmDatas(List<ShopBean.ResultBean> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.adapter_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ViewHolder holder=viewHolder;

        String masterPic = mDatas.get(i).getMasterPic();
        Uri uri = Uri.parse(masterPic);
        holder.image.setImageURI(uri);

        holder.name.setText(mDatas.get(i).getCommodityName());
        holder.price.setText("$"+mDatas.get(i).getPrice()+".00");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView image;
        private final TextView name;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.iamgess);
            name = itemView.findViewById(R.id.names);
            price = itemView.findViewById(R.id.price);


        }
    }
}
