package com.example.edvblk.popularmoviesadnd;

import com.example.edvblk.popularmoviesadnd.utils.network.InternetChecker;
import com.example.edvblk.popularmoviesadnd.utils.network.MoviesResultResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

import static com.example.edvblk.popularmoviesadnd.MainContract.Model;
import static com.example.edvblk.popularmoviesadnd.MainContract.View;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class MoviesPresenterTest {
    private static final String DEFAULT_ERROR_MESSAGE = "errorMessage";
    private final List<Movie> moviesList = Arrays.asList(new Movie("link1"), new Movie("link2"));
    private final Scheduler scheduler = Schedulers.trampoline();
    private final MoviesResultResponse<List<Movie>> moviesResponse
            = new MoviesResultResponse<>(moviesList);
    private MoviesPresenter presenter;
    private InternetChecker internetChecker;
    private View view;
    private MessagesProvider messagesProvider;
    private Model model;

    @Before
    public void setUp() throws Exception {
        view = mock(View.class);
        model = mock(Model.class);
        internetChecker = mock(InternetChecker.class);
        messagesProvider = mock(MessagesProvider.class);
        presenter = new MoviesPresenter(model, internetChecker, messagesProvider, scheduler);
        presenter.takeView(view);
        when(messagesProvider.provideNetworkErrorMessage()).thenReturn(DEFAULT_ERROR_MESSAGE);
        when(messagesProvider.provideRequestErrorMessage()).thenReturn(DEFAULT_ERROR_MESSAGE);
        when(internetChecker.isInternetAvailable()).thenReturn(true);
        when(model.getMovies()).thenReturn(Single.just(moviesResponse));
    }

    @Test
    public void onCreate_noInternet_callsViewShowErrorWithProvidedErrorMessage() throws Exception {
        when(internetChecker.isInternetAvailable()).thenReturn(false);

        presenter.onCreate();

        verify(view).showError(DEFAULT_ERROR_MESSAGE);
        verify(messagesProvider).provideNetworkErrorMessage();
    }

    @Test
    public void onCreate_noInternet_doesNotCallModel() throws Exception {
        when(internetChecker.isInternetAvailable()).thenReturn(false);

        presenter.onCreate();

        verifyZeroInteractions(model);
    }

    @Test
    public void onCreate_hasInternet_callsModelGetMovies() throws Exception {
        when(internetChecker.isInternetAvailable()).thenReturn(true);

        presenter.onCreate();

        verify(model).getMovies();
    }

    @Test
    public void onCreate_successfulResponse_callsViewPopulateView() throws Exception {
        presenter.onCreate();

        verify(view).populateView(moviesList);
    }

    @Test
    public void onCreate_failureResponse_callsViewShowErrorWithProvidedMessage() throws Exception {
        when(model.getMovies()).thenReturn(Single.error(new RuntimeException()));

        presenter.onCreate();

        verify(view).showError(messagesProvider.provideRequestErrorMessage());
    }

    @Test
    public void dropView_viewsAreNotCalled() throws Exception {
        TestScheduler testScheduler = new TestScheduler();
        when(model.getMovies()).thenReturn(Single.just(moviesResponse).subscribeOn(testScheduler));

        presenter.onCreate();
        presenter.dropView();
        testScheduler.triggerActions();

        verifyZeroInteractions(view);
    }
}