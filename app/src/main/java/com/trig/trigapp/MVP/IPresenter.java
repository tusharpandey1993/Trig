package com.trig.trigapp.MVP;

import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Response.LoginResponse;

import java.util.List;

public interface IPresenter {


    void showProgressDialog();
    void hideProgressDialog();
    void onError(String error);
    void onError(String error,int code);
    void onError(Object error);
    void onError(Object error,int Code);
    void onResponse(LoginResponse loginResponse);
//    void callAPPListAPI(String userName, String miko);
//    void onResponseUnsubscribeFeedbacks(getUnsubscribeFeedbacksRes response) ;
//    void subscribeFreeBundle(ASCommanResponse response) ;

}
