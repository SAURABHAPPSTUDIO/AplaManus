package com.saurabhjadhavcse.aplamanus.Users.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.saurabhjadhavcse.aplamanus.R;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Cart.MyCart;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Cart.TrackOrders;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Chicken.ChickenSee;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Fish.FishSee;
import com.saurabhjadhavcse.aplamanus.Users.Customer.Mutton.MuttonSee;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


public class CustomerDashboard extends AppCompatActivity {

    LinearLayout seeFish, seeChicken, seeMutton, seeMyOrders, trackOrders, seeProfile;
    SliderView sliderView;
    int[] images = {R.drawable.slider_image1,
            R.drawable.slider_image5,
            R.drawable.slider_image2,
            R.drawable.slider_image3,
            R.drawable.slider_image4,
            R.drawable.slider_image5};

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        sliderView = findViewById(R.id.image_slider);
        seeFish = findViewById(R.id.SeeFish);
        seeChicken = findViewById(R.id.SeeChicken);
        seeMutton = findViewById(R.id.SeeMutton);
        seeMyOrders = findViewById(R.id.MyOrders);
        trackOrders = findViewById(R.id.TrackOrders);
        seeProfile = findViewById(R.id.Profile);

        mAdView = findViewById(R.id.adView);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

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


        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        seeFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDashboard.this, FishSee.class));
            }
        });


        seeChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDashboard.this, ChickenSee.class));
            }
        });

        seeMutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDashboard.this, MuttonSee.class));
            }
        });

        seeMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDashboard.this, MyCart.class));
            }
        });

        trackOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDashboard.this, TrackOrders.class));
            }
        });

        seeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDashboard.this, Profile.class));
            }
        });

    }
}