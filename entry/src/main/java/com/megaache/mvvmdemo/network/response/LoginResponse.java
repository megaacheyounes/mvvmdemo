package com.megaache.mvvmdemo.network.response;

import com.megaache.mvvmdemo.base.BaseRepo;
import com.megaache.mvvmdemo.model.User;
import com.squareup.moshi.Json;

public class LoginResponse extends BaseRepo {
    @Json(name = "user")
    private User user;
    @Json(name = "accessToken")
    private String accessToken;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
