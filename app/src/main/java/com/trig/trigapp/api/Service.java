package com.trig.trigapp.api;

import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Request.LoadAssignmentsReq;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Request.GetProfileDetailsReq;
import com.trig.trigapp.api.Request.getCourseDetailsReq;
import com.trig.trigapp.api.Response.getCourseListRes;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.trig.trigapp.api.Response.getLoginRes;
import com.trig.trigapp.api.Response.getAssessmentListRes;
import com.trig.trigapp.api.Response.getCourseDetailsRes;
import com.trig.trigapp.api.Response.getCourseTopicsRes;
import com.trig.trigapp.api.Response.getFeedbackRes;
import com.trig.trigapp.api.Response.getLoadAssignmentsRes;
import com.trig.trigapp.api.Response.getProfileRes;
import com.trig.trigapp.api.Response.getScoreRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {


    @POST("login/login")
    Call<getLoginRes> callLogin(@Body LoginRequest loginRequest);

    @POST("UserDashBoard/getdashboard")
    Call<getDashboardRes> getDashboard(@Body CommonReq req);

    @POST("Course/GetCourseList")
    Call<getCourseListRes> getCourseList(@Body getCourseDetailsReq req);

    @POST("CourseDetails/GetCourseDetails")
    Call<getCourseDetailsRes> getCourseDetails(@Body CommonReq req);

    @POST("Course/GetCourseTopics")
    Call<getCourseTopicsRes> getCourseTopics(@Body CommonReq req);

    @POST("UserProfile/GetFeedback")
    Call<getFeedbackRes> getFeedback(@Body CommonReq req);

    @POST("UserProfile/GetProfile")
    Call<getProfileRes> getProfile(@Body GetProfileDetailsReq req);

    @POST("UserAssessment/getAssessmentList")
    Call<getAssessmentListRes> getAssessmentList(@Body CommonReq req);

    @POST("SubmitAssessment/getScore")
    Call<getScoreRes> getScore(@Body LoadAssignmentsReq req);

    @POST("UserAssessment/LoadAssessment")
    Call<getLoadAssignmentsRes> loadAssignments(@Body LoadAssignmentsReq req);

   }