package com.example.edvblk.popularmoviesadnd;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {
    private final List<Movie> items = new ArrayList<>();
    private final BaseImageLoader baseImageLoader;

    public MoviesAdapter(BaseImageLoader baseImageLoader) {
        this.baseImageLoader = baseImageLoader;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_movie, parent, false);
        return new MoviesViewHolder(itemView, baseImageLoader);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Movie> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }
}
