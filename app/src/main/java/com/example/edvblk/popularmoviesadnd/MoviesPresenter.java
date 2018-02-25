package com.example.edvblk.popularmoviesadnd;

import com.example.edvblk.popularmoviesadnd.base.BasePresenterImpl;
import com.example.edvblk.popularmoviesadnd.utils.network.InternetChecker;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

import static com.example.edvblk.popularmoviesadnd.MainContract.Model;
import static com.example.edvblk.popularmoviesadnd.MainContract.Presenter;

class MoviesPresenter extends BasePresenterImpl<MainContract.View>
        implements Presenter {
    private final Model model;
    private final InternetChecker internetChecker;
    private final MessagesProvider messagesProvider;
    private final Disposable disposable = Disposables.disposed();
    private final Scheduler scheduler;

    MoviesPresenter(
            Model model,
            InternetChecker internetChecker,
            MessagesProvider messagesProvider,
            Scheduler scheduler
    ) {
        this.model = model;
        this.internetChecker = internetChecker;
        this.messagesProvider = messagesProvider;
        this.scheduler = scheduler;
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
                .observeOn(scheduler)
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
