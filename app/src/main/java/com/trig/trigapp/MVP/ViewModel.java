package com.trig.trigapp.MVP;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Response.CourseListResponse;
import com.trig.trigapp.api.Response.DashboardResponse;
import com.trig.trigapp.api.Response.LoginResponse;
import com.trig.trigapp.api.Response.ProfileResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel {

    private final Context mContext;
    private final String TAG = "ViewModel";
    private final IPresenter iPresenter;

    public ViewModel(Context context, IPresenter iPresenter) {
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
                                Log.e(TAG, "onResponse:login " + new Gson().toJson(response.body()));
                            }
                        } else {
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:login " + new Gson().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    public void callProfileApi(String userId) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .callProfile(userId)
                .enqueue(new Callback<ProfileResponse>() {
                    @Override
                    public void onResponse(Call<ProfileResponse> call,
                                           Response<ProfileResponse> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
                                iPresenter.onResponseProfile(response.body());
                                Log.e(TAG, "onResponse:profile " + new Gson().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:profile " + new Gson().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ProfileResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }


    public void callDashboardApi() {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .callDashboard()
                .enqueue(new Callback<DashboardResponse>() {
                    @Override
                    public void onResponse(Call<DashboardResponse> call,
                                           Response<DashboardResponse> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
                                iPresenter.onResponseProfile(response.body());
                                Log.e(TAG, "onResponse:DashboardResponse " + new Gson().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:DashboardResponse " + new Gson().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DashboardResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    public void callCourses() {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getCourses()
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call,
                                           Response<JsonArray> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
                                iPresenter.onResponseCourseList(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + new Gson().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + new Gson().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

}
