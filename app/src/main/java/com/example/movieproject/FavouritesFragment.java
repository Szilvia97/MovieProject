package com.example.movieproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class FavouritesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    public static interface Queries {
        @GET("movie/popular")
        Call<PageModel> getPopularMovies(@Query("page") int page, @Query("api_key") String apiKey);

        @GET("search/movie")
        Call<PageModel> searchMovie(@Query("page") int page, @Query("api_key") String apiKey, @Query("query") String query);

        @GET("movie/{movie_id}/similar")
        Call<PageModel> getSimilar(@Path("movie_id") int id, @Query("page") int page, @Query("api_key") String apiKey);

//        @GET("movie/{movie_id}/images")
//        Call<Images> getImages(@Path("movie_id") int id, @Query("api_key") String apiKey);
    }
}
