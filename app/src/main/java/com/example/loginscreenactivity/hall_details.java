package com.example.loginscreenactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.loginscreenactivity.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class hall_details extends AppCompatActivity implements OnMapReadyCallback {
    ImageSlider mainslider;
    private GoogleMap mMap;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mainslider = findViewById(R.id.image_slider);
        linearLayout=findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(hall_details.this,hall_details_selection.class);
                startActivity(i);
            }
        });
        final List<SlideModel> localImages = new ArrayList<>();

        // Add sample images to the slider
        localImages.add(new SlideModel(R.drawable.hall1, "Image 2", ScaleTypes.FIT));
        localImages.add(new SlideModel(R.drawable.hall2, "Image 3", ScaleTypes.FIT));

        mainslider.setImageList(localImages, ScaleTypes.FIT);

        mainslider.setItemClickListener(new ItemClickListener() {
            @Override
            public void doubleClick(int i) {

            }

            @Override
            public void onItemSelected(int position) {
                Toast.makeText(getApplicationContext(), localImages.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker at the specified location and move the camera
        LatLng location = new LatLng(31.540508031218028, 74.39547535288482);
        mMap.addMarker(new MarkerOptions().position(location).title("Marker at Majesty"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
    }

    public void onEditClick(View view) {

    }
}