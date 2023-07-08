package com.saurabhjadhavcse.aplamanus.Users.Customer.Cart;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.FishModel;

import java.util.ArrayList;

public class MyCart extends AppCompatActivity {
    RecyclerView recyclerView;

    private CartAdapter adapter;
    private ArrayList<FishModel> cartItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        recyclerView = findViewById(R.id.recyclerViewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartItemList = new ArrayList<>();
        adapter = new CartAdapter(cartItemList, this);
        recyclerView.setAdapter(adapter);

        // Show progress dialog while data is being loaded
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progressbar_food_type);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        // Retrieve the cart data from Firebase Realtime Database
        String userId = FirebaseAuth.getInstance().getUid();
        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("MyOrders").child(userId);
        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartItemList.clear();
                for (DataSnapshot cartSnapshot : dataSnapshot.getChildren()) {
                    String image = cartSnapshot.child("image").getValue(String.class);
                    String name = cartSnapshot.child("name").getValue(String.class);
                    String price = cartSnapshot.child("price").getValue(String.class);

                    FishModel cartItem = new FishModel(userId,image, name, price);
                    cartItemList.add(cartItem);
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur during data retrieval
                progressDialog.dismiss();
            }
        });
    }
}
