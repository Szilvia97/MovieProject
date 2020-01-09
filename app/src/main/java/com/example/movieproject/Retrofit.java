package com.example.movieproject;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private static retrofit2.Retrofit retrofit;
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String KEY = "ffc5314021b5a0b512e53870d9eb8eed";

    public static retrofit2.Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}