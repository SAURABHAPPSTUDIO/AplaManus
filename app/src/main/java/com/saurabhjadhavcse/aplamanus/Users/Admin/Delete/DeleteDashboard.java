package com.saurabhjadhavcse.aplamanus.Users.Admin.Delete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saurabhjadhavcse.aplamanus.R;

public class DeleteDashboard extends AppCompatActivity {

    private Button deleteAllBtnFish, deleteAllBtnMuttton, deleteAllBtnChicken, deleteAllBtnVegetable;
    private DatabaseReference root1, root2, root3, root4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_dashboard);

        deleteAllBtnFish = findViewById(R.id.deleteAllFishItems);
        deleteAllBtnMuttton = findViewById(R.id.deleteAllMuttonItems);
        deleteAllBtnChicken = findViewById(R.id.deleteAllChickenItems);
        deleteAllBtnVegetable = findViewById(R.id.deleteAllVegetableItems);

        root1 = FirebaseDatabase.getInstance().getReference("Fish");
        root2 = FirebaseDatabase.getInstance().getReference("Chicken");
        root3 = FirebaseDatabase.getInstance().getReference("Mutton");
        root4 = FirebaseDatabase.getInstance().getReference("Vegetable");

        deleteAllBtnFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllItems1();
                Toast.makeText(DeleteDashboard.this, "All Fish Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        deleteAllBtnChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllItems2();
                Toast.makeText(DeleteDashboard.this, "All Chicken Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        deleteAllBtnMuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllItems3();
                Toast.makeText(DeleteDashboard.this, "All Mutton Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        deleteAllBtnVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllItems4();
                Toast.makeText(DeleteDashboard.this, "All Vegetables Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteAllItems1() {
        root1.removeValue();
    }

    private void deleteAllItems2() {
        root2.removeValue();
    }

    private void deleteAllItems3() {
        root3.removeValue();
    }

    private void deleteAllItems4() {
        root4.removeValue();
    }
}