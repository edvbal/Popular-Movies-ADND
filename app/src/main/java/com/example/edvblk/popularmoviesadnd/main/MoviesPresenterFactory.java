package com.example.edvblk.popularmoviesadnd.main;

import android.content.Context;

import com.example.edvblk.popularmoviesadnd.MessagesProviderImpl;
import com.example.edvblk.popularmoviesadnd.base.BaseApplication;
import com.example.edvblk.popularmoviesadnd.main.MoviesModel;
import com.example.edvblk.popularmoviesadnd.main.MoviesPresenter;
import com.example.edvblk.popularmoviesadnd.utils.network.DefaultInternetChecker;
import com.example.edvblk.popularmoviesadnd.utils.network.MoviesService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Retrofit;

public class MoviesPresenterFactory {
    MoviesPresenter create(Context context) {
        Retrofit retrofit = BaseApplication.getRetrofit(context);
        MoviesService service = retrofit.create(MoviesService.class);
        DefaultInternetChecker internetChecker = new DefaultInternetChecker(context);
        MessagesProviderImpl messagesProvider = new MessagesProviderImpl(context.getResources());
        return new MoviesPresenter(
                new MoviesModel(service),
                internetChecker,
                messagesProvider,
                AndroidSchedulers.mainThread()
        );
    }
}
