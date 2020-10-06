package com.trig.trigapp.api;

import com.google.gson.JsonArray;
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Request.LoadAssignmentsReq;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Request.GetProfileDetailsReq;
import com.trig.trigapp.api.Request.SubmitAssessmentReq;
import com.trig.trigapp.api.Request.User_id;
import com.trig.trigapp.api.Request.getCourseDetailsReq;
import com.trig.trigapp.api.Response.CommonResponse;
import com.trig.trigapp.api.Response.getAssessmentListRes;
import com.trig.trigapp.api.Response.getCourseDetailsRes;
import com.trig.trigapp.api.Response.getCourseListRes;
import com.trig.trigapp.api.Response.getCourseTopicsRes;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.trig.trigapp.api.Response.getFeedbackRes;
import com.trig.trigapp.api.Response.getLoadAssignmentsRes;
import com.trig.trigapp.api.Response.getLoginRes;
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
    Call<JsonArray> getCourseList(@Body getCourseDetailsReq req);

    @POST("CourseDetails/GetCourseDetails")
    Call<getCourseDetailsRes> getCourseDetails(@Body getCourseDetailsReq req);

    @POST("Course/GetCourseTopics")
    Call<JsonArray> getCourseTopics(@Body CommonReq req);

    @POST("UserProfile/GetFeedback")
    Call<getFeedbackRes> getFeedback(@Body CommonReq req);

    @POST("UserProfile/GetProfile")
    Call<CommonResponse> getProfile(@Body GetProfileDetailsReq req);

    @POST("UserAssessment/getAssessmentList")
    Call<JsonArray> getAssessmentList(@Body User_id req);

    @POST("SubmitAssessment/getScore")
    Call<getScoreRes> getScore(@Body LoadAssignmentsReq req);

    @POST("UserAssessment/LoadAssessment")
    Call<JsonArray> loadAssignments(@Body LoadAssignmentsReq req);


    @POST("SubmitAssessment/save")
    Call<JsonArray> submitAssessment(@Body SubmitAssessmentReq submitAssessmentReq);

    @POST("TrainerUnit/GetBranch")
    Call<JsonArray> getBranch(@Body  User_id req);

   }