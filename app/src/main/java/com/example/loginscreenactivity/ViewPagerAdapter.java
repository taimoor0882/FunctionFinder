package com.example.loginscreenactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new fragment_home() ;
            case 1:
                return new fragment_favs();
            case 2:
                return new fragment_notification();
            case 3:
                return new fragment_profile();
            default:
                return new fragment_home();
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Number of tabs
    }
}
