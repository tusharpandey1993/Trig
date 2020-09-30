package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getAssessmentListRes {

    @SerializedName("assestment_id")
    @Expose
    private Integer assestmentId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("assessment_name")
    @Expose
    private String assessmentName;
    @SerializedName("assigned_date")
    @Expose
    private String assignedDate;
    @SerializedName("assestment_completed_date")
    @Expose
    private String assestmentCompletedDate;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Assessment_id")
    @Expose
    private String Score;
    @SerializedName("Score")
    @Expose
    private Integer assessmentId;

    public Integer getAssestmentId() {
        return assestmentId;
    }

    public void setAssestmentId(Integer assestmentId) {
        this.assestmentId = assestmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getAssestmentCompletedDate() {
        return assestmentCompletedDate;
    }

    public void setAssestmentCompletedDate(String assestmentCompletedDate) {
        this.assestmentCompletedDate = assestmentCompletedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }
}
