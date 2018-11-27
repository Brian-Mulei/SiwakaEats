package com.example.brioz.foodapp.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.brioz.foodapp.Interface.itemClickListener;
import com.example.brioz.foodapp.Model.order;
import com.example.brioz.foodapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView cartname,cartprice;
    public ImageView cartimage;

    private itemClickListener itemClickListener;

    public void setCartname(TextView cartname) {
        this.cartname = cartname;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        cartname=(TextView)itemView.findViewById(R.id.cartname);
        cartprice=(TextView)itemView.findViewById(R.id.cartprice);
        cartimage=(ImageView)itemView.findViewById(R.id.cartimage);

    }

    @Override
    public void onClick(View v) {

    }
}


public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<order> listData= new ArrayList<>();
    private Context context;

    public CartAdapter(List<order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout,viewGroup,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(" "+listData.get(i).getQuantity(), Color.RED);
        cartViewHolder.cartimage.setImageDrawable(drawable);

        Locale locale =new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price =(Integer.parseInt(listData.get(i).getPrice()))*Integer.parseInt(listData.get(i).getQuantity());

        cartViewHolder.cartprice.setText(fmt.format(price));

        cartViewHolder.cartname.setText(listData.get(i).getFoodName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}