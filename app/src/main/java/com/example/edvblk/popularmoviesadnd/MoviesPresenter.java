package com.example.edvblk.popularmoviesadnd;

import com.example.edvblk.popularmoviesadnd.base.BasePresenterImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

import static com.example.edvblk.popularmoviesadnd.MainContract.Model;
import static com.example.edvblk.popularmoviesadnd.MainContract.Presenter;

class MoviesPresenter extends BasePresenterImpl<MainContract.View>
        implements Presenter {
    private final Model model;
    private final Disposable disposable = Disposables.disposed();

    MoviesPresenter(Model model) {
        this.model = model;
    }

    @Override
    public void onCreate() {
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
