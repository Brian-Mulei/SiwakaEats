package com.example.brioz.foodapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brioz.foodapp.Common.Common;
import com.example.brioz.foodapp.Model.User;
import com.google.android.gms.signin.SignInOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class signin extends AppCompatActivity {

    EditText textphone, textname, password;
    Button signin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        textphone = (MaterialEditText) findViewById(R.id.textphone);
        textname = (MaterialEditText) findViewById(R.id.textname);
        password = (MaterialEditText) findViewById(R.id.password);
        signin1 =(Button) findViewById(R.id.signin1);

        //int firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Users");

        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(signin.this);
                mDialog.setMessage("Please Wait......");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // if user exists in database
                       if(dataSnapshot.child(textphone.getText().toString()).exists()) {
                           //get user details
                           mDialog.dismiss();
                           User user =dataSnapshot.child(textphone.getText().toString()).getValue(User.class);
                            user.setPhone(textphone.getText().toString());
                           if (user.getPassword().equals(password.getText().toString()))
                           {
                               Toast.makeText(signin.this, "Welcome Back!! :)", Toast.LENGTH_SHORT).show();

                               Intent homeIntent = new Intent(signin.this,Home.class);
                               Common.currentUser=user;
                               startActivity(homeIntent);

                           }
                           else
                               {
                              Toast.makeText(signin.this, " Failed login::wrong password!!", Toast.LENGTH_SHORT).show();
                           }

                       }
                       else
                           {
                               mDialog.dismiss();
                            Toast.makeText(signin.this, " Failed login:: User Doesn't exist", Toast.LENGTH_SHORT).show();

                           }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}