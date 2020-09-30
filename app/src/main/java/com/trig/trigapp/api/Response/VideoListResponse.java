package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoListResponse {


    @Expose
    @SerializedName("course_start_date")
    private String course_start_date;
    @Expose
    @SerializedName("course_total_duration")
    private String course_total_duration;
    @Expose
    @SerializedName("is_active")
    private String is_active;
    @Expose
    @SerializedName("course_type")
    private String course_type;
    @Expose
    @SerializedName("Status")
    private String Status;
    @Expose
    @SerializedName("course_detail")
    private String course_detail;
    @Expose
    @SerializedName("course_name")
    private String course_name;
    @Expose
    @SerializedName("course_id")
    private String course_id;

    public String getCourse_start_date() {
        return course_start_date;
    }

    public void setCourse_start_date(String course_start_date) {
        this.course_start_date = course_start_date;
    }

    public String getCourse_total_duration() {
        return course_total_duration;
    }

    public void setCourse_total_duration(String course_total_duration) {
        this.course_total_duration = course_total_duration;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCourse_detail() {
        return course_detail;
    }

    public void setCourse_detail(String course_detail) {
        this.course_detail = course_detail;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "{" +
                "course_start_date='" + course_start_date + '\'' +
                ", course_total_duration='" + course_total_duration + '\'' +
                ", is_active='" + is_active + '\'' +
                ", course_type='" + course_type + '\'' +
                ", Status='" + Status + '\'' +
                ", course_detail='" + course_detail + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_id='" + course_id + '\'' +
                '}';
    }
}
