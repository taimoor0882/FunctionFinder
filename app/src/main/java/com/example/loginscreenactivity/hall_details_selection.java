package com.example.loginscreenactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hall_details_selection extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference bookingsRef;

    private EditText editTextEventType, editTextCapacity;
    private Spinner spinnerFoodPackages;
    private ImageView btnPackages;

    private CardView card_package;

    private Button btnSubmit;
    String eventType;
    int capacity;
    String foodPackage;
    private String[] foodPackages = {"Package 1", "Package 2", "Package 3", "Package 4", "Package 5"};
    private ArrayAdapter<String> foodPackageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_details_selection);

        // Initialize Firebase
        database = FirebaseDatabase.getInstance();
        bookingsRef = database.getReference("bookings");

        // Initialize UI elements
        editTextEventType = findViewById(R.id.editTextEventType);
        editTextCapacity = findViewById(R.id.editcapacity);
        spinnerFoodPackages = findViewById(R.id.spinnerFoodPackages);
        card_package = findViewById(R.id.cardViewFoodPackages);
        btnSubmit = findViewById(R.id.button);
        btnPackages = card_package.findViewById(R.id.btnPackages);

        foodPackageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foodPackages);
        foodPackageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFoodPackages.setAdapter(foodPackageAdapter);

        // Set up the Submit button click listener
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBooking();
            }
        });

        btnPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(hall_details_selection.this, food_packages.class);
                startActivity(i); // Don't forget to start the activity
            }
        });
    }

    private void saveBooking() {
        eventType = editTextEventType.getText().toString();
        capacity = Integer.parseInt(editTextCapacity.getText().toString());
        foodPackage = spinnerFoodPackages.getSelectedItem().toString();

        // Create a new booking object
        Booking booking = new Booking(eventType, capacity, foodPackage);

        // Save the booking to Firebase
        bookingsRef.push().setValue(booking)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Booking saved successfully
                        Toast.makeText(hall_details_selection.this, "Booking saved", Toast.LENGTH_SHORT).show();

                        // Start the checkout activity with the passed data
                        Intent i = new Intent(hall_details_selection.this, checkout.class);
                        i.putExtra("eventtype", eventType);
                        i.putExtra("capacity", capacity);
                        i.putExtra("foodPackage", foodPackage);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to save booking
                        Toast.makeText(hall_details_selection.this, "Failed to save booking", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}