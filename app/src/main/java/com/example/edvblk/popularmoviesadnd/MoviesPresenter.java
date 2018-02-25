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
    private final DefaultInternetChecker internetChecker;
    private final MessagesProvider messagesProvider;
    private final Disposable disposable = Disposables.disposed();

    MoviesPresenter(
            Model model, DefaultInternetChecker internetChecker,
            MessagesProvider messagesProvider
    ) {
        this.model = model;
        this.internetChecker = internetChecker;
        this.messagesProvider = messagesProvider;
    }

    @Override
    public void onCreate() {
        if (!internetChecker.isInternetAvailable()) {
            onView(view -> {
                view.showError(messagesProvider.provideNetworkErrorMessage());
            });
            return;
        }
        model.getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> onView(
                        view -> view.populateView(movies.getResult())
                ), throwable -> onView(
                        view -> view.showError(messagesProvider.provideRequestErrorMessage())));
    }

    @Override
    public void dropView() {
        disposable.dispose();
        super.dropView();
    }
}
