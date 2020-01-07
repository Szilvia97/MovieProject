package com.example.movieproject;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular")
    Call<PageResult> getPopularMovies(@Query("page") int page, @Query("api_key") String apiKey);
}