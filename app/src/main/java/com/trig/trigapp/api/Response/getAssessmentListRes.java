package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getAssessmentListRes {


    @Expose
    @SerializedName("Assessment_id")
    private int Assessment_id_Capital;
    @Expose
    @SerializedName("Score")
    private String Score;
    @Expose
    @SerializedName("Status")
    private String Status;
    @Expose
    @SerializedName("assestment_completed_date")
    private String assestment_completed_date;
    @Expose
    @SerializedName("assigned_date")
    private String assigned_date;
    @Expose
    @SerializedName("assessment_name")
    private String assessment_name;
    @Expose
    @SerializedName("is_active")
    private int is_active;
    @Expose
    @SerializedName("user_id")
    private int user_id;
    @Expose
    @SerializedName("assestment_id")
    private int assestment_id;


    public int getAssessment_id_Capital() {
        return Assessment_id_Capital;
    }

    public void setAssessment_id_Capital(int assessment_id_Capital) {
        Assessment_id_Capital = assessment_id_Capital;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAssestment_completed_date() {
        return assestment_completed_date;
    }

    public void setAssestment_completed_date(String assestment_completed_date) {
        this.assestment_completed_date = assestment_completed_date;
    }

    public String getAssigned_date() {
        return assigned_date;
    }

    public void setAssigned_date(String assigned_date) {
        this.assigned_date = assigned_date;
    }

    public String getAssessment_name() {
        return assessment_name;
    }

    public void setAssessment_name(String assessment_name) {
        this.assessment_name = assessment_name;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAssestment_id() {
        return assestment_id;
    }

    public void setAssestment_id(int assestment_id) {
        this.assestment_id = assestment_id;
    }
}
