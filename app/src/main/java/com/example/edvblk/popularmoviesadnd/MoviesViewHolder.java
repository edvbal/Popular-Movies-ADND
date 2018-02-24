package com.example.edvblk.popularmoviesadnd;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.edvblk.popularmoviesadnd.base.BaseImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

class MoviesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView)
    ImageView imageView;
    private final BaseImageLoader baseImageLoader;

    public MoviesViewHolder(View itemView, BaseImageLoader baseImageLoader) {
        super(itemView);
        this.baseImageLoader = baseImageLoader;
        ButterKnife.bind(this, itemView);
    }

    public void onBind(Movie movie) {
        baseImageLoader.loadImageFromUrl(imageView, movie.getImageUrl()
        );
    }
}
