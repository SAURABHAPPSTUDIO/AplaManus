package com.saurabhjadhavcse.aplamanus.Users.Customer.Vegetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.FishModel;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.VegetableModel;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Fish.FishAdapter;

import java.util.ArrayList;

public class VegetableSee extends AppCompatActivity {

    ArrayList<VegetableModel> vegetableList;

    VegetableAdapter vegetableAdapter;


    // FIREBASE

    DatabaseReference root = FirebaseDatabase.getInstance().getReference("Vegetable");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_see);

        ImageView imageViewNoData = findViewById(R.id.imageViewNoData);

        // RECYCLER VIEW

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewVegetables);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        vegetableList = new ArrayList<>();
        vegetableAdapter = new VegetableAdapter(vegetableList, this);
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

                if (vegetableList.isEmpty()) {
                    imageViewNoData.setVisibility(View.VISIBLE);
                } else {
                    imageViewNoData.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss(); // Dismiss the progress dialog on cancellation
            }
        });
    }
}