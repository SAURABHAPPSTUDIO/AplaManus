package com.saurabhjadhavcse.aplamanus.Users.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Chicken.ChickenDashboard;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Fish.FishDashboard;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Mutton.MuttonDasboard;


public class AddItemDashboard extends AppCompatActivity {

    LinearLayout addFish, addChicken, addMutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_dashboard);

        addFish = findViewById(R.id.AddFish);
        addChicken = findViewById(R.id.AddChicken);
        addMutton = findViewById(R.id.AddMutton);

        addFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddItemDashboard.this, FishDashboard.class));
            }
        });

        addChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddItemDashboard.this, ChickenDashboard.class));
            }
        });

        addMutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddItemDashboard.this, MuttonDasboard.class));
            }
        });
    }
}