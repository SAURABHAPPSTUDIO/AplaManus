package com.saurabhjadhavcse.aplamanus.Users.Admin.Vegetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Fish.FishAdd;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Fish.FishDashboard;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Fish.SeeFish;

public class VegetableDashboard extends AppCompatActivity {

    LinearLayout addVegetable, seeVegetable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_dasboard);

        addVegetable = findViewById(R.id.AddVeggies);
        seeVegetable = findViewById(R.id.SeeVeggies);

        addVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VegetableDashboard.this, VegetableAdd.class));
            }
        });

        seeVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VegetableDashboard.this, SeeVegetable.class));
            }
        });
    }
}