package com.example.brioz.foodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.brioz.foodapp.Database.Database;
import com.example.brioz.foodapp.Model.order;
import com.example.brioz.foodapp.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference request;
    TextView txttotal;
    Button placeorder;
    List<order> cart =new ArrayList<>();

    CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database = FirebaseDatabase.getInstance();
        request=database.getReference("Request");

        recyclerView =(RecyclerView)findViewById(R.id.Cart);
        txttotal =(TextView)findViewById(R.id.txttotal);
        placeorder=(Button) findViewById(R.id.placeorder);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        
        loadlistFood();
    }

    private void loadlistFood() {

        cart = new Database(this).getcarts();
        adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);

        int total=0;
        for(order order:cart)
            total=+(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        Locale locale =new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txttotal.setText(fmt.format(total));


    }

    @Override
    public void onClick(View v) {

    }
}
