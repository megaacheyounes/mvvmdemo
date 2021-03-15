package com.megaache.mvvmdemo.network;

import com.megaache.mvvmdemo.Config;
import com.megaache.mvvmdemo.network.request.LoginRequest;
import com.megaache.mvvmdemo.network.response.LoginResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIServices {
    @POST(Config.LOGIN_URL)
    @Headers("Content-Type: application/json;charset=UTF-8")
    public Observable<LoginResponse> login(@Body LoginRequest loginRequest);

}
