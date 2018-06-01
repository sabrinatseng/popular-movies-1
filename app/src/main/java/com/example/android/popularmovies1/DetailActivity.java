package com.example.android.popularmovies1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sabrina on 6/1/18.
 */

public class DetailActivity extends AppCompatActivity{
    @BindView(R.id.iv_poster)
    private
    ImageView mImageViewPoster;

    @BindView(R.id.tv_release_date)
    private
    TextView mTextViewReleaseDate;

    @BindView(R.id.tv_avg_rating)
    private
    TextView mTextViewAvgRating;

    @BindView(R.id.tv_synopsis)
    private
    TextView mTextViewSynopsis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        try {
            Movie movie = intent.getParcelableExtra(getString(R.string.extras_movie));
            populateUI(movie);
        }
        catch (Exception e) {
            e.printStackTrace();
            finish();
            Toast.makeText(this, getString(R.string.detail_error_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void populateUI(Movie movie) {
        setTitle(movie.getTitle());

        String url = MoviesAdapter.BASE_POSTER_URL + MoviesAdapter.POSTER_SIZE + movie.getPosterPath();
        Picasso.get().load(url).into(mImageViewPoster);

        mTextViewReleaseDate.setText(movie.getReleaseDate());
        mTextViewAvgRating.setText(movie.getVoteAvg());
        mTextViewSynopsis.setText(movie.getOverview());
    }
}
