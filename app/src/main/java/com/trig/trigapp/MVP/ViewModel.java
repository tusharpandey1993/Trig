package com.trig.trigapp.MVP;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel {

    private Context mContext;
    private String  TAG="ViewModel";
    private IPresenter iPresenter;

    public ViewModel(Context context,IPresenter iPresenter) {
        this.mContext = context;
        this.iPresenter = iPresenter;
    }

    public void callLoginApi(LoginRequest request) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .callLogin(request)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call,
                                           Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                iPresenter.onResponse(response.body());
                                Log.e(TAG, "onResponse:login "+new Gson().toJson(response.body()));
                            }
                        }else {
                            if(response.errorBody()!=null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:login "+new Gson().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
    }

}
