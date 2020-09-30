package com.trig.trigapp.api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitAssessmentReq {


    @Expose
    @SerializedName("optString")
    private String optString;
    @Expose
    @SerializedName("user_id")
    private String user_id;
    @Expose
    @SerializedName("Assessment_id")
    private int Assessment_id;

    public String getOptString() {
        return optString;
    }

    public void setOptString(String optString) {
        this.optString = optString;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getAssessment_id() {
        return Assessment_id;
    }

    public void setAssessment_id(int assessment_id) {
        Assessment_id = assessment_id;
    }
}
