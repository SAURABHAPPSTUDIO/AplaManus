package com.saurabhjadhavcse.aplamanus.Users.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Delete.DeleteDashboard;
import com.saurabhjadhavcse.aplamanus.Users.Admin.Orders.TotalOrders;

public class AdminDashboard extends AppCompatActivity {

    LinearLayout addItem, seeOrders, deleteAll;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference tokensRef = database.getReference("tokens");

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        addItem = findViewById(R.id.AddItems);
        seeOrders = findViewById(R.id.SeeOrders);
        mAdView = findViewById(R.id.adView2);
        deleteAll = findViewById(R.id.DeleteAll);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError);
                mAdView.loadAd(adRequest);
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    String token = task.getResult();
                    if (token != null) {
                        Log.d("FCMToken", "Token: " + token); // Log the FCM token
                    } else {
                        Toast.makeText(AdminDashboard.this, "Failed to get FCM token", Toast.LENGTH_SHORT).show();
                    }

                    token = task.getResult();

                    if (token != null) {
                        tokensRef.child("AdminToken").setValue(token).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("FCMToken", "Token stored in the database");
                                } else {
                                    Toast.makeText(AdminDashboard.this, "Failed to store FCM token", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(AdminDashboard.this, "Failed to get FCM token", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(AdminDashboard.this, "Failed to get FCM token", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, AddItemDashboard.class));
            }
        });


        seeOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, TotalOrders.class));
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboard.this, DeleteDashboard.class));
            }
        });


    }
}