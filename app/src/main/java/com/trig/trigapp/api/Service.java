package com.trig.trigapp.api;


import com.google.gson.JsonArray;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Response.CourseListResponse;
import com.trig.trigapp.api.Response.DashboardResponse;
import com.trig.trigapp.api.Response.LoginResponse;
import com.trig.trigapp.api.Response.ProfileResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Service {

//    @Headers("Content-Type: application/json")
//    @POST("login")
//    Call<ParentalLoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("login/login")
    Call<LoginResponse> callLogin(@Body LoginRequest loginRequest);

    @POST("UserProfile\\GetProfile?userid=9919&profile=user")
    Call<ProfileResponse> callProfile(@Query("user_id") String user);

    @POST("UserDashBoard/getdashboard?user_id=9919")
    Call<DashboardResponse> callDashboard();

    @POST("Course/GetCourseTopics?user_id=9919")
    Call<JsonArray> getCourses();

   }