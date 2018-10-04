package com.dicoding.moviestate.movie;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dicoding.moviestate.R;
import com.dicoding.moviestate.base.BaseFragment;
import com.dicoding.moviestate.entity.MovieItem;
import com.dicoding.moviestate.entity.MovieResponse;
import com.dicoding.moviestate.movie.adapter.MovieAdapter;
import com.dicoding.moviestate.network.MovieDataSources;
import com.dicoding.moviestate.network.MovieDataSourcesCallback;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieTopRatedFragment extends BaseFragment implements MovieDataSourcesCallback {

    private ArrayList<MovieItem> movies = new ArrayList<>();
    private MovieAdapter movieAdapter;

    public MovieTopRatedFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieAdapter = new MovieAdapter(movies);

        RecyclerView movieList = view.findViewById(R.id.movie_list);
        movieList.setHasFixedSize(true);
        movieList.setLayoutManager(new LinearLayoutManager(getContext()));
        movieList.setAdapter(movieAdapter);

        if (savedInstanceState == null) {
            getMovieDataSources().getMovies(MovieDataSources.URL_TOP_RATED, this);
        } else {
            movies = savedInstanceState.getParcelableArrayList(KEY_MOVIES);
            movieAdapter.refill(movies);
        }
    }

    @Override
    public void onSuccess(MovieResponse movieResponse) {
        movies = movieResponse.getResults();
        movieAdapter.refill(movies);
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_MOVIES, movies);
        super.onSaveInstanceState(outState);
    }

}
