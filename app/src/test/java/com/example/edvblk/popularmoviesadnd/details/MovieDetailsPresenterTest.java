package com.example.edvblk.popularmoviesadnd.details;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MovieDetailsPresenterTest {
    @Test
    public void onCreate_callsView() {
        MovieDetailsPresenter presenter = new MovieDetailsPresenter();
        MovieDetailsContract.View view = mock(MovieDetailsContract.View.class);
        presenter.takeView(view);

        presenter.onCreate();

        verify(view).showMovieDetails();
    }
}