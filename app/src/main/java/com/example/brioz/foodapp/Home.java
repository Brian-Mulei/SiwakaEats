package com.example.brioz.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brioz.foodapp.Common.Common;
import com.example.brioz.foodapp.Interface.itemClickListener;
import com.example.brioz.foodapp.Model.Category;
import com.example.brioz.foodapp.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        FirebaseDatabase database;
        ImageView menu_image;
        DatabaseReference Category;
        TextView session_name;
        RecyclerView nav_menu;
        RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);
        //firebase
        database = FirebaseDatabase.getInstance();
        Category = database.getReference("Category");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartintent =new Intent(Home.this,Cart.class);
                startActivity(cartintent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //name for user
        View headerView = navigationView.getHeaderView(0);
        session_name =(TextView)headerView.findViewById(R.id.session_name);
        session_name.setText(Common.currentUser.getName());
        //load menu
        nav_menu=(RecyclerView)findViewById(R.id.nav_menu);
        nav_menu.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        nav_menu.setLayoutManager(layoutManager);
        loadMenu();
    }


    private void loadMenu(){
             adapter = new FirebaseRecyclerAdapter<com.example.brioz.foodapp.Model.Category, MenuViewHolder>(Category.class,R.layout.menu_item,MenuViewHolder.class,Category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, com.example.brioz.foodapp.Model.Category model, int position) {
                viewHolder.menu_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.menu_image);
                final Category clickItem = model;
                viewHolder.setItemClicklistener(new itemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                       Toast.makeText(Home.this,""+clickItem.getName(), Toast.LENGTH_SHORT).show();

                        Intent foodlist =new Intent(Home.this,FoodList.class);

                        foodlist.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodlist);
                    }
                });
            }
        };
        nav_menu.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {

        } else if (id == R.id.nav_cart) {
            Intent cartintent =new Intent(Home.this,Cart.class);
            startActivity(cartintent);

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_logout) {
            Intent logout =new Intent(Home.this,signin.class);

            startActivity(logout);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
