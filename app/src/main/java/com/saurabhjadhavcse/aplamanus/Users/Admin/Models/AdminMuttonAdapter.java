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


public class AdminMuttonAdapter extends RecyclerView.Adapter<AdminMuttonAdapter.MyViewHolder> {

    private final ArrayList<MuttonModel> mMuttonlist;

    private final Context context;

    public AdminMuttonAdapter(ArrayList<MuttonModel> mFishList, Context context) {
        this.mMuttonlist = mFishList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_loader_admin, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(mMuttonlist.get(position).getMuttonImageUrl()).into(holder.fishImageHolder);

        MuttonModel muttonModel = mMuttonlist.get(position);
        holder.fishItemName.setText(muttonModel.getMuttonTextUrl());
        holder.fishItemPrice.setText(muttonModel.getMuttonPriceUrl() + " / KG");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemId = muttonModel.getModelId();

                // Delete the item from Firebase
                DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference("Mutton").child(itemId);
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
        return mMuttonlist.size();
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
