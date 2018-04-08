package com.example.edvblk.popularmoviesadnd.details;

import com.example.edvblk.popularmoviesadnd.base.BasePresenter;

public class MovieDetailsContract {
    public interface View {
        void showMovieDetails();
    }

    public interface Presenter extends BasePresenter<View> {
        void onCreate();
    }
}
