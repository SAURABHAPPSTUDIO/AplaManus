package com.saurabhjadhavcse.aplamanus.Users.Admin.Vegetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.AdminFishAdapter;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.AdminVegetableAdapter;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.FishModel;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.VegetableModel;

import java.util.ArrayList;

public class SeeVegetable extends AppCompatActivity {

    ArrayList<VegetableModel> vegetableList;

    AdminVegetableAdapter vegetableAdapter;

    // FIREBASE

    DatabaseReference root = FirebaseDatabase.getInstance().getReference("Vegetable");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_vegetable);

        // RECYCLER VIEW

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewVegetable);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        vegetableList = new ArrayList<>();
        vegetableAdapter = new AdminVegetableAdapter(vegetableList, this);
        recyclerView.setAdapter(vegetableAdapter);

        // Show progress dialog while data is being loaded
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progressbar_food_type);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vegetableList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    VegetableModel vegetableModel = dataSnapshot.getValue(VegetableModel.class);
                    vegetableList.add(vegetableModel);
                }
                vegetableAdapter.notifyDataSetChanged();
                progressDialog.dismiss(); // Dismiss the progress dialog when data is loaded
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss(); // Dismiss the progress dialog on cancellation
            }
        });
    }
}