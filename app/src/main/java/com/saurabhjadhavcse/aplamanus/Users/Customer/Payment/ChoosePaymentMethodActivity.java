package com.saurabhjadhavcse.aplamanus.Users.Customer.Payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.saurabhjadhavcse.aplamanus.R;

public class ChoosePaymentMethodActivity extends AppCompatActivity {
    private Button cashOnDeliveryButton;
    private Button onlinePaymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_payment_method);

        cashOnDeliveryButton = findViewById(R.id.cashOnDeliveryButton);
        onlinePaymentButton = findViewById(R.id.onlinePaymentButton);

        cashOnDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Cash on Delivery selection
                // Start the next activity or process the order accordingly
            }
        });

        onlinePaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Online Payment selection
                // Redirect to the online payment gateway or start the payment process
            }
        });
    }
}
