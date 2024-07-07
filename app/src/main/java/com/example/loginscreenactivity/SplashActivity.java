package com.example.loginscreenactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SplashActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView splashImage = findViewById(R.id.splashImage);
        TextView splashText = findViewById(R.id.splashText);
        FloatingActionButton skipButton = findViewById(R.id.skipButton);

        // Set the splash image and text
        splashImage.setImageResource(R.drawable.sample_image);
        splashText.setText("Effortless Marriage Hall Booking");

        // Set up animation for the splash image
        AnimationSet animationSet = new AnimationSet(true);

        // Translate animation (move from top to center)
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, -1f, // Start from above the screen
                Animation.RELATIVE_TO_PARENT, 0f   // Move to its original position
        );
        translateAnimation.setDuration(1000);

        // Fade-in animation
        AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);

        // Combine animations
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(fadeIn);
        splashImage.startAnimation(animationSet);

        // Make text and button visible after the image animation ends
        handler.postDelayed(() -> {
            splashText.setVisibility(View.VISIBLE);
            skipButton.setVisibility(View.VISIBLE);
            splashText.startAnimation(fadeIn); // Optional: add fade-in for text
        }, 1000); // Delay same as animation duration

        // Set up skip button to navigate to MainActivity
        skipButton.setOnClickListener(v -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        });

        // Automatically navigate to MainActivity after 3 seconds
        handler.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
