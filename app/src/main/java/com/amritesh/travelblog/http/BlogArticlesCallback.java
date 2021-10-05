package com.amritesh.travelblog.http;

import java.util.List;

public interface BlogArticlesCallback {
    void onSuccess(List<Blog> blogList);
    void onError();
}

