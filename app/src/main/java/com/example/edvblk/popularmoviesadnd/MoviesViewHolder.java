package com.example.edvblk.popularmoviesadnd;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

class MoviesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView)
    ImageView imageView;

    public MoviesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(Movie movie) {
    }
}