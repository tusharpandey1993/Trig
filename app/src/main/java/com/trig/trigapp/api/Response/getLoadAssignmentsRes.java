package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getLoadAssignmentsRes {

    @SerializedName("assestment_id")
    @Expose
    public Integer assestmentId;
    @SerializedName("assessment_question_id")
    @Expose
    public Integer assessmentQuestionId;
    @SerializedName("question_text")
    @Expose
    public String questionText;
    @SerializedName("is_multiple")
    @Expose
    public String isMultiple;
    @SerializedName("option_id")
    @Expose
    public Object optionId;
    @SerializedName("option_text")
    @Expose
    public Object optionText;
    @SerializedName("SelectedOpts")
    @Expose
    public Object selectedOpts;
    @SerializedName("CorrectOpts")
    @Expose
    public Object correctOpts;
    @SerializedName("options")
    @Expose
    public List<Option> options = null;

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

    public Object getOptionId() {
        return optionId;
    }

    public void setOptionId(Object optionId) {
        this.optionId = optionId;
    }

    public Object getOptionText() {
        return optionText;
    }

    public void setOptionText(Object optionText) {
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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public class Option {

        @SerializedName("option_id")
        @Expose
        public String optionId;
        @SerializedName("option_text")
        @Expose
        public String optionText;

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
    }
}

