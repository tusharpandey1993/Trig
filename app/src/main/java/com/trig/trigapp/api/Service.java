package com.trig.trigapp.api;

import com.google.gson.JsonArray;
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Request.LoadAssignmentsReq;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Request.GetProfileDetailsReq;
import com.trig.trigapp.api.Request.getCourseDetailsReq;
import com.trig.trigapp.api.Response.DashboardResponse;
import com.trig.trigapp.api.Response.LoadAssignmentsRes;
import com.trig.trigapp.api.Response.LoginResponse;
import com.trig.trigapp.api.Response.ProfileResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {


    @POST("login/login")
    Call<LoginResponse> callLogin(@Body LoginRequest loginRequest);

    @POST("UserDashBoard/getdashboard")
    Call<DashboardResponse> getDashboard(@Body CommonReq req);

    @POST("Course/GetCourseList")
    Call<JsonArray> getCourseList(@Body getCourseDetailsReq req);

    @POST("CourseDetails/GetCourseDetails")
    Call<LoadAssignmentsRes> getCourseDetails(@Body CommonReq req);

    @POST("Course/GetCourseTopics")
    Call<LoadAssignmentsRes> getCourseTopics(@Body CommonReq req);

    @POST("UserProfile/GetFeedback")
    Call<LoadAssignmentsRes> getFeedback(@Body CommonReq req);

    @POST("UserProfile/GetProfile")
    Call<ProfileResponse> getProfile(@Body GetProfileDetailsReq req);

    @POST("UserAssessment/getAssessmentList")
    Call<LoadAssignmentsRes> getAssessmentList(@Body CommonReq req);

    @POST("SubmitAssessment/getScore")
    Call<LoadAssignmentsRes> getScore(@Body LoadAssignmentsReq req);

    @POST("UserAssessment/LoadAssessment")
    Call<LoadAssignmentsRes> loadAssignments(@Body LoadAssignmentsReq req);

   }