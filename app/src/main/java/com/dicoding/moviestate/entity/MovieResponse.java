package com.dicoding.moviestate.entity;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class MovieResponse{

	@SerializedName("results")
	private ArrayList<MovieItem> results;

	public ArrayList<MovieItem> getResults(){
		return results;
	}
}