package com.example.edvblk.popularmoviesadnd;

import com.example.edvblk.popularmoviesadnd.utils.network.MoviesResultResponse;
import com.example.edvblk.popularmoviesadnd.utils.network.MoviesService;

import java.util.List;

import io.reactivex.Single;

class MoviesModel implements MainContract.Model {
    private final MoviesService service;

    MoviesModel(MoviesService service) {
        this.service = service;
    }

    @Override
    public Single<MoviesResultResponse<List<Movie>>> getMovies() {
        return service.getMovies();
    }
}