package com.example.edvblk.popularmoviesadnd;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.edvblk.popularmoviesadnd.base.BaseImageLoader;
import com.example.edvblk.popularmoviesadnd.utils.image.ImageUrlProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

class MoviesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView)
    ImageView imageView;
    private final BaseImageLoader baseImageLoader;
    private final ImageUrlProvider imageUrlProvider;

    public MoviesViewHolder(
            View itemView,
            BaseImageLoader baseImageLoader,
            ImageUrlProvider imageUrlProvider
    ) {
        super(itemView);
        this.baseImageLoader = baseImageLoader;
        this.imageUrlProvider = imageUrlProvider;
        ButterKnife.bind(this, itemView);
    }

    public void onBind(Movie movie) {
        String posterPath = movie.getPosterPath();
        baseImageLoader.loadImageFromUrl(imageView, imageUrlProvider.provideUrl(posterPath));
    }
}
