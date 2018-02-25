package com.example.edvblk.popularmoviesadnd;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("poster_path")
    private final String posterPath;

    public Movie(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
