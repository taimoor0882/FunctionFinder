package com.example.loginscreenactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class completion_page extends AppCompatActivity {

    private RecyclerView recyclerView;
    private nearHallAdapter adapter;
    public List<nearHall> hallsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion_page);

        ImageView backButton = findViewById(R.id.backButton);
        Button homeButton = findViewById(R.id.button2);

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(completion_page.this, checkout.class);
            startActivity(intent);
        });

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(completion_page.this, MainActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.recyclerView_thanks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and populate the hall list
        hallsList = new ArrayList<>();
        nearHall hall1 = new nearHall("Hall 1", "Details for Hall 1", "Location 1", "50 guests", "4.5", R.drawable.image3, R.drawable.location, R.drawable.people, R.drawable.feed, R.drawable.forward);
        nearHall hall2 = new nearHall("Hall 2", "Details for Hall 2", "Location 2", "100 guests", "4.8", R.drawable.image4, R.drawable.location, R.drawable.people, R.drawable.feed, R.drawable.forward);

// Adding hall1 and hall2 to your hallsList
        hallsList.add(hall1);
        hallsList.add(hall2);

        adapter = new nearHallAdapter(this, hallsList);
        recyclerView.setAdapter(adapter);
    }
}