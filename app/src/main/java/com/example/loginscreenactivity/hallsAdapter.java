package com.example.loginscreenactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class hallsAdapter extends RecyclerView.Adapter<hallsAdapter.PackageViewHolder> implements Filterable {

    private Context context;
    private List<halls> hallsList;
    private List<halls> hallsListFull;

    public hallsAdapter(Context context, List<halls> hallsList) {
        this.context = context;
        this.hallsList = hallsList;
        this.hallsListFull = new ArrayList<>(hallsList);
    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.halls, parent, false);
        return new PackageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewHolder holder, int position) {
        halls hallItem = hallsList.get(position);
        Glide.with(context).load(hallItem.getImageUrl()).into(holder.imageMessageSent);
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
            List<halls> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(hallsListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase(Locale.getDefault()).trim();
                for (halls item : hallsListFull) {
                    if (item.getHallName().toLowerCase(Locale.getDefault()).contains(filterPattern)) {
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
            hallsList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class PackageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMessageSent;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMessageSent = itemView.findViewById(R.id.imageMessageSent);
        }
    }
}
