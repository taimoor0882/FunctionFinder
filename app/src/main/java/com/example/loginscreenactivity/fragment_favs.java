package com.example.loginscreenactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class fragment_favs extends Fragment {

    private RecyclerView recyclerView;
    private nearHallAdapter adapter;
    private List<nearHall> hallsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favs, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        hallsList = new ArrayList<>();
        // Add sample data to the list
        hallsList.add(new nearHall("Birthday Decor Outdoor", "Details about Birthday Decor Outdoor", "Cantt", "50-100 guests", "4.9 rating", R.drawable.mask, R.drawable.location, R.drawable.people, R.drawable.feed, R.drawable.forward));
        // Add more sample data as needed

        adapter = new nearHallAdapter(getContext(), hallsList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
