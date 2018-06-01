package com.example.android.popularmovies1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sabrina on 5/31/18.
 */

public class MovieList {
    @SerializedName("results")
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }
}
