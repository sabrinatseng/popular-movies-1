package com.example.android.popularmovies1;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sabrina on 5/29/18.
 */

public class MoviesAdapter extends RecyclerView<MoviesAdapter.MoviesAdapterViewHolder> {
    public MoviesAdapter{}

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_movie)
        ImageView mMovieImageView;

        public MoviesAdapterViewholder(View view) {
            super(view);
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
