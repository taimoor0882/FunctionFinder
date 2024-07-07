package com.example.loginscreenactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class edit_profile extends AppCompatActivity {

    private TextView tvProfileText;
    private ImageView ivBackButton, ivProfileImage;
    private EditText etFirstName, etLastName, etEmail;
    private Button btnSaveChanges;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(); // Initialize views and Firebase

        loadUser(); // Load user information from Firebase

        btnSaveChanges.setOnClickListener(v -> {
            String firstName = etFirstName.getText().toString().trim();
            String lastName = etLastName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            if (firstName.isEmpty()) {
                etFirstName.setError("First Name can't be empty");
                return;
            }

            if (lastName.isEmpty()) {
                etLastName.setError("Last Name can't be empty");
                return;
            }

            if (email.isEmpty()) {
                etEmail.setError("Email can't be empty");
                return;
            }

            HashMap<String, Object> userInfo = new HashMap<>();
            userInfo.put("firstName", firstName);
            userInfo.put("lastName", lastName);
            userInfo.put("email", email);

            ProgressDialog dialog = showProgressDialog("Saving your information...");

            reference.child("Users")
                    .child(user.getUid())
                    .updateChildren(userInfo)
                    .addOnSuccessListener(unused -> {
                        dialog.dismiss();
                        Toast.makeText(edit_profile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        dialog.dismiss();
                        Toast.makeText(edit_profile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        ivBackButton.setOnClickListener(v -> {
            // Handle back button click
            finish();
        });
    }

    private void loadUser() {
        reference.child("Users")
                .child(user.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String firstName = Objects.requireNonNull(snapshot.child("firstName").getValue()).toString();
                            String lastName = Objects.requireNonNull(snapshot.child("lastName").getValue()).toString();
                            String email = Objects.requireNonNull(snapshot.child("email").getValue()).toString();

                            etFirstName.setText(firstName);
                            etLastName.setText(lastName);
                            etEmail.setText(email);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(edit_profile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private ProgressDialog showProgressDialog(String message) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(message);
        dialog.show();
        return dialog;
    }

    private void init() {
        setContentView(R.layout.activity_edit_profile);

        tvProfileText = findViewById(R.id.profileText);
        ivBackButton = findViewById(R.id.backButton);
        ivProfileImage = findViewById(R.id.profileImage);
        etFirstName = findViewById(R.id.editTextFirstName);
        etLastName = findViewById(R.id.editTextLastName);
        etEmail = findViewById(R.id.editTextEmail);
        btnSaveChanges = findViewById(R.id.buttonSaveChanges);

        // Initialize Firebase references
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();

        if (user != null) {
            // Set profile image, e.g., from URL or drawable resource
            // ivProfileImage.setImageResource(R.drawable.profile_picture);
        }
    }
}
