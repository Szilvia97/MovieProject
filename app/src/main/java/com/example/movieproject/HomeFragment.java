package com.example.movieproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {

    private Call<PageModel> call;
    private MoviesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rv_search);
        Queries getQuery = Retrofit.getRetrofit().create(Queries.class);

        call = getQuery.getPopularMovies(1, Retrofit.KEY);

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

        return view;
    }

}
