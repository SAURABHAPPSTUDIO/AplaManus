package com.saurabhjadhavcse.aplamanus.Users.Customer.Vegetable;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Models.VegetableModel;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Cart.MyCart;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Fish.FishAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class VegetableAdapter extends RecyclerView.Adapter<VegetableAdapter.MyViewHolder> {

    private final ArrayList<VegetableModel> mVegetableList;

    private final Context context;

    public VegetableAdapter(ArrayList<VegetableModel> mVegetableList, Context context) {
        this.mVegetableList = mVegetableList;
        this.context = context;
    }

    @NonNull
    @Override
    public VegetableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fish_item_loader, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VegetableAdapter.MyViewHolder holder, int position) {
        Picasso.get().load(mVegetableList.get(position).getVegetableUrl()).into(holder.fishImageHolder);

        VegetableModel vegetableModel = mVegetableList.get(position);
        holder.fishItemName.setText(vegetableModel.getVegetableTextUrl());
        holder.fishItemPrice.setText(vegetableModel.getVegetablePriceUrl() + " / KG");

        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key = FirebaseDatabase.getInstance().getReference("MyOrders").push().getKey();

                String userId = FirebaseAuth.getInstance().getUid();
                FirebaseDatabase.getInstance().getReference("Users").child(userId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //Hash Map to store the values
                        HashMap orderDetails = new HashMap();

                        orderDetails.put("image",vegetableModel.getVegetableUrl());
                        orderDetails.put("name",vegetableModel.getVegetableTextUrl());
                        orderDetails.put("price",vegetableModel.getVegetablePriceUrl());

                        Toast.makeText(v.getContext(), "Order Added To Cart", Toast.LENGTH_SHORT).show();

                   /*     if (task.isSuccessful()) {
                            FirebaseDatabase.getInstance().getReference("TotalOrders").child(key)
                                    .setValue(orderDetails)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                            }
                                        }
                                    });
                        }

                    */


                        FirebaseDatabase.getInstance().getReference("MyOrders").child(userId).child(key)
                                .setValue(orderDetails)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Intent intent = new Intent(context, MyCart.class);
                                        context.startActivity(intent);
                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return mVegetableList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        public TextView fishItemName, fishItemPrice;
        ImageView fishImageHolder;
        Button order;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fishImageHolder = itemView.findViewById(R.id.FishImageHolder);
            fishItemName = itemView.findViewById(R.id.FishItemName);
            fishItemPrice = itemView.findViewById(R.id.FishItemPrice);
            order = itemView.findViewById(R.id.orderFish);
        }
    }
}