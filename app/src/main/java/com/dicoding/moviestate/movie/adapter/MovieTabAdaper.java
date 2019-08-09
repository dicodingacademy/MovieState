package com.dicoding.moviestate.movie.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dicoding.moviestate.movie.nowplaying.MovieNowPlayingFragment;
import com.dicoding.moviestate.movie.upcoming.MovieUpComingFragment;

public class MovieTabAdaper extends FragmentPagerAdapter {

    public MovieTabAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new MovieNowPlayingFragment();
        }
        return new MovieUpComingFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "Now Playing";
        }
        return "Up Coming";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
