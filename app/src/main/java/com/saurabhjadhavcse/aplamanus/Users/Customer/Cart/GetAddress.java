package com.saurabhjadhavcse.aplamanus.Users.Customer.Cart;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Models.CustomerModel;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Notification.FCMSend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GetAddress extends AppCompatActivity {

    private TextInputLayout Name, Email, PhoneNo, editTextAddress, orderName, orderPrice, orderQuantity;
    private Button submit;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    String userId;
    FirebaseUser firebaseUser;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("UserOrders");

    DatabaseReference tokensRef = firebaseDatabase.getReference("tokens");

    String orderId = FirebaseDatabase.getInstance().getReference("UserOrders").push().getKey();


    private String retrieveToken;

    String USERID = FirebaseAuth.getInstance().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address);

        Name = findViewById(R.id.ProfileName);
        Email = findViewById(R.id.ProfileEmail);
        PhoneNo = findViewById(R.id.ProfilePhoneN0);
        orderName = findViewById(R.id.orderName);
        orderPrice = findViewById(R.id.orderPrice);
        orderQuantity = findViewById(R.id.OrderQuantity);
        submit = findViewById(R.id.buttonSubmit);

        editTextAddress = findViewById(R.id.editTextAddressCustomer);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        userId = firebaseAuth.getCurrentUser().getUid();
        firebaseUser = firebaseAuth.getCurrentUser();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String currentTime = timeFormat.format(new Date());


        DocumentReference documentReference = firebaseFirestore.collection("Users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                Name.getEditText().setText(documentSnapshot.getString("FullName"));
                Email.getEditText().setText(documentSnapshot.getString("UserEmail"));
                PhoneNo.getEditText().setText(documentSnapshot.getString("PhoneNumber"));
                String address = documentSnapshot.getString("Address");
                editTextAddress.getEditText().setText(address);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        Intent intent = getIntent();

        String foodTitlePass = intent.getStringExtra("foodTitle");
        String foodPricePass = intent.getStringExtra("result");
        String foodQuantity = intent.getStringExtra("quantity");
        String imageUrlPass = intent.getStringExtra("imageUrl");
        String tempPrice = intent.getStringExtra("tempPrice");

        orderName.getEditText().setText(foodTitlePass);
        orderPrice.getEditText().setText(foodPricePass);
        orderQuantity.getEditText().setText(foodQuantity + "Grams");

        tokensRef.child("AdminToken").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String token = dataSnapshot.getValue(String.class);
                    retrieveToken = token;
                } else {
                    Toast.makeText(GetAddress.this, "Error Token", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error case
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextAddress.getEditText().getText().toString().isEmpty()) {
                    editTextAddress.setError("Please Enter The Address");
                } else {

                    String userId = firebaseAuth.getCurrentUser().getUid();

                    CustomerModel user = new CustomerModel();
                    user.setName(Name.getEditText().getText().toString());
                    user.setEmail(Email.getEditText().getText().toString());
                    user.setPhoneNo(PhoneNo.getEditText().getText().toString());
                    user.setAddress(editTextAddress.getEditText().getText().toString());
                    user.setOrderName(orderName.getEditText().getText().toString());
                    user.setOrderPrice(orderPrice.getEditText().getText().toString());
                    user.setOrderPrice(orderPrice.getEditText().getText().toString());
                    user.setOrderQuantity(orderQuantity.getEditText().getText().toString());
                    user.setTempPrice(tempPrice);
                    user.setOrderImageUrl(imageUrlPass);
                    user.setOrderDate(currentDate);
                    user.setOrderTime(currentTime);
                    user.setOrderId(orderId);

                    uploadTextToFirebase(tempPrice);

                    // Set the value under the new order ID
                    databaseReference.child(userId).child(orderId).setValue(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Data successfully submitted to the database

                                    FirebaseDatabase.getInstance().getReference("TotalOrders").child(orderId)
                                            .setValue(user, orderId)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(GetAddress.this, "Order Placed", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Failed to submit data to the database
                                    Toast.makeText(GetAddress.this, "Order Failed Please Try Again", Toast.LENGTH_SHORT).show();
                                }
                            });

                    FirebaseDatabase.getInstance().getReference("MyOrders").child(userId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });


                    startActivity(new Intent(GetAddress.this, OrderCompleted.class));


                    // Notification

                    String title = "Order Received";
                    String message = "New Order";

                    FCMSend.pushNotification(
                            GetAddress.this,
                            retrieveToken,
                            title,
                            message
                    );
                }
            }
        });


        // Notification

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    String token = task.getResult();
                    if (token != null) {
                        Log.d("FCMToken", "Token" + token); // Log the FCM token
                    }
                }
            }
        });


    }


    private void uploadTextToFirebase(String text) {
        // Create a new node in the Firebase Realtime Database
        DatabaseReference textRef = FirebaseDatabase.getInstance().getReference("Texts").push();
        textRef.setValue(text)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to upload text
                    }
                });
    }
}