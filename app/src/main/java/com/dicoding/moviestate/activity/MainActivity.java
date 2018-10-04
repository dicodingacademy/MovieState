package com.dicoding.moviestate.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dicoding.moviestate.R;
import com.dicoding.moviestate.base.BaseAppCompatActivity;
import com.dicoding.moviestate.movie.MovieHomeFragment;
import com.dicoding.moviestate.movie.MovieNowPlayingFragment;
import com.dicoding.moviestate.movie.MoviePopularMovie;
import com.dicoding.moviestate.movie.MovieTopRatedFragment;

public class MainActivity extends BaseAppCompatActivity {

    private Fragment pageContent = new MovieHomeFragment();
    private String title = "Home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.main_toolbar);
        final DrawerLayout drawerLayout = findViewById(R.id.main_drawer);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.main_navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        pageContent = new MovieHomeFragment();
                        title = "Home";
                        break;
                    case R.id.menu_popular:
                        pageContent = new MoviePopularMovie();
                        title = "Popular";
                        break;
                    case R.id.menu_top_rated:
                        pageContent = new MovieTopRatedFragment();
                        title = "Top Rated";
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pageContent).commit();
                toolbar.setTitle(title);
                drawerLayout.closeDrawers();
                return true;
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pageContent).commit();
            toolbar.setTitle(title);
        } else {
            pageContent = getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);
            title = savedInstanceState.getString(KEY_TITLE);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, pageContent).commit();
            toolbar.setTitle(title);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_TITLE, title);
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(outState);
    }
}
