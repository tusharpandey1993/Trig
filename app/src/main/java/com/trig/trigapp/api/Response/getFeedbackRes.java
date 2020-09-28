package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getFeedbackRes {

    @SerializedName("userid")
    @Expose
    private Object userid;
    @SerializedName("feedback")
    @Expose
    private String feedback;
    @SerializedName("remarks_suggestion")
    @Expose
    private String remarksSuggestion;
    @SerializedName("FeedbackBy")
    @Expose
    private String feedbackBy;
    @SerializedName("FeedbackOn")
    @Expose
    private String feedbackOn;

    public Object getUserid() {
        return userid;
    }

    public void setUserid(Object userid) {
        this.userid = userid;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getRemarksSuggestion() {
        return remarksSuggestion;
    }

    public void setRemarksSuggestion(String remarksSuggestion) {
        this.remarksSuggestion = remarksSuggestion;
    }

    public String getFeedbackBy() {
        return feedbackBy;
    }

    public void setFeedbackBy(String feedbackBy) {
        this.feedbackBy = feedbackBy;
    }

    public String getFeedbackOn() {
        return feedbackOn;
    }

    public void setFeedbackOn(String feedbackOn) {
        this.feedbackOn = feedbackOn;
    }

}
