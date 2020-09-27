package com.trig.trigapp.api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadAssignmentsReq {

    @SerializedName("Assessment_id")
    @Expose
    private Integer assessmentId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("Flag")
    @Expose
    private String flag;

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public LoadAssignmentsReq(Integer assessmentId, String userId, String flag) {
        this.assessmentId = assessmentId;
        this.userId = userId;
        this.flag = flag;
    }

    public LoadAssignmentsReq(Integer assessmentId, String userId) {
        this.assessmentId = assessmentId;
        this.userId = userId;
    }
}
