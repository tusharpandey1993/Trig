package com.trig.trigapp.MVP;

import com.google.gson.JsonArray;
import com.trig.trigapp.api.Response.CommonResponse;
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
    default void onResponseProfile(getDashboardRes dashboardResponse){}
    default void onResponseCourseTopicList(JsonArray JsonArray){}
    default void onResponseFeedback(getFeedbackRes getFeedbackRes){}
    default void onResponseLoadAssessmentQuestions(JsonArray jsonElements){}
//    void callAPPListAPI(String userName, String miko){}
//    void onResponseUnsubscribeFeedbacks(getUnsubscribeFeedbacksRes response) {}
//    void subscribeFreeBundle(ASCommanResponse response) {}

}
