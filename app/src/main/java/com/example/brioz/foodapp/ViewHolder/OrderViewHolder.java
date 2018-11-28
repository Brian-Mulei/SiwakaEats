package com.example.brioz.foodapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.brioz.foodapp.Interface.itemClickListener;
import com.example.brioz.foodapp.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView totalprice,order_phone,order_status,order_id;

    private itemClickListener itemClicklistener;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        totalprice =(TextView)itemView.findViewById(R.id.totalprice);
        order_phone =(TextView)itemView.findViewById(R.id.order_phone);
        order_status =(TextView)itemView.findViewById(R.id.order_status);
        order_id =(TextView)itemView.findViewById(R.id.order_id);

        itemView.setOnClickListener(this);


    }

    public void setItemClicklistener(itemClickListener itemClicklistener) {
        this.itemClicklistener = itemClicklistener;
    }



    @Override
    public void onClick(View v) {
        itemClicklistener.onClick(v,getAdapterPosition(),false);

    }
}
