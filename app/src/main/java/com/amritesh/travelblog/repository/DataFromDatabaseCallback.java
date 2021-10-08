package com.amritesh.travelblog.repository;

import com.amritesh.travelblog.http.Blog;

import java.util.List;

public interface DataFromDatabaseCallback {
    void onSuccess(List<Blog> blogList);
}