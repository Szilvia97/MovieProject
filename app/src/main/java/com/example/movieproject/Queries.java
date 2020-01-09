package com.example.movieproject;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Queries {
    @GET("movie/popular")
    Call<PageModel> getPopular(@Query("page") int page, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<PageModel> getSearch(@Query("page") int page, @Query("api_key") String apiKey, @Query("query") String q);

}
