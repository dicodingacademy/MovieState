package com.dicoding.moviestate.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.dicoding.moviestate.entity.Screen;
import com.dicoding.moviestate.network.MovieDataSources;

public abstract class BaseFragment extends Fragment {

    public BaseMovieViewModel movieViewModel;

    public abstract Screen provideScreen();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieViewModel = new BaseMovieViewModel.Factory()
                .setDataSources(new MovieDataSources())
                .setOwners(this)
                .setScreen(provideScreen())
                .build();
    }
}
