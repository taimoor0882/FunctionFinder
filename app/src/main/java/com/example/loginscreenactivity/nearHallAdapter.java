package com.example.loginscreenactivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class nearHallAdapter extends RecyclerView.Adapter<nearHallAdapter.HallViewHolder> implements Filterable {

    private Context context;
    private List<nearHall> hallsList;
    private List<nearHall> hallsListFull;

    public nearHallAdapter(Context context, List<nearHall> hallsList) {
        this.context = context;
        this.hallsList = hallsList;
        this.hallsListFull = new ArrayList<>(hallsList);
    }

    @NonNull
    @Override
    public HallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hall_item, parent, false);
        return new HallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HallViewHolder holder, int position) {
        nearHall hall = hallsList.get(position);
        holder.tvCategoryName.setText(hall.getHallName());
        holder.tvDetails.setText("Details:");
        holder.details.setText(hall.getDetails());
        holder.location.setText(hall.getLocation());
        holder.guests.setText(hall.getGuests());
        holder.rating.setText(hall.getRating());
        holder.ivCategoryPic.setImageResource(hall.getImageResId());
        holder.ivLocation.setImageResource(hall.getLocationIconResId());
        holder.ivGuests.setImageResource(hall.getGuestsIconResId());
        holder.ivFeedback.setImageResource(hall.getFeedbackIconResId());
        holder.ivForward.setImageResource(hall.getForwardIconResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, hall_details.class);
            intent.putExtra("hallName", hall.getHallName());
            intent.putExtra("details", hall.getDetails());
            intent.putExtra("location", hall.getLocation());
            intent.putExtra("guests", hall.getGuests());
            intent.putExtra("rating", hall.getRating());
            intent.putExtra("imageResId", hall.getImageResId());
            intent.putExtra("locationIconResId", hall.getLocationIconResId());
            intent.putExtra("guestsIconResId", hall.getGuestsIconResId());
            intent.putExtra("feedbackIconResId", hall.getFeedbackIconResId());
            intent.putExtra("forwardIconResId", hall.getForwardIconResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hallsList.size();
    }

    @Override
    public Filter getFilter() {
        return hallFilter;
    }

    private Filter hallFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<nearHall> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(hallsListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (nearHall item : hallsListFull) {
                    if (item.getHallName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            hallsList.clear();
            hallsList.addAll((List<nearHall>) results.values);
            notifyDataSetChanged();
        }
    };

    public static class HallViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCategoryPic, ivLocation, ivGuests, ivFeedback, ivForward;
        TextView tvCategoryName, tvDetails, details, location, guests, rating;

        public HallViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategoryPic = itemView.findViewById(R.id.ivCategoryPic);
            ivLocation = itemView.findViewById(R.id.ivlocation);
            ivGuests = itemView.findViewById(R.id.ivppl);
            ivFeedback = itemView.findViewById(R.id.ivfeedback);
            ivForward = itemView.findViewById(R.id.ivforward);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvDetails = itemView.findViewById(R.id.tvdetails);
            details = itemView.findViewById(R.id.details);
            location = itemView.findViewById(R.id.tvloc);
            guests = itemView.findViewById(R.id.guests);
            rating = itemView.findViewById(R.id.ratings);
        }
    }
}