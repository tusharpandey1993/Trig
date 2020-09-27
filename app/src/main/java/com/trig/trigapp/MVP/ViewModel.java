package com.trig.trigapp.MVP;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Request.GetProfileDetailsReq;
import com.trig.trigapp.api.Request.LoadAssignmentsReq;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Request.getCourseDetailsReq;
import com.trig.trigapp.api.Response.DashboardResponse;
import com.trig.trigapp.api.Response.LoadAssignmentsRes;
import com.trig.trigapp.api.Response.LoginResponse;
import com.trig.trigapp.api.Response.ProfileResponse;
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



    public void callDashboardApi(String userId) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getDashboard(new CommonReq(userId))
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

    public void callCourses(Integer courseId) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getCourseList(new getCourseDetailsReq("",courseId))
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


    public void callgetCourseDetails() {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getCourseDetails(new CommonReq(""))
                .enqueue(new Callback<LoadAssignmentsRes>() {
                    @Override
                    public void onResponse(Call<LoadAssignmentsRes> call,
                                           Response<LoadAssignmentsRes> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
//                                iPresenter.onResponseCourseList(response.body());
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
                    public void onFailure(Call<LoadAssignmentsRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    public void callgetCourseTopics() {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getCourseTopics(new CommonReq(""))
                .enqueue(new Callback<LoadAssignmentsRes>() {
                    @Override
                    public void onResponse(Call<LoadAssignmentsRes> call,
                                           Response<LoadAssignmentsRes> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
//                                iPresenter.onResponseCourseList(response.body());
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
                    public void onFailure(Call<LoadAssignmentsRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    public void callgetFeedback() {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getFeedback(new CommonReq(""))
                .enqueue(new Callback<LoadAssignmentsRes>() {
                    @Override
                    public void onResponse(Call<LoadAssignmentsRes> call,
                                           Response<LoadAssignmentsRes> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
//                                iPresenter.onResponseCourseList(response.body());
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
                    public void onFailure(Call<LoadAssignmentsRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

//    """userid"":""9919"",
//""profile"":""user"""
//
//            """userid"":""9919"",
//""profile"":""trainer"""
//
//            """userid"":""9919"",
//""profile"":""getbranch"""

    public void callProfileApi(String userId, String type) {

        Utility.getInstance().createServiceForTrigApp(mContext)
                .getProfile(new GetProfileDetailsReq(userId,type))
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


    public void callgetAssessmentList() {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getAssessmentList(new CommonReq(""))
                .enqueue(new Callback<LoadAssignmentsRes>() {
                    @Override
                    public void onResponse(Call<LoadAssignmentsRes> call,
                                           Response<LoadAssignmentsRes> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
//                                iPresenter.onResponseCourseList(response.body());
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
                    public void onFailure(Call<LoadAssignmentsRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

 public void callgetScore(Integer assessmentId) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getScore(new LoadAssignmentsReq(assessmentId,""))
                .enqueue(new Callback<LoadAssignmentsRes>() {
                    @Override
                    public void onResponse(Call<LoadAssignmentsRes> call,
                                           Response<LoadAssignmentsRes> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
//                                iPresenter.onResponseCourseList(response.body());
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
                    public void onFailure(Call<LoadAssignmentsRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    //flag = ATTEMPT and   flag = REVIEW

    public void callLoadAssessment(Integer assessmentId,String flag) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .loadAssignments(new LoadAssignmentsReq(assessmentId,"",flag))
                .enqueue(new Callback<LoadAssignmentsRes>() {
                    @Override
                    public void onResponse(Call<LoadAssignmentsRes> call,
                                           Response<LoadAssignmentsRes> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
//                                iPresenter.onResponseCourseList(response.body());
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
                    public void onFailure(Call<LoadAssignmentsRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

}
