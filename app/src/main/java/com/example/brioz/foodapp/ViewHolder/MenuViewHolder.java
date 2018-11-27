package com.example.brioz.foodapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brioz.foodapp.Interface.itemClickListener;
import com.example.brioz.foodapp.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView menu_name;
        public ImageView menu_image;
        private itemClickListener itemClicklistener;



    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        menu_name=(TextView)itemView.findViewById(R.id.menu_name);
        menu_image =(ImageView)itemView.findViewById(R.id.menu_image);

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
