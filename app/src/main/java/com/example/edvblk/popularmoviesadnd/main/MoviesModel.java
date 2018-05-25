package com.example.edvblk.popularmoviesadnd.main;

import com.example.edvblk.popularmoviesadnd.utils.network.MoviesResultResponse;
import com.example.edvblk.popularmoviesadnd.utils.network.MoviesService;

import java.util.List;

import io.reactivex.Single;

public class MoviesModel implements MainContract.Model {
    private final MoviesService service;

    public MoviesModel(MoviesService service) {
        this.service = service;
    }

    @Override
    public Single<MoviesResultResponse<List<Movie>>> getPopularMovies() {
        return service.getPopularMovies();
    }

    @Override
    public Single<MoviesResultResponse<List<Movie>>> getHighestRatedMovies() {
        return service.getHighestRatedMovies();
    }
}
