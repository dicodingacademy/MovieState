package com.dicoding.moviestate.base;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dicoding.moviestate.entity.MovieItem;

import java.util.List;

public abstract class BaseViewModel extends ViewModel {

    public MutableLiveData<List<MovieItem>> observeMovie = new MutableLiveData<>();

    public abstract void getMovieByUrl(String url);
}
