package com.dicoding.moviestate.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.dicoding.moviestate.network.MovieDataSources;

public abstract class BaseFragment<T extends ViewModel> extends Fragment {

    public T movieViewModel;

    public abstract Class<T> provideViewModelClass();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieViewModel = ViewModelProviders.of(this).get(provideViewModelClass());
    }
}
