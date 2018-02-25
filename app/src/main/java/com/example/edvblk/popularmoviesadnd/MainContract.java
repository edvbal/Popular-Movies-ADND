package com.example.edvblk.popularmoviesadnd;

import com.example.edvblk.popularmoviesadnd.base.BasePresenter;
import com.example.edvblk.popularmoviesadnd.utils.MoviesResultResponse;

import java.util.List;

import io.reactivex.Single;

public interface MainContract {
    interface View {
        void populateView(List<Movie> movies);
    }

    interface Presenter extends BasePresenter<View> {
        void onCreate();
    }

    interface Model {
        Single<MoviesResultResponse<List<Movie>>> getMovies();
    }
}
