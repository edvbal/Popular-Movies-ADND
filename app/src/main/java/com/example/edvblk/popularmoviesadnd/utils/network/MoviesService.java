package com.example.edvblk.popularmoviesadnd.utils.network;

import com.example.edvblk.popularmoviesadnd.BuildConfig;
import com.example.edvblk.popularmoviesadnd.Movie;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface MoviesService {
    @GET("/" + BuildConfig.
            API_VERSION + "/" + "movie/popular")
    Single<MoviesResultResponse<List<Movie>>> getMovies();
}