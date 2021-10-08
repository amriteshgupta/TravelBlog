package com.amritesh.travelblog.repository;

import android.content.Context;

import com.amritesh.travelblog.database.AppDatabase;
import com.amritesh.travelblog.database.BlogDAO;
import com.amritesh.travelblog.database.DatabaseProvider;
import com.amritesh.travelblog.http.Blog;
import com.amritesh.travelblog.http.BlogHttpClient;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BlogRepository {

    private BlogHttpClient httpClient;
    private AppDatabase database;
    private Executor executor;

    public BlogRepository(Context context) {
        httpClient = BlogHttpClient.INSTANCE;
        database = DatabaseProvider.getInstance(context.getApplicationContext());
        executor = Executors.newSingleThreadExecutor();
    }
    public void loadDataFromDatabase(DataFromDatabaseCallback callback) {
        executor.execute(() -> callback.onSuccess(database.blogDao().getAll()));
    }

    public void loadDataFromNetwork(DataFromNetworkCallback callback) {
        executor.execute(() -> {
            List<Blog> blogList = httpClient.loadBlogArticles();
            if (blogList == null) {
                callback.onError(); // 1
            } else {
                BlogDAO blogDAO = database.blogDao();
                blogDAO.deleteAll(); // 2
                blogDAO.insertAll(blogList); // 3
                callback.onSuccess(blogList); // 4
            }
        });
    }
}