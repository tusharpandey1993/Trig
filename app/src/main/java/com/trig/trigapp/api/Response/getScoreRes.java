package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getScoreRes {

    @SerializedName("assestment_id")
    @Expose
    private Integer assestmentId;
    @SerializedName("assessment_question_id")
    @Expose
    private Integer assessmentQuestionId;
    @SerializedName("question_text")
    @Expose
    private String questionText;
    @SerializedName("is_multiple")
    @Expose
    private String isMultiple;
    @SerializedName("option_id")
    @Expose
    private String optionId;
    @SerializedName("option_text")
    @Expose
    private String optionText;
    @SerializedName("SelectedOpts")
    @Expose
    private Object selectedOpts;
    @SerializedName("CorrectOpts")
    @Expose
    private Object correctOpts;

    public Integer getAssestmentId() {
        return assestmentId;
    }

    public void setAssestmentId(Integer assestmentId) {
        this.assestmentId = assestmentId;
    }

    public Integer getAssessmentQuestionId() {
        return assessmentQuestionId;
    }

    public void setAssessmentQuestionId(Integer assessmentQuestionId) {
        this.assessmentQuestionId = assessmentQuestionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(String isMultiple) {
        this.isMultiple = isMultiple;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Object getSelectedOpts() {
        return selectedOpts;
    }

    public void setSelectedOpts(Object selectedOpts) {
        this.selectedOpts = selectedOpts;
    }

    public Object getCorrectOpts() {
        return correctOpts;
    }

    public void setCorrectOpts(Object correctOpts) {
        this.correctOpts = correctOpts;
    }
}
