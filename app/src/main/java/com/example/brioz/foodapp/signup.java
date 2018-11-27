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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class signup extends AppCompatActivity {

    EditText textphone, textname, password;
    Button signup1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        textphone = (MaterialEditText) findViewById(R.id.textphone);
        textname = (MaterialEditText) findViewById(R.id.textname);
        password = (MaterialEditText) findViewById(R.id.password);
        signup1=(Button) findViewById(R.id.signup1);

        //int firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Users");

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(signup.this);
                mDialog.setMessage("Please Wait......");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //check if user exists
                        if(dataSnapshot.child(textphone.getText().toString()).exists())
                        {
                            Intent homeIntent = new Intent(signup.this,signin.class);
                            Toast.makeText(signup.this, " registered successfully", Toast.LENGTH_SHORT).show();

                            startActivity(homeIntent);

                        }
                        else
                        {
                            mDialog.dismiss();
                            User user=new User(textname.getText().toString(),password.getText().toString());
                            table_user.child(textphone.getText().toString()).setValue(user);
                            Toast.makeText(signup.this, " Phone Number Already registered", Toast.LENGTH_SHORT).show();
                            finish();

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
