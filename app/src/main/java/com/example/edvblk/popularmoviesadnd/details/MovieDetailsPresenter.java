package com.example.edvblk.popularmoviesadnd.details;

import com.example.edvblk.popularmoviesadnd.base.BasePresenterImpl;

class MovieDetailsPresenter extends BasePresenterImpl<MovieDetailsContract.View>
        implements MovieDetailsContract.Presenter {
    @Override
    public void onCreate() {
        onView(MovieDetailsContract.View::showMovieDetails);
    }
}
