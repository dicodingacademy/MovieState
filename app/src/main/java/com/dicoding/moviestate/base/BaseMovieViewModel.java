package com.dicoding.moviestate.base;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.dicoding.moviestate.entity.MovieItem;
import com.dicoding.moviestate.entity.MovieResponse;
import com.dicoding.moviestate.entity.Screen;
import com.dicoding.moviestate.network.MovieDataSources;
import com.dicoding.moviestate.network.MovieDataSourcesCallback;

import java.util.List;

public class BaseMovieViewModel extends ViewModel {

    private Screen screen;
    private MovieDataSources dataSources;

    private BaseMovieViewModel(Screen screen, MovieDataSources dataSources) {
        this.screen = screen;
        this.dataSources = dataSources;

        getMovieByUrl();
    }

    public MutableLiveData<List<MovieItem>> observeMovie = new MutableLiveData<>();

    private void getMovieByUrl() {
        String url = "";

        switch (screen) {
            case NOW_PLAYING:
                url = MovieDataSources.URL_NOW_PLAYING;
                break;
            case POPULAR:
                url = MovieDataSources.URL_POPULAR;
                break;
            case UPCOMING:
                url = MovieDataSources.URL_UP_COMING;
                break;
            case TOP_RATED:
                url = MovieDataSources.URL_TOP_RATED;
        }

        dataSources.getMovies(url, new MovieDataSourcesCallback() {
            @Override
            public void onSuccess(MovieResponse movieResponse) {
                observeMovie.postValue(movieResponse.getResults());
            }

            @Override
            public void onFailed(String error) {
                // TODO: so something magic here
            }
        });

    }

    static class Factory {

        private Screen screen;
        private Fragment fragment;
        private MovieDataSources dataSources;

        Factory setScreen(Screen screen) {
            this.screen = screen;
            return this;
        }

        Factory setDataSources(MovieDataSources dataSources) {
            this.dataSources = dataSources;
            return this;
        }

        Factory setOwners(Fragment fragment) {
            this.fragment = fragment;
            return this;
        }

        BaseMovieViewModel build() {
            MovieModelFactory factory = new MovieModelFactory(screen, dataSources);
            return ViewModelProviders.of(fragment, factory).get(BaseMovieViewModel.class);
        }
    }

    private static class MovieModelFactory implements ViewModelProvider.Factory {

        private Screen screen;
        private MovieDataSources dataSources;

        private MovieModelFactory(Screen screen, MovieDataSources dataSources) {
            this.screen = screen;
            this.dataSources = dataSources;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new BaseMovieViewModel(screen, dataSources);
        }
    }
}
