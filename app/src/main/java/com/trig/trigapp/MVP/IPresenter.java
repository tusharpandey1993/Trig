package com.trig.trigapp.MVP;

import com.google.gson.JsonArray;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Response.CourseListResponse;
import com.trig.trigapp.api.Response.DashboardResponse;
import com.trig.trigapp.api.Response.LoginResponse;
import com.trig.trigapp.api.Response.ProfileResponse;

import java.util.List;

public interface IPresenter {


    void showProgressDialog();
    void hideProgressDialog();
    void onError(String error);
    void onError(String error,int code);
    void onError(Object error);
    void onError(Object error,int Code);
    void onResponse(LoginResponse loginResponse);
    void onResponseProfile(ProfileResponse profileResponse);
    void onResponseProfile(DashboardResponse dashboardResponse);
    void onResponseCourseList(JsonArray JsonArray);
//    void callAPPListAPI(String userName, String miko);
//    void onResponseUnsubscribeFeedbacks(getUnsubscribeFeedbacksRes response) ;
//    void subscribeFreeBundle(ASCommanResponse response) ;

}
