package com.trig.trigapp.api;

import com.google.gson.JsonArray;
import com.trig.trigapp.api.Request.AssessmentTrainerFromReportReq;
import com.trig.trigapp.api.Request.AssignCoursesToEmp;
import com.trig.trigapp.api.Request.ChangeStatusReq;
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Request.GetReportReq;
import com.trig.trigapp.api.Request.LoadAssignmentsReq;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Request.GetProfileDetailsReq;
import com.trig.trigapp.api.Request.SubmitAssessmentReq;
import com.trig.trigapp.api.Request.SubmitFeedback;
import com.trig.trigapp.api.Request.TrainerDashboardReq;
import com.trig.trigapp.api.Request.User_id;
import com.trig.trigapp.api.Request.getCourseDetailsReq;
import com.trig.trigapp.api.Response.CommonResponse;
import com.trig.trigapp.api.Response.getCourseDetailsRes;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.trig.trigapp.api.Response.getFeedbackRes;
import com.trig.trigapp.api.Response.getLoginRes;
import com.trig.trigapp.api.Response.getScoreRes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {


    @POST("login/login")
    Call<getLoginRes> callLogin(@Body LoginRequest loginRequest);

    @POST("UserDashBoard/getdashboard")
    Call<getDashboardRes> getDashboard(@Body CommonReq req);

    @POST("Course/GetCourseList")
    Call<JsonArray> getCourseList(@Body getCourseDetailsReq req);

    @POST("CourseDetails/GetCourseDetails")
    Call<getCourseDetailsRes> getCourseDetails(@Body getCourseDetailsReq req);

    @POST("Course/GetCourseTopics")
    Call<JsonArray> getCourseTopics(@Body CommonReq req);

    @POST("UserProfile/GetFeedback")
    Call<getFeedbackRes> getFeedback(@Body CommonReq req);

    @POST("UserProfile/GetProfile")
    Call<CommonResponse> getProfile(@Body GetProfileDetailsReq req);

    @POST("UserAssessment/getAssessmentList")
    Call<JsonArray> getAssessmentList(@Body User_id req);

    @POST("SubmitAssessment/getScore")
    Call<getScoreRes> getScore(@Body LoadAssignmentsReq req);

    @POST("UserAssessment/LoadAssessment")
    Call<JsonArray> loadAssignments(@Body LoadAssignmentsReq req);


    @POST("SubmitAssessment/save")
    Call<JsonArray> submitAssessment(@Body SubmitAssessmentReq submitAssessmentReq);

    @POST("TrainerUnit/GetBranch")
    Call<JsonArray> getBranch(@Body  User_id req);

    @POST("TrainerUnit/GetUnit")
    Call<JsonArray> getUnit(@Body  CommonReq commonReq);

    // Dashboard Trainer
    @POST("TrainerDashBoard/GetDashBoard")
    Call<getDashboardRes> getTainerDashboard(@Body TrainerDashboardReq trainerDashboardReq);

    // Assign Course Trainer
    @POST("Trainer/GetUserList")
    Call<JsonArray> getUserList(@Body TrainerDashboardReq trainerDashboardReq);

    // Assign Course To Employees
    @POST("Trainer/AssignToEmployee")
    Call<String> assignCourseTrainerToEmp(@Body AssignCoursesToEmp assignCoursesToEmp);

    // Get Report
    @POST("TrainerReport/GetReport")
    Call<JsonArray> getUserReport(@Body GetReportReq getReportReq);

    // Get Course List From Report
    @POST("GetUserCourse/GetCourse")
    Call<JsonArray> getCourseTrainer(@Body CommonReq commonReq);

    // Get Assessment List From Report
    @POST("UserAssessment/getAssessmentList ")
    Call<JsonArray> getAssessmentTrainer(@Body AssessmentTrainerFromReportReq assessmentTrainerFromReportReq);

    // Submit Feedback
    @POST("TrainerReport/SubmitFeedback")
    Call<String> submitFeedback(@Body SubmitFeedback submitFeedback);

    // Submit Feedback
    @POST("TrainerReport/ChangeStatus")
    Call<String> changeStatus(@Body ChangeStatusReq changeStatusReq);

   }