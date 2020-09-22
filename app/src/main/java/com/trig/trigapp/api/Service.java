package com.trig.trigapp.api;


import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Response.LoginResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Service {

//    @Headers("Content-Type: application/json")
//    @POST("login")
//    Call<ParentalLoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("login/login")
    Call<LoginResponse> callLogin(@Body LoginRequest loginRequest);

   }