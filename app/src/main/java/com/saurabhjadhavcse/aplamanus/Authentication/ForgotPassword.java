package com.saurabhjadhavcse.aplamanus.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.saurabhjadhavcse.aplamanus.R;

public class ForgotPassword extends AppCompatActivity {

    TextInputLayout ForgotPassword;
    Button PasswordResetBtn;
    TextView BackToLogin;

    // FIREBASE
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ForgotPassword = findViewById(R.id.forgetPassword);
        PasswordResetBtn = findViewById(R.id.passwordRecoverButton);
        BackToLogin = findViewById(R.id.goBackToLogin);

        //FIREBASE

        firebaseAuth = FirebaseAuth.getInstance();

        // ONCLICK LISTENERS

        BackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        PasswordResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = ForgotPassword.getEditText().getText().toString().trim();
                if (mail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Your Mail", Toast.LENGTH_SHORT).show();
                } else {

                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Mail Sent To Recover Mail", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPassword.this, Login.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Mail Is Wrong Or Account Don't Exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
