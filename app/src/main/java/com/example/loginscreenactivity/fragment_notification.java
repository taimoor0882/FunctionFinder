package com.example.loginscreenactivity;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment_notification extends Fragment {

    private TextView tvHallName;
    private TextView tvStartDate;
    private TextView tvEndTime;

    public fragment_notification() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        // Initialize TextViews
        tvHallName = view.findViewById(R.id.tvHallName);
        tvStartDate = view.findViewById(R.id.tvStartDate);
        tvEndTime = view.findViewById(R.id.tvEndTime);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch booking details from Firebase
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference bookingRef = FirebaseDatabase.getInstance().getReference("booking").child(userId);

            bookingRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String hallName = dataSnapshot.child("hallName").getValue(String.class);
                        String startTime = dataSnapshot.child("startTime").getValue(String.class);
                        String endTime = dataSnapshot.child("endTime").getValue(String.class);

                        // Update TextViews with fetched details
                        if (hallName != null) {
                            tvHallName.setText(hallName);
                        }
                        if (startTime != null) {
                            tvStartDate.setText("Start Time: " + startTime);
                        }
                        if (endTime != null) {
                            tvEndTime.setText("End Time: " + endTime);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error
                }
            });
        }
    }
}