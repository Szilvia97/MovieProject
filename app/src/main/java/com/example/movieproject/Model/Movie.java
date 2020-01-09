package com.example.movieproject.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Movie implements Serializable {

    private int id;

    private String title;

    private String overview;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("vote_average")
    private String rating;

    public Movie(int id, String title, String overview, String posterPath, String rating) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return overview;
    }

    public void setDescription(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getRating() {return rating;}

    public void setRating(String Rating){this.rating = Rating;}

}