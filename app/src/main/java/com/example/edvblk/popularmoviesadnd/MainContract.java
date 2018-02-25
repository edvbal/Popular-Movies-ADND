package com.example.edvblk.popularmoviesadnd;

import com.example.edvblk.popularmoviesadnd.base.BasePresenter;
import com.example.edvblk.popularmoviesadnd.utils.network.MoviesResultResponse;

import java.util.List;

import io.reactivex.Single;

public interface MainContract {
    interface View {
        void populateView(List<Movie> movies);

        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter<View> {
        void onCreate();
    }

    interface Model {
        Single<MoviesResultResponse<List<Movie>>> getMovies();
    }
}
