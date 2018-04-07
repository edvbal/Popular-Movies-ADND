package com.example.edvblk.popularmoviesadnd;

import com.example.edvblk.popularmoviesadnd.utils.network.InternetChecker;
import com.example.edvblk.popularmoviesadnd.utils.network.MoviesResultResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
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
    private final TestScheduler scheduler = new TestScheduler();
    private final MoviesResultResponse<List<Movie>> moviesResponse
            = new MoviesResultResponse<>(moviesList);
    private MoviesPresenter presenter;
    private InternetChecker internetChecker;
    private View view;
    private MessagesProvider messagesProvider;
    private Model model;

    @Before
    public void setUp() {
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
    public void onCreate_noInternet_callsViewShowErrorWithProvidedErrorMessage() {
        when(internetChecker.isInternetAvailable()).thenReturn(false);

        presenter.onCreate();
        scheduler.triggerActions();

        verify(view).showError(DEFAULT_ERROR_MESSAGE);
        verify(messagesProvider).provideNetworkErrorMessage();
    }

    @Test
    public void onCreate_noInternet_doesNotCallModel() {
        when(internetChecker.isInternetAvailable()).thenReturn(false);

        presenter.onCreate();
        scheduler.triggerActions();


        verifyZeroInteractions(model);
    }

    @Test
    public void onCreate_hasInternet_callsModelGetMovies() {
        when(internetChecker.isInternetAvailable()).thenReturn(true);

        presenter.onCreate();
        scheduler.triggerActions();


        verify(model).getMovies();
    }

    @Test
    public void onCreate_hasInternetAfterDispose_doesNotInteractWithView() {
        when(internetChecker.isInternetAvailable()).thenReturn(true);

        presenter.onCreate();
        presenter.dropView();
        scheduler.triggerActions();

        verifyZeroInteractions(view);
    }

    @Test
    public void onCreate_successfulResponse_callsViewPopulateView() {
        presenter.onCreate();
        scheduler.triggerActions();


        verify(view).populateView(moviesList);
    }

    @Test
    public void onCreate_failureResponse_callsViewShowErrorWithProvidedMessage() {
        when(model.getMovies()).thenReturn(Single.error(new RuntimeException()));

        presenter.onCreate();
        scheduler.triggerActions();


        verify(view).showError(messagesProvider.provideRequestErrorMessage());
    }

    @Test
    public void dropView_viewsAreNotCalled() {
        presenter.onCreate();
        presenter.dropView();
        scheduler.triggerActions();

        verifyZeroInteractions(view);
    }
}