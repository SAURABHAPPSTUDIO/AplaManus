package com.saurabhjadhavcse.aplamanus.Users.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.saurabhjadhavcse.aplamanus.MainActivity;
import com.saurabhjadhavcse.aplamanus.R;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    TextInputLayout fullName, email, phoneNo, password, editTextAddress;
    Button saveBtn,logout;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullName = findViewById(R.id.profileFullName);
        email = findViewById(R.id.profileEmail);
        phoneNo = findViewById(R.id.profilePhoneNo);
        password = findViewById(R.id.profilePassword);
        saveBtn = findViewById(R.id.profileSaveBtn);
        logout = findViewById(R.id.logout);

        editTextAddress = findViewById(R.id.editTextAddress);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        final FirebaseUser user = fAuth.getCurrentUser();
        final String userId = user.getUid();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Profile.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                finish();
            }
        });

        DocumentReference documentReference = fStore.collection("Users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String fullNameText = documentSnapshot.getString("FullName");
                    String emailText = documentSnapshot.getString("UserEmail");
                    String phoneNoText = documentSnapshot.getString("PhoneNumber");
                    String passwordText = documentSnapshot.getString("Password");
                    String address = documentSnapshot.getString("Address");

                    fullName.getEditText().setText(fullNameText);
                    email.getEditText().setText(emailText);
                    phoneNo.getEditText().setText(phoneNoText);
                    password.getEditText().setText(passwordText);
                    editTextAddress.getEditText().setText(address);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Profile.this, "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedFullName = fullName.getEditText().getText().toString().trim();
                String updatedEmail = email.getEditText().getText().toString().trim();
                String updatedPhoneNo = phoneNo.getEditText().getText().toString().trim();
                String updatedPassword = password.getEditText().getText().toString().trim();
                String address = editTextAddress.getEditText().getText().toString().trim();

                Map<String, Object> updatedUserInfo = new HashMap<>();
                updatedUserInfo.put("FullName", updatedFullName);
                updatedUserInfo.put("UserEmail", updatedEmail);
                updatedUserInfo.put("PhoneNumber", updatedPhoneNo);
                updatedUserInfo.put("Password", updatedPassword);
                updatedUserInfo.put("Address", address);

                DocumentReference documentReference = fStore.collection("Users").document(userId);
                documentReference.update(updatedUserInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Profile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Profile.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}