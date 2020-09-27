package com.trig.trigapp.api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getCourseDetailsReq {


        @SerializedName("user_id")
        @Expose
        private String userid;
        @SerializedName("course_id")
        @Expose
        private Integer course_id;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public getCourseDetailsReq(String userid, Integer course_id) {
        this.userid = userid;
        this.course_id = course_id;
    }
}
