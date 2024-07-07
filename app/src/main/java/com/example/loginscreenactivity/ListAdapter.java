package com.example.loginscreenactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<nearHall> {

    Context context;

    public ListAdapter(@NonNull Context context, @NonNull ArrayList<nearHall> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.hall_item, parent, false);
        }

        TextView tvName = v.findViewById(R.id.tvCategoryName);
        TextView tvDetails = v.findViewById(R.id.tvdetails);
        TextView tvLocation = v.findViewById(R.id.tvloc);
        TextView tvGuests = v.findViewById(R.id.guests);
        TextView tvRatings = v.findViewById(R.id.ratings);
        ImageView ivCategoryPic = v.findViewById(R.id.ivCategoryPic);
        ImageView ivLocation = v.findViewById(R.id.ivlocation);
        ImageView ivPpl = v.findViewById(R.id.ivppl);
        ImageView ivFeedback = v.findViewById(R.id.ivfeedback);
        ImageView ivForward = v.findViewById(R.id.ivforward);

        nearHall hall = getItem(position);

        if (hall != null) {
            tvName.setText(hall.getHallName());
            tvDetails.setText(hall.getDetails());
            tvLocation.setText(hall.getLocation());
            tvGuests.setText(hall.getGuests());
            tvRatings.setText(hall.getRating());
            ivCategoryPic.setImageResource(hall.getImageResId());
            ivLocation.setImageResource(hall.getLocationIconResId());
            ivPpl.setImageResource(hall.getGuestsIconResId());
            ivFeedback.setImageResource(hall.getFeedbackIconResId());
            ivForward.setImageResource(hall.getForwardIconResId());
        }

        return v;
    }
}
