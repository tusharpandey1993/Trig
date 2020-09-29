package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getCourseDetailsRes {


    @Expose
    @SerializedName("course_completed_date")
    private String course_completed_date;
    @Expose
    @SerializedName("course_assigned_date")
    private String course_assigned_date;
    @Expose
    @SerializedName("course_start_date")
    private String course_start_date;
    @Expose
    @SerializedName("course_topic")
    private String course_topic;
    @Expose
    @SerializedName("course_total_duration")
    private String course_total_duration;
    @Expose
    @SerializedName("created_by")
    private String created_by;
    @Expose
    @SerializedName("created_date")
    private String created_date;
    @Expose
    @SerializedName("is_deleted")
    private String is_deleted;
    @Expose
    @SerializedName("has_assessment")
    private String has_assessment;
    @Expose
    @SerializedName("is_active")
    private String is_active;
    @Expose
    @SerializedName("courseText")
    private String courseText;
    @Expose
    @SerializedName("course_file")
    private String course_file;
    @Expose
    @SerializedName("course_type_id")
    private String course_type_id;
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

    public String getCourse_completed_date() {
        return course_completed_date;
    }

    public void setCourse_completed_date(String course_completed_date) {
        this.course_completed_date = course_completed_date;
    }

    public String getCourse_assigned_date() {
        return course_assigned_date;
    }

    public void setCourse_assigned_date(String course_assigned_date) {
        this.course_assigned_date = course_assigned_date;
    }

    public String getCourse_start_date() {
        return course_start_date;
    }

    public void setCourse_start_date(String course_start_date) {
        this.course_start_date = course_start_date;
    }

    public String getCourse_topic() {
        return course_topic;
    }

    public void setCourse_topic(String course_topic) {
        this.course_topic = course_topic;
    }

    public String getCourse_total_duration() {
        return course_total_duration;
    }

    public void setCourse_total_duration(String course_total_duration) {
        this.course_total_duration = course_total_duration;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getHas_assessment() {
        return has_assessment;
    }

    public void setHas_assessment(String has_assessment) {
        this.has_assessment = has_assessment;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getCourseText() {
        return courseText;
    }

    public void setCourseText(String courseText) {
        this.courseText = courseText;
    }

    public String getCourse_file() {
        return course_file;
    }

    public void setCourse_file(String course_file) {
        this.course_file = course_file;
    }

    public String getCourse_type_id() {
        return course_type_id;
    }

    public void setCourse_type_id(String course_type_id) {
        this.course_type_id = course_type_id;
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
                "course_completed_date='" + course_completed_date + '\'' +
                ", course_assigned_date='" + course_assigned_date + '\'' +
                ", course_start_date='" + course_start_date + '\'' +
                ", course_topic='" + course_topic + '\'' +
                ", course_total_duration='" + course_total_duration + '\'' +
                ", created_by='" + created_by + '\'' +
                ", created_date='" + created_date + '\'' +
                ", is_deleted='" + is_deleted + '\'' +
                ", has_assessment='" + has_assessment + '\'' +
                ", is_active='" + is_active + '\'' +
                ", courseText='" + courseText + '\'' +
                ", course_file='" + course_file + '\'' +
                ", course_type_id='" + course_type_id + '\'' +
                ", course_type='" + course_type + '\'' +
                ", Status='" + Status + '\'' +
                ", course_detail='" + course_detail + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_id='" + course_id + '\'' +
                '}';
    }
}
