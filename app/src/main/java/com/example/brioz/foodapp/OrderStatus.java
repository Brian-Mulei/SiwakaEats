package com.example.brioz.foodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.brioz.foodapp.Common.Common;
import com.example.brioz.foodapp.Model.Request;
import com.example.brioz.foodapp.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;


    FirebaseDatabase database;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        database = FirebaseDatabase.getInstance();
        requests=database.getReference("Request");
        recyclerView= (RecyclerView)findViewById(R.id.listorders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders(Common.currentUser.getTextphone());

    }

    private void loadOrders(String textphone) {
        adapter=new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                    Request.class,
                    R.layout.order_layout,
                OrderViewHolder.class,
                requests.orderByChild("phone")
                        .equalTo(textphone)
        ){
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                //totalprice,order_phone,order_status,order_id;
                viewHolder.order_id.setText(adapter.getRef(position).getKey());
                viewHolder.order_status.setText(convertCodeToStatus(model.getStatus()));
              viewHolder.totalprice.setText(model.getTotal());
                viewHolder.order_phone.setText(model.getPhone());

            }
        };
        recyclerView.setAdapter(adapter);
    }

    private String convertCodeToStatus(String status) {
        if(("0").equals(status))
            return "Order Placed";
        else if(("1").equals(status))
            return "Order is being Prepared";
        else
            return "Order was picked up";

    }
}
