package com.example.loginscreenactivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivitytab extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Connect the TabLayout and the ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:

                        tab.setIcon(R.drawable.ic_home); // Replace with your own icon
                        break;
                    case 1:

                        tab.setIcon(R.drawable.ic_favs); // Replace with your own icon
                        break;
                    case 2:

                        tab.setIcon(R.drawable.ic_notification); // Replace with your own icon
                        break;
                    case 3:

                        tab.setIcon(R.drawable.ic_profile); // Replace with your own icon
                        break;
                }
            }
        }).attach();
    }
}
