package com.example.edvblk.popularmoviesadnd;

import android.content.Context;

import com.example.edvblk.popularmoviesadnd.base.BaseApplication;
import com.example.edvblk.popularmoviesadnd.utils.network.DefaultInternetChecker;
import com.example.edvblk.popularmoviesadnd.utils.network.MoviesService;

import retrofit2.Retrofit;

public class MoviesPresenterFactory {
    public MoviesPresenter create(Context context) {
        Retrofit retrofit = BaseApplication.getRetrofit(context);
        MoviesService service = retrofit.create(MoviesService.class);
        return new MoviesPresenter(new MoviesModel(service), new DefaultInternetChecker(context));
    }
}
