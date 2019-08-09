package com.dicoding.moviestate.movie.nowplaying;

import com.dicoding.moviestate.base.BaseViewModel;
import com.dicoding.moviestate.entity.MovieResponse;
import com.dicoding.moviestate.network.MovieDataSources;
import com.dicoding.moviestate.network.MovieDataSourcesCallback;

public class MovieNowPlayingViewModel extends BaseViewModel {

    @Override
    public void getMovieByUrl(String url) {
        MovieDataSources dataSources = new MovieDataSources();
        dataSources.getMovies(url, new MovieDataSourcesCallback() {
            @Override
            public void onSuccess(MovieResponse movieResponse) {
                observeMovie.postValue(movieResponse.getResults());
            }

            @Override
            public void onFailed(String error) {
                // TODO: do some magic here
            }
        });
    }
}