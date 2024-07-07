package com.example.loginscreenactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class main_hall1 extends AppCompatActivity {

    private ListAdapter listAdapter;
    private hallsAdapter hallsAdapter;
    private List<nearHall> hallList;
    private List<nearHall> filteredHallList;
    private List<halls> hallsRecyclerViewList;
    private ConstraintLayout card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hall1);

        // Initialize hall lists
        hallList = new ArrayList<>();
        filteredHallList = new ArrayList<>();
        hallsRecyclerViewList = new ArrayList<>();

        // Populate hall lists with sample data
        populateHallList();
        populateHallsRecyclerViewList();

        // Setup ListView with ListAdapter
        ListView listView = findViewById(R.id.list);
        listAdapter = new ListAdapter(this, new ArrayList<>(filteredHallList));
        listView.setAdapter(listAdapter);

        // Setup RecyclerView with hallsAdapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        hallsAdapter = new hallsAdapter(this, hallsRecyclerViewList);
        recyclerView.setAdapter(hallsAdapter);

        card=findViewById(R.id.card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(main_hall1.this,hall_details.class);
            }
        });
        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterHalls(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterHalls(newText);
                return true;
            }
        });
    }

    private void populateHallList() {
        // Add sample data to hallList
        hallList.add(new nearHall("Birthday Decor Outdoor", "Lovely setup", "Cantt", "50-100 guests", "4.9 rating", R.drawable.image4, R.drawable.location, R.drawable.people, R.drawable.feed, R.drawable.forward));
        hallList.add(new nearHall("Wedding Hall", "Elegant ambiance", "Gulberg", "200-300 guests", "4.8 rating", R.drawable.image3, R.drawable.location, R.drawable.people, R.drawable.feed, R.drawable.forward));
        hallList.add(new nearHall("Conference Hall", "Professional setup", "DHA", "100-150 guests", "4.7 rating", R.drawable.image4, R.drawable.location, R.drawable.people, R.drawable.feed, R.drawable.forward));

        // Initially, filtered list should display all halls
        filteredHallList.addAll(hallList);
    }

    private void populateHallsRecyclerViewList() {
        // Add sample data to hallsRecyclerViewList
        hallsRecyclerViewList.add(new halls("Birthday Decor Outdoor",   R.drawable.hall1));
        hallsRecyclerViewList.add(new halls("Wedding Hall",   R.drawable.hall2));
        hallsRecyclerViewList.add(new halls("Conference Hall",   R.drawable.hall3));
    }

    private void filterHalls(String query) {
        filteredHallList.clear();
        for (nearHall hall : hallList) {
            if (hall.getHallName().toLowerCase().contains(query.toLowerCase())) {
                filteredHallList.add(hall);
            }
        }
        listAdapter.clear();
        listAdapter.addAll(filteredHallList);
        listAdapter.notifyDataSetChanged();
    }
}