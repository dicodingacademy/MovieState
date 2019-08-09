package com.dicoding.moviestate.movie.nowplaying;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.moviestate.R;
import com.dicoding.moviestate.base.BaseFragment;
import com.dicoding.moviestate.entity.MovieItem;
import com.dicoding.moviestate.movie.adapter.MovieAdapter;
import com.dicoding.moviestate.network.MovieDataSources;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieNowPlayingFragment extends BaseFragment<MovieNowPlayingViewModel> {

    private MovieAdapter movieAdapter;

    public MovieNowPlayingFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieAdapter = new MovieAdapter(new ArrayList<MovieItem>());

        RecyclerView movieList = view.findViewById(R.id.movie_list);
        movieList.setHasFixedSize(true);
        movieList.setLayoutManager(new LinearLayoutManager(getContext()));
        movieList.setAdapter(movieAdapter);

        movieViewModel.observeMovie.observe(this, movieObservable);
        movieViewModel.getMovieByUrl(MovieDataSources.URL_NOW_PLAYING);
    }

    Observer<List<MovieItem>> movieObservable = new Observer<List<MovieItem>>() {
        @Override
        public void onChanged(@Nullable List<MovieItem> movieItems) {
            movieAdapter.refill(movieItems);
        }
    };

    @Override
    public Class<MovieNowPlayingViewModel> provideViewModelClass() {
        return MovieNowPlayingViewModel.class;
    }
}
