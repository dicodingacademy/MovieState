package com.dicoding.moviestate.network;

import com.dicoding.moviestate.entity.MovieResponse;

public interface MovieDataSourcesCallback {

    void onSuccess(MovieResponse movieResponse);

    void onFailed(String error);
}
