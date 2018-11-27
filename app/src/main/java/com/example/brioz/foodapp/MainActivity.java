package com.example.brioz.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button signin,signup;
    TextView motto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin =(Button)findViewById(R.id.signin);
        signup =(Button)findViewById(R.id.signup);
        motto =(TextView)findViewById(R.id.motto);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signup=new Intent(MainActivity.this, signup.class);
                startActivity(signup);
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin=new Intent(MainActivity.this, signin.class);
                startActivity(signin);
            }
        });
    }
}
