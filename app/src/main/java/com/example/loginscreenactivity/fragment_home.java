package com.example.loginscreenactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends Fragment {

    private RecyclerView recyclerView1, recyclerView2;
    private nearHallAdapter nearHallAdapter;
    private hallsAdapter hallsAdapter;
    private SearchView searchView;
    private ConstraintLayout innerConstraintLayout, innerConstraintLayout2;
    private ImageSlider imageSlider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        FirebaseApp.initializeApp(requireContext());

        recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        innerConstraintLayout = view.findViewById(R.id.innerConstraintLayout);
        innerConstraintLayout2 = view.findViewById(R.id.innerConstraintLayout2);
        searchView = view.findViewById(R.id.searchView1);
        imageSlider = view.findViewById(R.id.image_slider);

        // Initialize and set layout manager for RecyclerViews
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<nearHall> nearHallList = new ArrayList<>();
        nearHallList.add(new nearHall("Birthday Decor Outdoor", "Sample details for Birthday Decor Outdoor", "Cantt", "50-100 guests", "4.9 rating", R.drawable.image4, R.drawable.location, R.drawable.people, R.drawable.feed, R.drawable.forward));
        nearHallList.add(new nearHall("Wedding Hall", "Sample details for Wedding Hall", "City Center", "100-200 guests", "4.8 rating", R.drawable.image3, R.drawable.location, R.drawable.people, R.drawable.feed, R.drawable.forward));

        List<halls> hallsList = new ArrayList<>();
        hallsList.add(new halls("hall1", R.drawable.image3));
        hallsList.add(new halls("hall2", R.drawable.image4));

        innerConstraintLayout.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), main_hall1.class);
            startActivity(intent);
        });

        innerConstraintLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), main_hall1.class);
            startActivity(intent);
        });

        nearHallAdapter = new nearHallAdapter(getContext(), nearHallList);
        hallsAdapter = new hallsAdapter(getContext(), hallsList);

        recyclerView1.setAdapter(nearHallAdapter);
        recyclerView2.setAdapter(hallsAdapter);

        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.hall1, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.hall2, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image3, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(imageList);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                nearHallAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }
}
