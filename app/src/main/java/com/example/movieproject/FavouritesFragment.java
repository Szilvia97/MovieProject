package com.example.movieproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieproject.Model.Movie;

import java.util.ArrayList;


public class FavouritesFragment extends Fragment {

    MovieSqliteHelper movieSqliteHelper;
    ArrayList<Movie> movie;
    private MoviesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView = view.findViewById(R.id.rv_fav);
        movieSqliteHelper = new MovieSqliteHelper(getActivity().getApplicationContext());

        movie = movieSqliteHelper.getMovies();
        if (adapter == null){
            layoutManager = new LinearLayoutManager(getContext());
            adapter = new MoviesAdapter(movie, getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
        else{
            adapter.updateMovies(movie);
            adapter.notifyDataSetChanged();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter = null;
        layoutManager = null;
    }

}
