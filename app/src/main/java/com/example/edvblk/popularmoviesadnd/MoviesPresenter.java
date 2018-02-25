package com.example.edvblk.popularmoviesadnd;

import com.example.edvblk.popularmoviesadnd.base.BasePresenterImpl;
import com.example.edvblk.popularmoviesadnd.utils.network.DefaultInternetChecker;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

import static com.example.edvblk.popularmoviesadnd.MainContract.Model;
import static com.example.edvblk.popularmoviesadnd.MainContract.Presenter;

class MoviesPresenter extends BasePresenterImpl<MainContract.View>
        implements Presenter {
    private final Model model;
    private DefaultInternetChecker internetChecker;
    private final Disposable disposable = Disposables.disposed();

    MoviesPresenter(Model model, DefaultInternetChecker internetChecker) {
        this.model = model;
        this.internetChecker = internetChecker;
    }

    @Override
    public void onCreate() {
        if (!internetChecker.isInternetAvailable()) {
            return;
        }
        model.getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((movies) -> onView(view -> {
                    view.populateView(movies.getResult());
                }), throwable -> {
                    // empty
                });
    }

    @Override
    public void dropView() {
        disposable.dispose();
        super.dropView();
    }
}
