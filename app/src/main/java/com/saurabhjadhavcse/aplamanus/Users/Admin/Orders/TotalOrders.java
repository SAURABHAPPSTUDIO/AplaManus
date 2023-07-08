package com.saurabhjadhavcse.aplamanus.Users.Admin.Orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.TotalOrdersAdapter;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Models.CustomerModel;

import java.util.ArrayList;
import java.util.List;

public class TotalOrders extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TotalOrdersAdapter totalOrdersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_orders);

        recyclerView = findViewById(R.id.recyclerTotalOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference totalOrdersRef = FirebaseDatabase.getInstance().getReference("TotalOrders");
        totalOrdersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<CustomerModel> orderList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CustomerModel order = snapshot.getValue(CustomerModel.class);
                    orderList.add(order);
                }
                totalOrdersAdapter = new TotalOrdersAdapter(orderList);
                recyclerView.setAdapter(totalOrdersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });



    }
}