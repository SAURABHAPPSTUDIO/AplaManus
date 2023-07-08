package com.saurabhjadhavcse.aplamanus.Users.Admin.Orders;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saurabhjadhavcse.aplamanus.R;
import com.squareup.picasso.Picasso;

public class SeeOrders extends AppCompatActivity {

    private TextInputLayout Name, Email, PhoneNo, editTextAddress;

    ImageView imageView;

    TextView getFoodName, getFoodPrice, getQuantity, getTotalPrice;

    Button call, track, status, delete;

    String getLatitude, getLongitude, getAddress, getPhoneNumber, orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_orders);

        Name = findViewById(R.id.ProfileName);
        PhoneNo = findViewById(R.id.ProfilePhoneN0);
        call = findViewById(R.id.Call);

        editTextAddress = findViewById(R.id.editTextAddressAdmin);

        imageView = findViewById(R.id.FoodImageMyOrders);
        delete = findViewById(R.id.Delete);

        getFoodName = findViewById(R.id.FoodNameMyOrders);
        getFoodPrice = findViewById(R.id.FoodPriceMyOrders);
        getQuantity = findViewById(R.id.FoodOrderQuantity);
        getTotalPrice = findViewById(R.id.FoodTotalPrice);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String foodName = extras.getString("foodName");
            String foodPrice = extras.getString("foodPrice");
            String quantity = extras.getString("quantity");
            String totalPrice = extras.getString("totalPrice");
            String fullName = extras.getString("fullName");
            String phoneNo = extras.getString("phoneNo");
            String address = extras.getString("address");
            String image = extras.getString("image");
            String latitude = extras.getString("latitude");
            String longitude = extras.getString("longitude");
            orderId = extras.getString("orderId");

            // Set the data to the TextInputLayouts and TextViews
            Name.getEditText().setText(fullName);
            PhoneNo.getEditText().setText(phoneNo);
            getFoodName.setText(foodName);
            getFoodPrice.setText(foodPrice);
            getQuantity.setText(quantity);
            getTotalPrice.setText(totalPrice);

            editTextAddress.getEditText().setText(address);

            getLatitude = latitude;
            getLongitude = longitude;
            getAddress = address;
            getPhoneNumber = phoneNo;

            Picasso.get().load(image).into(imageView);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initiate a phone call to the customer
                Uri phoneUri = Uri.parse("tel:" + getPhoneNumber);
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, phoneUri);
                startActivity(dialIntent);
            }
        });

    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to delete this order?");

        // Set positive button and its click listener
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Perform deletion here
                deleteOrder();
            }
        });

        // Set negative button and its click listener
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteOrder() {

        Log.d("OrderID", orderId);
        FirebaseDatabase.getInstance().getReference("TotalOrders").child(orderId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(SeeOrders.this, "Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SeeOrders.this, TotalOrders.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        Toast.makeText(this, "Order deleted successfully", Toast.LENGTH_SHORT).show();
    }
}