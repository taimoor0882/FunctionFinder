package com.example.loginscreenactivity;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class checkout extends AppCompatActivity {

    private ImageView back;
    private TextView heading, hallName, location, price, timeSeparator;
    private EditText startTime, endTime;
    private Button bookButton;
    private DatabaseReference databaseReference;
    private Calendar calendar;
    String hall;
    String loc;
    String cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("halls");

        // Initialize UI elements
        back = findViewById(R.id.back);
        hallName = findViewById(R.id.hallName);
        location = findViewById(R.id.tvLoc);
        price = findViewById(R.id.tvprice);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        timeSeparator = findViewById(R.id.textView13);
        bookButton = findViewById(R.id.book);

        calendar = Calendar.getInstance();

        // Set OnClickListener for start time
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerDialog(startTime);
            }
        });

        // Set OnClickListener for end time
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerDialog(endTime);
            }
        });

        // Set OnClickListener for book button
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookHall();
                Intent inten=new Intent(checkout.this,completion_page.class);
                startActivity(inten);
                inten.putExtra("hall", (CharSequence) hallName);

            }


        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(checkout.this,completion_page.class);
                startActivity(i);

            }
        });
    }

    private void showDateTimePickerDialog(final EditText timeField) {
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        new DatePickerDialog(checkout.this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(checkout.this, (view1, hourOfDay, minute) -> {
                    date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    date.set(Calendar.MINUTE, minute);
                    timeField.setText(String.format("%02d-%02d-%d %02d:%02d", dayOfMonth, monthOfYear + 1, year, hourOfDay, minute));
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    private void bookHall() {
        String hall = hallName.getText().toString();
        String loc = location.getText().toString();
        String cost = price.getText().toString();
        String start = startTime.getText().toString();
        String end = endTime.getText().toString();

        if (hall.isEmpty() || loc.isEmpty() || cost.isEmpty() || start.isEmpty() || end.isEmpty()) {
            Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = getIntent();
        String eventType = intent.getStringExtra("eventtype");
        int capacity = intent.getIntExtra("capacity", 0); // Default value is 0 if not found
        String foodPackage = intent.getStringExtra("foodPackage");
        Map<String, Object> hallBooking = new HashMap<>();
        hallBooking.put("hallName", hall);
        hallBooking.put("location", loc);
        hallBooking.put("price", cost);
        hallBooking.put("startTime", start);
        hallBooking.put("endTime", end);
        hallBooking.put("eventtype",eventType);
        hallBooking.put("capacity",capacity);
        hallBooking.put("foodPackage",foodPackage);

        databaseReference.push().setValue(hallBooking)
                .addOnSuccessListener(aVoid -> Toast.makeText(checkout.this, "Hall booked successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(checkout.this, "Failed to book hall", Toast.LENGTH_SHORT).show());
    }

}