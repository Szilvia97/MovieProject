package com.example.movieproject;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.movieproject.Model.Movie;
import com.google.gson.Gson;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    List<Movie> movies;
    Context context;

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;

        public MovieViewHolder(@NonNull View view) {
            super(view);
            this.cardView = view.findViewById(R.id.cv_item);
        }
    }

    public MoviesAdapter(List<Movie> movies, Context context){
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MoviesAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        ((TextView) holder.cardView.findViewById(R.id.tv_title)).
                setText(movies.get(position).getTitle());
        ((TextView) holder.cardView.findViewById(R.id.tv_desc)).
                setText(movies.get(position).getDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new DetailsFragment();
                Bundle args = new Bundle();
                Gson gson = new Gson();
                String movie = gson.toJson(movies.get(position));
                args.putString("movie", movie);
                fragment.setArguments(args);
                NavigationActivity activity = (NavigationActivity) view.getContext();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.navigationContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Glide.with(context).
                load("https://image.tmdb.org/t/p/w500" + movies.get(position).getPosterPath()).
                placeholder(R.drawable.ic_launcher_foreground).
                into((ImageView) holder.cardView.findViewById(R.id.iv_poster));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateMovies(List<Movie> movies){
        this.movies = movies;
    }
}
