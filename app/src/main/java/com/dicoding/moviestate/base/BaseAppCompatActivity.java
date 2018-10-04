package com.dicoding.moviestate.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;

@SuppressLint("Registered")
public class BaseAppCompatActivity extends AppCompatActivity {

    public static final String KEY_TITLE = "title";
    public static final String KEY_FRAGMENT = "fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(this);
    }
}
