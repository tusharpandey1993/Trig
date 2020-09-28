package com.trig.trigapp.MVP;

import com.google.gson.JsonArray;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.trig.trigapp.api.Response.getLoginRes;
import com.trig.trigapp.api.Response.ProfileResponse;

public interface IPresenter {


    void showProgressDialog();
    void hideProgressDialog();
    void onError(String error);
    void onError(String error,int code);
    void onError(Object error);
    void onError(Object error,int Code);
    void onResponse(getLoginRes loginResponse);
    void onResponseProfile(ProfileResponse profileResponse);
    void onResponseProfile(getDashboardRes dashboardResponse);
    void onResponseCourseList(JsonArray JsonArray);
//    void callAPPListAPI(String userName, String miko);
//    void onResponseUnsubscribeFeedbacks(getUnsubscribeFeedbacksRes response) ;
//    void subscribeFreeBundle(ASCommanResponse response) ;

}
