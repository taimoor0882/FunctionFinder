package com.example.loginscreenactivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageViewHolder> {

    private List<Package> packageList;
    private Context context;

    public PackageAdapter(Context context, List<Package> packageList) {
        this.context = context;
        this.packageList = packageList;
    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_item, parent, false);
        return new PackageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewHolder holder, int position) {
        Package packageModel = packageList.get(position);
        holder.bind(packageModel);
    }

    @Override
    public int getItemCount() {
        return packageList.size();
    }

    public class PackageViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCategoryPic;
        TextView tvPackageNumber;
        CardView cardView;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategoryPic = itemView.findViewById(R.id.ivCategoryPic);
            tvPackageNumber = itemView.findViewById(R.id.tvPackageNumber);
            cardView = itemView.findViewById(R.id.cardView);
        }

        public void bind(final Package packageModel) {
            ivCategoryPic.setImageResource(packageModel.getImageResource());
            tvPackageNumber.setText(packageModel.getPackageName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, package_details.class);
                    intent.putExtra("imageResource", packageModel.getImageResource());
                    intent.putExtra("packageName", packageModel.getPackageName());
                    context.startActivity(intent);
                }
            });
        }
    }
}