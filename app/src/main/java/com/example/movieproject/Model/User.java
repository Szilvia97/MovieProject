package com.example.movieproject.Model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.movieproject.SqliteHelper;

public class User {
    protected SQLiteDatabase MovieDB;
    private SqliteHelper dbHelper;
    private Context mContext;

    public String id;
    public String userName;
    public String email;
    public String password;

    public User(String id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

}