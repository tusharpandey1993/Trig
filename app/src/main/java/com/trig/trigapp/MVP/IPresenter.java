package com.trig.trigapp.MVP;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.trig.trigapp.api.Response.CommonResponse;
import com.trig.trigapp.api.Response.getAssessmentListRes;
import com.trig.trigapp.api.Response.getCourseDetailsRes;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.trig.trigapp.api.Response.getFeedbackRes;
import com.trig.trigapp.api.Response.getLoadAssignmentsRes;
import com.trig.trigapp.api.Response.getLoginRes;

public interface IPresenter {


    default void showProgressDialog(){}
    default void hideProgressDialog(){}
    default void onError(String error){}
    default void onError(String error,int code){}
    default void onError(Object error){}
    default void onError(Object error,int Code){}
    default void onResponse(getLoginRes loginResponse){}
    default void onResponseProfile(CommonResponse commonResponse){}
    default void onResponseGetDashboard(getDashboardRes dashboardResponse){}
    default void onResponseCourseTopicList(JsonArray JsonArray){}
    default void onResponseAssessmentTopicList(JsonArray JsonArray){}
    default void onResponseFeedback(getFeedbackRes getFeedbackRes){}
    default void onResponseLoadAssessmentQuestions(JsonArray jsonElements){}
    default void onResponseVideoList(JsonArray jsonArray){}
    default void onResponseGetVideoUrl(getCourseDetailsRes getCourseDetailsRes){}
    default void onResponseSubmitAssessment(){}

    default void onResponsegetBranch(JsonArray jsonArray){}
    default void onResponsegetUnit(JsonArray jsonArray){}
    default void onResponseDashboardTrainer(getDashboardRes getDashboardRes){}
    default void onResponseGetUserList(JsonArray jsonArray){}
    default void onResponseAssignCourseRes(String string){}
    default void onResponseGetUserReportRes(JsonArray jsonArray){}
    default void onResponseGetCourseTrainerRes(JsonArray jsonArray){}
    default void onResponseGetAssessmentTrainerRes(JsonArray jsonArray){}
    default void onResponseSubmitFeedback(Object o){}
    default void onResponseChangeStatus(Object o){}


}
