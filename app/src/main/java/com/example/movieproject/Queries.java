package com.example.movieproject;

import android.provider.MediaStore;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Queries {
    @GET("movie/popular")
    Call<PageModel> getPopularMovies(@Query("page") int page, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<PageModel> searchMovie(@Query("page") int page, @Query("api_key") String apiKey, @Query("query") String query);

    @GET("movie/{movie_id}/similar")
    Call<PageModel> getSimilar(@Path("movie_id") int id, @Query("page") int page, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/images")
    Call<MediaStore.Images> getImages(@Path("movie_id") int id, @Query("api_key") String apiKey);
}
