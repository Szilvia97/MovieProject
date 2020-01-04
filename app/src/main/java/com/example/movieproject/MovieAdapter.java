package com.example.movieproject;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    List<Movie> movies;
    Context context;

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;

        public MovieViewHolder(@NonNull View view) {
            super(view);
            this.cardView = view.findViewById(R.id.cv_movie_item);
        }
    }

    public MovieAdapter(List<Movie> movies, Context context){
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieAdapter.MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        ((TextView) holder.cardView.findViewById(R.id.tv_movie_title)).
                setText(movies.get(position).getTitle());
        ((TextView) holder.cardView.findViewById(R.id.tv_movie_description)).
                setText(movies.get(position).getDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.showDetails(movies.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateMovies(List<Movie> movies){
        this.movies = movies;
    }
}
