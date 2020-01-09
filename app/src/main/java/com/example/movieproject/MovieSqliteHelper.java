package com.example.movieproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.movieproject.Model.Movie;

import java.util.ArrayList;

public class MovieSqliteHelper extends SQLiteOpenHelper {
    //DATABASE NAME
    public static final String DATABASE_NAME = "MovieDB";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_MOVIES = "movies";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";

    //COLUMN title
    public static final String KEY_TITLE = "title";

    //COLUMN description
    public static final String KEY_OVERVIEW = "overview";

    //COLUMN path to poster
    public static final String KEY_POSTERPATH = "posterpath";

    //COLUMN rating
    public static final String KEY_RATING = "rating";

    //SQL for creating users table
    public static final String SQL_TABLE_MOVIES = " CREATE TABLE " + TABLE_MOVIES
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_TITLE + " TEXT, "
            + KEY_OVERVIEW + " TEXT, "
            + KEY_POSTERPATH + " TEXT, "
            + KEY_RATING + " TEXT"
            + " ) ";


    public MovieSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_MOVIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_MOVIES);
    }

    //using this method we can add users to user table
    public void addMovie(Movie movie) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put id in  @values
        values.put(KEY_ID, movie.getId());

        //Put title in  @values
        values.put(KEY_TITLE, movie.getTitle());

        //Put description in  @values
        values.put(KEY_OVERVIEW, movie.getDescription());

        //Put posterpath in  @values
        values.put(KEY_POSTERPATH, movie.getPosterPath());

        //Put rating in  @values
        values.put(KEY_RATING, movie.getRating());

        // insert row
        long todo_id = db.insertOrThrow(TABLE_MOVIES, null, values);
        Log.d("TAGMOVIE", "inserted: "+todo_id);
    }

    public ArrayList<Movie> getMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +  TABLE_MOVIES, null);
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Movie movie = new Movie(
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))),
                    cursor.getString(cursor.getColumnIndex(KEY_TITLE)),
                    cursor.getString(cursor.getColumnIndex(KEY_OVERVIEW)),
                    cursor.getString(cursor.getColumnIndex(KEY_POSTERPATH)),
                    cursor.getString(cursor.getColumnIndex(KEY_RATING))
            );
            movies.add(movie);
        }
        return movies;
    }
}
