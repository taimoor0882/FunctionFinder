package com.example.loginscreenactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class fragment_profile extends Fragment {

    public fragment_profile() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Find the CardViews by ID
        CardView editProfileCard = view.findViewById(R.id.editProfileCard);
        CardView bookedAppointmentCard = view.findViewById(R.id.privacyCard);
        CardView logoutCard = view.findViewById(R.id.logoutcard);

        editProfileCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), edit_profile.class);
            startActivity(intent);
        });

        bookedAppointmentCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), completion_page.class);
            startActivity(intent);
        });

        logoutCard.setOnClickListener(v -> {
            // Handle user logout here
            logoutUser();
        });

        return view;
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getActivity(), "User logged out", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        requireActivity().finish();
    }
}
