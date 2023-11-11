package com.saurabhjadhavcse.aplamanus.Users.Customer.Meat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Fish.FishAdd;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Fish.FishDashboard;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Fish.SeeFish;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Meat.Chicken.ChickenSee;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Meat.Mutton.MuttonSee;

public class MeatDashboard extends AppCompatActivity {

    LinearLayout seeMutton, seeChicken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_dashboard);

        seeMutton = findViewById(R.id.SeeMutton);
        seeChicken = findViewById(R.id.SeeChicken);

        seeMutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeatDashboard.this, MuttonSee.class));
            }
        });

        seeChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeatDashboard.this, ChickenSee.class));
            }
        });
    }
}