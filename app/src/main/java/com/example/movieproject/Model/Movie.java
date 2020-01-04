package com.example.movieproject.Model;

import java.io.Serializable;


public class Movie implements Serializable {
    public static final String TABLE_NAME = "movies";

    public static final String COLUMN_ID = "movie_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_POSTER_PATH = "poster_path";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + COLUMN_TITLE + " TEXT, "
                    + COLUMN_DESCRIPTION + " TEXT, "
                    + COLUMN_POSTER_PATH + " TEXT, "
                    + COLUMN_USER_ID + " INTEGER"
                    + ")";


    private int id;

    private String title;

    private String description;

    private String posterPath;

    public Movie(int id, String title, String overview, String posterPath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.posterPath = posterPath;
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
        return description;
    }

    public void setDescription(String overview) {
        this.description = description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", posterPath='" + posterPath + '\'' +
                '}';
    }
}