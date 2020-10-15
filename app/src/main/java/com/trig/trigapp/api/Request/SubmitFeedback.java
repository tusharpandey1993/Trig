package com.trig.trigapp.api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitFeedback {


    @Expose
    @SerializedName("suggestion")
    private String suggestion;
    @Expose
    @SerializedName("feedback_text")
    private String feedback_text;
    @Expose
    @SerializedName("to_id")
    private int to_id;
    @Expose
    @SerializedName("feedback_id")
    private int feedback_id;
    @Expose
    @SerializedName("user_id")
    private int user_id;

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getFeedback_text() {
        return feedback_text;
    }

    public void setFeedback_text(String feedback_text) {
        this.feedback_text = feedback_text;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
