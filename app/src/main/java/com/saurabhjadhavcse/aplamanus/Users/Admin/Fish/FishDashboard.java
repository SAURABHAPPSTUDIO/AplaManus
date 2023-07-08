package com.saurabhjadhavcse.aplamanus.Users.Admin.Fish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.saurabhjadhavcse.aplamanus.R;

public class FishDashboard extends AppCompatActivity {

    LinearLayout addFish, seeFish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_dashboard);

        addFish = findViewById(R.id.AddFish);
        seeFish = findViewById(R.id.SeeFish);

        addFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FishDashboard.this, FishAdd.class));
            }
        });

        seeFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FishDashboard.this, SeeFish.class));
            }
        });
    }
}