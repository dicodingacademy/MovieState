package com.dicoding.moviestate.base;

import android.support.v4.app.Fragment;

import com.dicoding.moviestate.network.MovieDataSources;

public class BaseFragment extends Fragment {

    public static final String KEY_MOVIES = "movies";

    public MovieDataSources getMovieDataSources() {
        return new MovieDataSources();
    }
}
