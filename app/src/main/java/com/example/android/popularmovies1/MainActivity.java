package com.example.android.popularmovies1;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {
    @BindView(R.id.recyclerview_movies)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_error)
    TextView mErrorMessage;

    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    private MoviesAdapter mMoviesAdapter;
    private static final String API_KEY = BuildConfig.MY_MOVIE_DB_API_KEY;

    private String sortBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        sortBy = getString(R.string.sortByPopular_tag);  //default

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mMoviesAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mMoviesAdapter);

        fetchData(sortBy);
    }

    private void fetchData(String sortBy) {
        mLoadingIndicator.setVisibility(View.VISIBLE);

        if (!isConnected())
            Toast.makeText(this, getString(R.string.no_internet_error_message), Toast.LENGTH_LONG).show();
        else {
            ApiService apiService = RetroClient.getApiService();
            Call<MovieList> call = apiService.getJSON(sortBy, API_KEY);
            call.enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                    if (response.isSuccessful()) {
                        mMoviesAdapter.setMovieData(response.body().getResults());
                        showMovieData();
                    } else
                        showError();

                    mLoadingIndicator.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onFailure(Call<MovieList> call, Throwable t) {
                    showError();
                    Log.e(MainActivity.class.getSimpleName(), t.toString());

                    mLoadingIndicator.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    private void showMovieData() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showError() {
        mErrorMessage.setText(getString(R.string.error_message));
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_refresh:
                fetchData(sortBy);
                break;
            case R.id.action_popular:
                sortBy = getString(R.string.sortByPopular_tag);
                fetchData(sortBy);
                break;
            case R.id.action_top_rated:
                sortBy = getString(R.string.sortByTopRated_tag);
                fetchData(sortBy);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(Movie clickedMovie) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.extras_movie), clickedMovie);
        intent.putExtras(bundle);

        ActivityCompat.startActivity(this, intent, null);
    }

    private boolean isConnected()
    {
        //returns true if there is internet to prevent crashing if there is no internet
        final ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        assert cm != null;
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
