package com.example.android.popularmovies1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview_movies)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_error)
    TextView mErrorMessage;

    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    private MoviesAdapter mMoviesAdapter;
    private static final String API_KEY = BuildConfig.MY_MOVIE_DB_API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mMoviesAdapter = new MoviesAdapter();
        mRecyclerView.setAdapter(mMoviesAdapter);

        fetchData("popular");
    }

    public void fetchData(String sortBy) {
        ApiService apiService = RetroClient.getApiService();
        Call<MovieList> call = apiService.getJSON(sortBy, API_KEY);
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful()) {
                    mMoviesAdapter.setMovieData(response.body().getResults());
                    showMovieData();
                }
                else
                    showError();
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                showError();
                Log.e(MainActivity.class.getSimpleName(), t.toString());
            }
        });
    }

    public void showMovieData() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void showError() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }
}
