package com.example.loginscreenactivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class HallDetailsActivity extends AppCompatActivity {

    private ImageView hallImage;
    private EditText hallNameInput, hallLocationInput, hallCapacityInput, hallDescriptionInput, hallFeaturesInput;
    private MaterialButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hall);

        hallImage = findViewById(R.id.hallImage);
        hallNameInput = findViewById(R.id.hallNameInput);
        hallLocationInput = findViewById(R.id.hallLocationInput);
        hallCapacityInput = findViewById(R.id.hallCapacityInput);
        hallDescriptionInput = findViewById(R.id.hallDescriptionInput);
        hallFeaturesInput = findViewById(R.id.hallFeaturesInput);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {
            String hallName = hallNameInput.getText().toString().trim();
            String hallLocation = hallLocationInput.getText().toString().trim();
            String hallCapacity = hallCapacityInput.getText().toString().trim();
            String hallDescription = hallDescriptionInput.getText().toString().trim();
            String hallFeatures = hallFeaturesInput.getText().toString().trim();

            if (hallName.isEmpty() || hallLocation.isEmpty() || hallCapacity.isEmpty() ||
                    hallDescription.isEmpty() || hallFeatures.isEmpty()) {
                Toast.makeText(HallDetailsActivity.this, "Please fill in all details", Toast.LENGTH_SHORT).show();
            } else {
                // Save hall details or upload to server
                Toast.makeText(HallDetailsActivity.this, "Hall details submitted", Toast.LENGTH_SHORT).show();
                // Handle data saving or sending logic here
            }
        });

        // Handle image click to choose an image (optional)
        hallImage.setOnClickListener(v -> {
            // Add code to pick an image from gallery or capture from camera
            // This can be done using an Intent for the gallery or a Camera API
        });
    }
}
