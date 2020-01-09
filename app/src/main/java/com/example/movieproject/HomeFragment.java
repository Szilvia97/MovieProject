package com.example.movieproject;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    private SearchView sv_movie;
    private String search;
    private Call<PageModel> call;
    private MoviesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rv_movie);
        sv_movie = view.findViewById(R.id.sv_movie);
        Searching();

     return view;
    }


    private void getpopularMovies() {
        Queries getQuery = Retrofit.getRetrofit().create(Queries.class);
        call = getQuery.getPopular(1, Retrofit.KEY);
        call.enqueue(new Callback<PageModel>() {
            @Override
            public void onResponse(Call<PageModel> call, Response<PageModel> response) {
                if (adapter == null) {
                    layoutManager = new LinearLayoutManager(getContext());
                    adapter = new MoviesAdapter(response.body().getResults(), getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.updateMovies(response.body().getResults());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PageModel> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR.", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getSearchMovies(String q){
        Queries querys = Retrofit.getRetrofit().create(Queries.class);
        call = querys.getSearch(1, Retrofit.KEY, q);
        call.enqueue(new Callback<PageModel>() {
            @Override
            public void onResponse(Call<PageModel> call, Response<PageModel> response) {
                if (adapter == null){
                    layoutManager = new LinearLayoutManager(getContext());
                    adapter = new MoviesAdapter(response.body().getResults(), getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    adapter.updateMovies(response.body().getResults());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PageModel> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Searching(){
        if (search == null || search.isEmpty()){
            getpopularMovies();
        }
        else{
            getSearchMovies(search);
        }

        sv_movie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String q) {
                search = q;
                getSearchMovies(q);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        adapter = null;
        layoutManager = null;
    }

}
