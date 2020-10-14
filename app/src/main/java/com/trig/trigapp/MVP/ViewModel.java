package com.trig.trigapp.MVP;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.api.Request.AssignCoursesToEmp;
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Request.GetProfileDetailsReq;
import com.trig.trigapp.api.Request.LoadAssignmentsReq;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Request.SubmitAssessmentReq;
import com.trig.trigapp.api.Request.TrainerDashboardReq;
import com.trig.trigapp.api.Request.User_id;
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

    // Login
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
                                Log.e(TAG, "onResponse:login " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:login " + Utility.getInstance().getG().toJson(response.errorBody()));
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


    // Dashboard 1st and 2nd card details
    public void getDashboard(CommonReq commonReq){
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getDashboard(commonReq)
                .enqueue(new Callback<getDashboardRes>() {
                    @Override
                    public void onResponse(Call<getDashboardRes> call,
                                           Response<getDashboardRes> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:getDashboard 3 " + response.body());
                                iPresenter.onResponseGetDashboard(response.body());
                                Log.e(TAG, "onResponse:DashboardResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:DashboardResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
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

    // List of vidoes
    public void callCourses(getCourseDetailsReq getCourseDetailsReq) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getCourseList(getCourseDetailsReq)
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call,
                                           Response<JsonArray> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:callCourses 3 " + response.body());
                                iPresenter.onResponseVideoList(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse:callCourses 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:callCourses " + Utility.getInstance().getG().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e(TAG, "onFailure: callCourses " + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }


    // To get Video URL
    public void callgetCourseDetails(getCourseDetailsReq getCourseDetailsReq) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getCourseDetails(getCourseDetailsReq)
                .enqueue(new Callback<getCourseDetailsRes>() {
                    @Override
                    public void onResponse(Call<getCourseDetailsRes> call,
                                           Response<getCourseDetailsRes> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:callgetCourseDetails 3 " + response.body());
                                iPresenter.onResponseGetVideoUrl(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
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

    // To get Grid Cards data
    public void callgetCourseTopics(CommonReq commonReq) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getCourseTopics(commonReq)
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call,
                                           Response<JsonArray> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:callgetCourseTopics 3 " + response.body());
                                iPresenter.onResponseCourseTopicList(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
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

    // To get Feedback From Trainer
    public void callgetFeedback(CommonReq commonReq) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getFeedback(commonReq)
                .enqueue(new Callback<getFeedbackRes>() {
                    @Override
                    public void onResponse(Call<getFeedbackRes> call,
                                           Response<getFeedbackRes> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:callgetFeedback 3 " + response.body());
                                iPresenter.onResponseFeedback(response.body());
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
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

    // To get Profile details
    public void callProfileApi(String userId, String type) {

        Utility.getInstance().createServiceForTrigApp(mContext)
                .getProfile(new GetProfileDetailsReq(userId,type))
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call,
                                           Response<CommonResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:callProfileApi 3 " + response.body());
                                iPresenter.onResponseProfile(response.body());
                                Log.e(TAG, "onResponse:profile " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:profile " + Utility.getInstance().getG().toJson(response.errorBody()));
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


    // For Dashboard: show Report
    public void callgetAssessmentList(String userID) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getAssessmentList(new User_id(userID))
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call,
                                           Response<JsonArray> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:callgetAssessmentList 3 " + response.body());
                                iPresenter.onResponseAssessmentTopicList(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e(TAG, "onFailure: callgetAssessmentList" + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    // Dashboard Report
 public void callgetScore(Integer assessmentId) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getScore(new LoadAssignmentsReq(assessmentId,""))
                .enqueue(new Callback<getScoreRes>() {
                    @Override
                    public void onResponse(Call<getScoreRes> call,
                                           Response<getScoreRes> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:callgetScore 3 " + response.body());
//                                iPresenter.onResponseCourseList(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
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
    // For Assessment Quiz and Result
    public void callLoadAssessment(Integer assessmentId, String user_id,String flag) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .loadAssignments(new LoadAssignmentsReq(assessmentId, user_id, flag))
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call,
                                           Response<JsonArray> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:callLoadAssessment 3 " + response.body());
                                iPresenter.onResponseLoadAssessmentQuestions(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e(TAG, "onFailure: callLoadAssessment" + t.getCause());
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    // This is submit button Api hit in assessment
    public void submitAssessment(SubmitAssessmentReq submitAssessmentReq) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .submitAssessment(submitAssessmentReq)
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call,
                                           Response<JsonArray> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:submitAssessment 3 " + response.body());
                                iPresenter.onResponseSubmitAssessment();
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e(TAG, "onFailure: callLoadAssessment" + t.getCause());
                        iPresenter.onResponseSubmitAssessment();
                        iPresenter.onError(t.getCause());
                    }
                });
    }


    // This is to get Trainer Branch List
    public void getBranch(User_id user_id) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getBranch(user_id)
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call,
                                           Response<JsonArray> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:getBranch 3 " + response.body());
                                iPresenter.onResponsegetBranch(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e(TAG, "onFailure: callLoadAssessment" + t.getCause());
                        iPresenter.onResponseSubmitAssessment();
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    // This is to get Trainer Unit List
    public void getUnit(CommonReq commonReq) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getUnit(commonReq)
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:getUnit " + response.body());
                                iPresenter.onResponsegetUnit(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e(TAG, "onFailure: callLoadAssessment" + t.getCause());
                        iPresenter.onResponseSubmitAssessment();
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    // This is to get Dashboard Trainer from selecting a Unit
    public void getTrainerDashboard(TrainerDashboardReq trainerDashboardReq) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getTainerDashboard(trainerDashboardReq)
                .enqueue(new Callback<getDashboardRes>() {
                    @Override
                    public void onResponse(Call<getDashboardRes> call,
                                           Response<getDashboardRes> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:getTrainerDashboard 3 " + response.body());
                                iPresenter.onResponseDashboardTrainer(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<getDashboardRes> call, Throwable t) {
                        Log.e(TAG, "onFailure: callLoadAssessment" + t.getCause());
                        iPresenter.onResponseSubmitAssessment();
                        iPresenter.onError(t.getCause());
                    }
                });
    }

    // This is to get User list after selecting UNIT
    public void getUserList(TrainerDashboardReq trainerDashboardReq) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .getUserList(trainerDashboardReq)
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call,
                                           Response<JsonArray> response) {
                        if (response.isSuccessful()) {

                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:getUserList 3 " + response.body());
                                iPresenter.onResponseGetUserList(response.body());
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e(TAG, "onFailure: callLoadAssessment" + t.getCause());
                        iPresenter.onResponseSubmitAssessment();
                        iPresenter.onError(t.getCause());
                    }
                });
    }

// This is to get Assign Courses to Employess (Specific to 1 and all options)
    public void assignCourseTrainerToEmp(AssignCoursesToEmp assignCoursesToEmp) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .assignCourseTrainerToEmp(assignCoursesToEmp)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call,
                                           Response<String> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.d(TAG, "onResponse:assignCourseTrainerToEmp 3 " + response.body());
                                iPresenter.onResponseAssignCourseRes(response.body());
//                                Log.e(TAG, "onResponse:CourseListResponse " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            Log.d(TAG, "onResponse: 4 errorBody " + response.errorBody());
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:CourseListResponse " + Utility.getInstance().getG().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e(TAG, "onFailure: callLoadAssessment" + t.getCause());
                        iPresenter.onResponseSubmitAssessment();
                        iPresenter.onError(t.getCause());
                    }
                });
    }

}
