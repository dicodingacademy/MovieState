package com.dicoding.moviestate.network;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.dicoding.moviestate.BuildConfig;
import com.dicoding.moviestate.entity.MovieResponse;

public class MovieDataSources {

    public static final String URL_TOP_RATED = BuildConfig.BASE_URL_TMDB + "top_rated?api_key={apiKey}&language=en-US&page=1";
    public static final String URL_NOW_PLAYING = BuildConfig.BASE_URL_TMDB + "now_playing?api_key={apiKey}&language=en-US&page=1";
    public static final String URL_UP_COMING = BuildConfig.BASE_URL_TMDB + "upcoming?api_key={apiKey}&language=en-US&page=1";
    public static final String URL_POPULAR = BuildConfig.BASE_URL_TMDB + "popular?api_key={apiKey}&language=en-US&page=1";

    public void getMovies(String movieEndpoint, final MovieDataSourcesCallback callback) {
        AndroidNetworking.get(movieEndpoint)
                .addPathParameter("apiKey", BuildConfig.TMDB_API_KEY)
                .setTag(MovieDataSources.class)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsObject(MovieResponse.class, new ParsedRequestListener<MovieResponse>() {
                    @Override
                    public void onResponse(MovieResponse response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ERROR", "onError: ", anError);
                        callback.onFailed("Terjadi kesalahan saat menghubungi server");
                    }
                });
    }
}
