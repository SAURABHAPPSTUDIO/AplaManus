package com.saurabhjadhavcse.aplamanus.Users.Admin.Chicken;

import android.app.ProgressDialog;
import android.os.Bundle;

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
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.AdminChickenAdapter;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.ChickenModel;

import java.util.ArrayList;

public class SeeChicken extends AppCompatActivity {

    ArrayList<ChickenModel> chickenList;

    AdminChickenAdapter chickenAdapter;

    // FIREBASE

    DatabaseReference root = FirebaseDatabase.getInstance().getReference("Chicken");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_chicken);

        // RECYCLER VIEW

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewChicken);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        chickenList = new ArrayList<>();
        chickenAdapter = new AdminChickenAdapter(chickenList, this);
        recyclerView.setAdapter(chickenAdapter);

        // Show progress dialog while data is being loaded
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progressbar_food_type);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chickenList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChickenModel chickenModel = dataSnapshot.getValue(ChickenModel.class);
                    chickenList.add(chickenModel);
                }
                chickenAdapter.notifyDataSetChanged();
                progressDialog.dismiss(); // Dismiss the progress dialog when data is loaded
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss(); // Dismiss the progress dialog on cancellation
            }
        });
    }
}