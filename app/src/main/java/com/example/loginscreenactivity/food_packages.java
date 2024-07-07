package com.example.loginscreenactivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class food_packages extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PackageAdapter packageAdapter;
    private List<Package> packageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_packages);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize packageList with your data
        packageList = new ArrayList<>();
        packageList.add(new Package(R.drawable.menu, "Package 1"));
        packageList.add(new Package(R.drawable.menu, "Package 2"));

        packageAdapter = new PackageAdapter(this, packageList);
        recyclerView.setAdapter(packageAdapter);
    }
}