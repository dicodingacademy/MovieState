package com.dicoding.moviestate.movie.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicoding.moviestate.R;
import com.dicoding.moviestate.entity.MovieItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private ArrayList<MovieItem> items;

    public MovieAdapter(ArrayList<MovieItem> items) {
        this.items = items;
    }

    public void refill(ArrayList<MovieItem> items) {
        this.items = new ArrayList<>();
        this.items.addAll(items);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder movieHolder, int i) {
        movieHolder.onBind(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvDateRelease, tvVote;
        private ImageView ivPoster, ivBackdrop;

        MovieHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.item_title);
            tvDateRelease = itemView.findViewById(R.id.item_date_release);
            tvVote = itemView.findViewById(R.id.item_vote);

            ivPoster = itemView.findViewById(R.id.item_poster);
            ivBackdrop = itemView.findViewById(R.id.item_backdrop);
        }

        void onBind(MovieItem item) {
            if (item.getPosterPath() != null && !item.getPosterPath().isEmpty()) {
                Picasso.get().load(item.getPosterPath()).transform(new CropCircleTransformation()).into(ivPoster);
            }

            if (item.getBackdropPath() != null && !item.getBackdropPath().isEmpty()) {
                Picasso.get().load(item.getPosterPath()).transform(new BlurTransformation(itemView.getContext(), 20)).into(ivBackdrop);
            }

            String title = checkTextIfNull(item.getTitle());
            if (title.length() > 30) {
                tvTitle.setText(String.format("%s...", title.substring(0, 29)));
            } else {
                tvTitle.setText(checkTextIfNull(item.getTitle()));
            }

            tvDateRelease.setText(checkTextIfNull(item.getReleaseDate()));
            tvVote.setText(checkTextIfNull("" + item.getVoteAverage()));
        }

        String checkTextIfNull(String text) {
            if (text != null && !text.isEmpty()) {
                return text;
            } else {
                return "-";
            }
        }
    }
}
