package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getDashboardRes {


    @Expose
    @SerializedName("assessmentCompleted")
    private int assessmentCompleted;
    @Expose
    @SerializedName("assessmentAssigned")
    private int assessmentAssigned;
    @Expose
    @SerializedName("courseCompleted")
    private int courseCompleted;
    @Expose
    @SerializedName("courseInprogress")
    private int courseInprogress;
    @Expose
    @SerializedName("courseAssigned")
    private int courseAssigned;

    public int getAssessmentCompleted() {
        return assessmentCompleted;
    }

    public void setAssessmentCompleted(int assessmentCompleted) {
        this.assessmentCompleted = assessmentCompleted;
    }

    public int getAssessmentAssigned() {
        return assessmentAssigned;
    }

    public void setAssessmentAssigned(int assessmentAssigned) {
        this.assessmentAssigned = assessmentAssigned;
    }

    public int getCourseCompleted() {
        return courseCompleted;
    }

    public void setCourseCompleted(int courseCompleted) {
        this.courseCompleted = courseCompleted;
    }

    public int getCourseInprogress() {
        return courseInprogress;
    }

    public void setCourseInprogress(int courseInprogress) {
        this.courseInprogress = courseInprogress;
    }

    public int getCourseAssigned() {
        return courseAssigned;
    }

    public void setCourseAssigned(int courseAssigned) {
        this.courseAssigned = courseAssigned;
    }

    @Override
    public String toString() {
        return "DashboardResponse{" +
                "assessmentCompleted=" + assessmentCompleted +
                ", assessmentAssigned=" + assessmentAssigned +
                ", courseCompleted=" + courseCompleted +
                ", courseInprogress=" + courseInprogress +
                ", courseAssigned=" + courseAssigned +
                '}';
    }
}
