package com.example.android.popularmovies1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sabrina on 5/31/18.
 */

public interface ApiService {
    @GET("{sortBy}?")
    Call<MovieList> getJSON(
            @Path("sortBy") String sortBy,
            @Query("api_key") String key);
}
