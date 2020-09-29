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

}

class Option {

    @SerializedName("option_id")
    @Expose
    public String optionId;
    @SerializedName("option_text")
    @Expose
    public String optionText;
}
