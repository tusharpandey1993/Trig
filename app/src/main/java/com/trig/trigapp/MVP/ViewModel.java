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
import com.trig.trigapp.api.Response.getAssessmentListRes;
import com.trig.trigapp.api.Response.getCourseDetailsRes;
import com.trig.trigapp.api.Response.getCourseListRes;
import com.trig.trigapp.api.Response.getCourseTopicsRes;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.trig.trigapp.api.Response.getFeedbackRes;
import com.trig.trigapp.api.Response.getLoadAssignmentsRes;
import com.trig.trigapp.api.Response.getLoginRes;
import com.trig.trigapp.api.Response.getScoreRes;
import com.trig.trigapp.api.Response.CommonResponse;
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
                .enqueue(new Callback<getLoginRes>() {
                    @Override
                    public void onResponse(Call<getLoginRes> call,
                                           Response<getLoginRes> response) {
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
                    public void onFailure(Call<getLoginRes> call, Throwable t) {
                        iPresenter.onError(t.getCause());
                        Log.e(TAG, "onFailure: callLoginApi" + t.getCause());
                    }
                });
    }



    public void getDashboard(String userId) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getDashboard(new CommonReq(userId))
                .enqueue(new Callback<getDashboardRes>() {
                    @Override
                    public void onResponse(Call<getDashboardRes> call,
                                           Response<getDashboardRes> response) {
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
                    public void onFailure(Call<getDashboardRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: getDashboard" + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    public void callCourses(Integer courseId) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getCourseList(new getCourseDetailsReq("",courseId))
                .enqueue(new Callback<getCourseListRes>() {
                    @Override
                    public void onResponse(Call<getCourseListRes> call,
                                           Response<getCourseListRes> response) {
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
                    public void onFailure(Call<getCourseListRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: callCourses" + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }


    public void callgetCourseDetails() {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getCourseDetails(new CommonReq(""))
                .enqueue(new Callback<getCourseDetailsRes>() {
                    @Override
                    public void onResponse(Call<getCourseDetailsRes> call,
                                           Response<getCourseDetailsRes> response) {
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
                    public void onFailure(Call<getCourseDetailsRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: callgetCourseDetails" + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    public void callgetCourseTopics() {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getCourseTopics(new CommonReq(""))
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
                        Log.e(TAG, "onFailure: callgetCourseTopics" + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    public void callgetFeedback(String userId) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getFeedback(new CommonReq(userId))
                .enqueue(new Callback<getFeedbackRes>() {
                    @Override
                    public void onResponse(Call<getFeedbackRes> call,
                                           Response<getFeedbackRes> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
                                iPresenter.onResponseFeedback(response.body());
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
                    public void onFailure(Call<getFeedbackRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: callgetFeedback" + t.getCause());
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
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call,
                                           Response<CommonResponse> response) {
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
                    public void onFailure(Call<CommonResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: callProfileApi" + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }


    public void callgetAssessmentList() {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getAssessmentList(new CommonReq(""))
                .enqueue(new Callback<getAssessmentListRes>() {
                    @Override
                    public void onResponse(Call<getAssessmentListRes> call,
                                           Response<getAssessmentListRes> response) {
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
                    public void onFailure(Call<getAssessmentListRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: callgetAssessmentList" + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

 public void callgetScore(Integer assessmentId) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getScore(new LoadAssignmentsReq(assessmentId,""))
                .enqueue(new Callback<getScoreRes>() {
                    @Override
                    public void onResponse(Call<getScoreRes> call,
                                           Response<getScoreRes> response) {
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
                    public void onFailure(Call<getScoreRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: callgetScore" + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    //flag = ATTEMPT and   flag = REVIEW

    public void callLoadAssessment(Integer assessmentId, String user_id,String flag) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .loadAssignments(new LoadAssignmentsReq(assessmentId, user_id, flag))
                .enqueue(new Callback<getLoadAssignmentsRes>() {
                    @Override
                    public void onResponse(Call<getLoadAssignmentsRes> call,
                                           Response<getLoadAssignmentsRes> response) {
                        Log.d(TAG, "onResponse: 1 " + response.body());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: 2 " + response.body());
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse: 3 " + response.body());
                                iPresenter.onResponseLoadAssessmentQuestions(response.body());
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
                    public void onFailure(Call<getLoadAssignmentsRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: callLoadAssessment" + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

}
