package com.example.edvblk.popularmoviesadnd;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

class GlideImageLoader implements BaseImageLoader {
    private final Context context;
    private final RequestManager requestManager;

    GlideImageLoader(Context context) {
        this.context = context;
        requestManager = Glide.with(context);
    }

    @Override
    public void loadImageFromUrl(View view, String url) {
        requestManager.load(url).into((ImageView) view);
    }
}
