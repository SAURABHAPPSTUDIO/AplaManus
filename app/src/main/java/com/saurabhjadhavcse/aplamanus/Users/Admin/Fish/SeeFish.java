package com.saurabhjadhavcse.aplamanus.Users.Admin.Fish;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.AdminFishAdapter;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.FishModel;

import java.util.ArrayList;

public class SeeFish extends AppCompatActivity {

    ArrayList<FishModel> fishList;

    AdminFishAdapter fishAdapter;

    ImageView backToSDash;

    // FIREBASE

    DatabaseReference root = FirebaseDatabase.getInstance().getReference("Fish");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_fish);

        // RECYCLER VIEW

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFish);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        fishList = new ArrayList<>();
        fishAdapter = new AdminFishAdapter(fishList, this);
        recyclerView.setAdapter(fishAdapter);

        // Show progress dialog while data is being loaded
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progressbar_food_type);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fishList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FishModel fishModel = dataSnapshot.getValue(FishModel.class);
                    fishList.add(fishModel);
                }
                fishAdapter.notifyDataSetChanged();
                progressDialog.dismiss(); // Dismiss the progress dialog when data is loaded
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss(); // Dismiss the progress dialog on cancellation
            }
        });
    }
}