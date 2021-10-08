package com.amritesh.travelblog.repository;

import com.amritesh.travelblog.http.Blog;

import java.util.List;

public interface DataFromNetworkCallback {
    void onSuccess(List<Blog> blogList);
    void onError();
}
