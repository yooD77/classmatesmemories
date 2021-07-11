package com.example.classmatesmemories_xxd_35_20191213;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView exit,modify,add,glance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        exit = findViewById(R.id.tv_exit);
        modify = findViewById(R.id.tv_modify);
        add = findViewById(R.id.tv_add);
        glance = findViewById(R.id.tv_query);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(it);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Main2Activity.this,add.class);
                startActivity(it);
            }
        });
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Main2Activity.this,Modify.class);
                startActivity(it);
            }
        });
        glance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent it = new Intent(Main2Activity.this,queryAll.class);
            startActivity(it);
            }
        });
    }
}
