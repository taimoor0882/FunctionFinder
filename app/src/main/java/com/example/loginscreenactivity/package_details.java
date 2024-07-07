package com.example.loginscreenactivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class package_details extends AppCompatActivity {

    private ImageView imageViewPackage;
    private TextView textViewPackageName;
    private CardView cardView;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);

        cardView = findViewById(R.id.cardView);
        constraintLayout = (ConstraintLayout) cardView.getChildAt(0);
        imageViewPackage = constraintLayout.findViewById(R.id.imageViewPackage);
        textViewPackageName = constraintLayout.findViewById(R.id.textViewPackageName);

        // Get data from the intent
        int imageResource = getIntent().getIntExtra("imageResource", R.drawable.menu);
        String packageName = getIntent().getStringExtra("packageName");

        // Set data to views
        imageViewPackage.setImageResource(imageResource);
        textViewPackageName.setText(packageName);
    }
}