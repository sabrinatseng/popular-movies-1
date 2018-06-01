package com.example.android.popularmovies1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sabrina on 5/31/18.
 */

public class RetroClient {
    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";

    private static final String BASE_URL_POPULAR = "http://api.themoviedb.org/3/movie/popular";
    private static final String BASE_URL_TOP_RATED = "http://api.themoviedb.org/3/movie/top_rated";

    private static final String KEY_PARAM = "api_key";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
