package com.saurabhjadhavcse.aplamanus.Users.Admin.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saurabhjadhavcse.aplamanus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminVegetableAdapter extends RecyclerView.Adapter<AdminVegetableAdapter.MyViewHolder> {

    private final ArrayList<VegetableModel> mVegetableList;

    private final Context context;

    public AdminVegetableAdapter(ArrayList<VegetableModel> mVegetableList, Context context) {
        this.mVegetableList = mVegetableList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminVegetableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_loader_admin, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminVegetableAdapter.MyViewHolder holder, int position) {
        Picasso.get().load(mVegetableList.get(position).getVegetableUrl()).into(holder.fishImageHolder);

        VegetableModel vegetableModel = mVegetableList.get(position);
        holder.fishItemName.setText(vegetableModel.getVegetableTextUrl());
        holder.fishItemPrice.setText(vegetableModel.getVegetablePriceUrl() + " / KG");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemId = vegetableModel.getModelId();

                // Delete the item from Firebase
                DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference("Vegetable").child(itemId);
                itemRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Failed to delete item", Toast.LENGTH_SHORT).show();
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
        Button delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fishImageHolder = itemView.findViewById(R.id.FishImageHolder);
            fishItemName = itemView.findViewById(R.id.FishItemName);
            fishItemPrice = itemView.findViewById(R.id.FishItemPrice);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
