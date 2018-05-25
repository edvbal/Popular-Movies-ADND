package com.example.edvblk.popularmoviesadnd.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edvblk.popularmoviesadnd.main.Movie;
import com.example.edvblk.popularmoviesadnd.R;
import com.example.edvblk.popularmoviesadnd.base.BaseActivity;
import com.example.edvblk.popularmoviesadnd.utils.image.DefaultImageUrlProvider;
import com.example.edvblk.popularmoviesadnd.utils.image.GlideImageLoader;
import com.example.edvblk.popularmoviesadnd.utils.image.ImageUrlProvider;

import butterknife.BindView;

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsContract.View {
    public static final String INTENT_EXTRA_KEY_MOVIE = "key.movies";
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.overviewTextView)
    TextView overviewTextView;
    @BindView(R.id.releaseDate)
    TextView releaseDate;
    @BindView(R.id.averageVote)
    TextView averageVote;
    private GlideImageLoader imageLoader;
    private MovieDetailsContract.Presenter presenter;

    public static void start(Context context, Movie movie) {
        Intent starter = new Intent(context, MovieDetailsActivity.class);
        starter.putExtra(INTENT_EXTRA_KEY_MOVIE, movie);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setHomeAsUp();
        initFields();
        presenter.onCreate();
    }

    private void initFields() {
        imageLoader = new GlideImageLoader(this);
        presenter = new MovieDetailsPresenter();
        presenter.takeView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void showMovieDetails() {
        if (getIntent().getExtras() == null) {
            return;
        }
        Movie movie = (Movie) getIntent().getExtras().get(INTENT_EXTRA_KEY_MOVIE);
        if (movie == null) {
            return;
        }
        setImageViewFromUrl(movie.getPosterPath());
        overviewTextView.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());
        averageVote.setText(String.valueOf(movie.getAverageVote()));
        collapsingToolbar.setTitle(movie.getTitle());
    }

    private void setImageViewFromUrl(String url) {
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        ImageUrlProvider provider = new DefaultImageUrlProvider(widthPixels);
        imageLoader.loadImageFromUrl(imageView, provider.provideUrl(url));
    }
}