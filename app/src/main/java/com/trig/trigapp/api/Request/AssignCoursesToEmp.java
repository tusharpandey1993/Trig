package com.trig.trigapp.api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignCoursesToEmp {


    @Expose
    @SerializedName("trainer_emp_code")
    private long trainer_emp_code;
    @Expose
    @SerializedName("course")
    private String course;
    @Expose
    @SerializedName("assessment")
    private String assessment;
    @Expose
    @SerializedName("userName")
    private String userName;

    public long getTrainer_emp_code() {
        return trainer_emp_code;
    }

    public void setTrainer_emp_code(long trainer_emp_code) {
        this.trainer_emp_code = trainer_emp_code;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
