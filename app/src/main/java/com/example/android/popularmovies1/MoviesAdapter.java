package com.example.android.popularmovies1;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sabrina on 5/29/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {
    public MoviesAdapter() {}

    public List<Movie> mMovieData;
    private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/";
    private static final String SIZE = "w185/";

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView mMovieImageView;

        public MoviesAdapterViewHolder(View view) {
            super(view);

            mMovieImageView = (ImageView) view.findViewById(R.id.iv_movie);
        }


    }

    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movies_grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {
        String url = BASE_POSTER_URL + SIZE + mMovieData.get(position).getPosterPath(); //use position to get the correct image url
        Picasso.get().load(url).into(holder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        if (mMovieData == null) return 0;
        return mMovieData.size();
    }

    public void setMovieData(List<Movie> movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }

}

