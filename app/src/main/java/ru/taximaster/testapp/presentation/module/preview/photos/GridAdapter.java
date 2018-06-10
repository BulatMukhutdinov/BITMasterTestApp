package ru.taximaster.testapp.presentation.module.preview.photos;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.taximaster.testapp.R;
import ru.taximaster.testapp.presentation.util.OnPhotoClickListener;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private final List<String> photos;
    private final OnPhotoClickListener listener;

    GridAdapter(List<String> photos, OnPhotoClickListener listener) {
        this.photos = photos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.preview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.photoUrl = photos.get(position);

        Picasso.with(holder.photo.getContext())
                .load(photos.get(position))
                .placeholder(holder.photo.getResources().getDrawable(R.drawable.iv_ico))
                .into(holder.photo);

        ViewCompat.setTransitionName(holder.photo, holder.photoUrl);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView photo;
        String photoUrl;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            photo = itemView.findViewById(R.id.photo);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(photoUrl, photo);
        }
    }
}