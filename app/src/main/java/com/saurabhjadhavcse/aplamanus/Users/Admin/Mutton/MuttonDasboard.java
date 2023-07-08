package com.saurabhjadhavcse.aplamanus.Users.Admin.Mutton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.saurabhjadhavcse.aplamanus.R;

public class MuttonDasboard extends AppCompatActivity {

    LinearLayout addFish, seeFish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutton_dasboard);

        addFish = findViewById(R.id.AddMutton);
        seeFish = findViewById(R.id.SeeMutton);

        addFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MuttonDasboard.this, MuttonAdd.class));
            }
        });

        seeFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MuttonDasboard.this, SeeMutton.class));
            }
        });
    }
}