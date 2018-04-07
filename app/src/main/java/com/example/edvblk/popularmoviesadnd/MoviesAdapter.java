package com.example.edvblk.popularmoviesadnd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edvblk.popularmoviesadnd.base.BaseAdapter;
import com.example.edvblk.popularmoviesadnd.utils.image.ImageLoader;
import com.example.edvblk.popularmoviesadnd.utils.image.ImageUrlProvider;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends BaseAdapter {
    private final ImageLoader imageLoader;
    private final ImageUrlProvider imageUrlProvider;

    public MoviesAdapter(ImageLoader imageLoader, ImageUrlProvider imageUrlProvider) {
        this.imageLoader = imageLoader;
        this.imageUrlProvider = imageUrlProvider;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_movie, parent, false);
        return new MoviesViewHolder(itemView, imageLoader, imageUrlProvider);
    }
}
