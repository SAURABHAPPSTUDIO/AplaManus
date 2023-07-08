package com.saurabhjadhavcse.aplamanus.Users.Admin.Chicken;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.saurabhjadhavcse.aplamanus.R;

public class ChickenDashboard extends AppCompatActivity {

    LinearLayout addChicken, seeChicken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_dashboard);

        addChicken = findViewById(R.id.AddChicken);
        seeChicken = findViewById(R.id.SeeChicken);

        addChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChickenDashboard.this, ChickenAdd.class));
            }
        });

        seeChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChickenDashboard.this, SeeChicken.class));
            }
        });
    }
}