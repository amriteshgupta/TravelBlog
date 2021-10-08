package com.amritesh.travelblog.database;

import androidx.room.*;

import com.amritesh.travelblog.http.*;

@Database(entities = {Blog.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BlogDAO blogDao();
}