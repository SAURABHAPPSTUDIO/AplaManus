package com.saurabhjadhavcse.aplamanus.Users.Customer.Mutton;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
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
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.MuttonModel;

import java.util.ArrayList;

public class MuttonSee extends AppCompatActivity {

    ArrayList<MuttonModel> muttonList;

    MuttonAdapter muttonAdapter;

    ImageView backToSDash;

    // FIREBASE

    DatabaseReference root = FirebaseDatabase.getInstance().getReference("Mutton");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutton_see);

        ImageView imageViewNoData = findViewById(R.id.imageViewNoData);

        // RECYCLER VIEW

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMutton);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        muttonList = new ArrayList<>();

        muttonAdapter = new MuttonAdapter(muttonList, this);
        recyclerView.setAdapter(muttonAdapter);
        // Show progress dialog while data is being loaded
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progressbar_food_type);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MuttonModel muttonModel = dataSnapshot.getValue(MuttonModel.class);
                    muttonList.add(muttonModel);
                }
                muttonAdapter.notifyDataSetChanged();
                progressDialog.dismiss(); // Dismiss the progress dialog when data is loaded

                if (muttonList.isEmpty()) {
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