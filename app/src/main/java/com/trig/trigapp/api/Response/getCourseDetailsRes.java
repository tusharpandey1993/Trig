package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getCourseDetailsRes {

    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("course_detail")
    @Expose
    private String courseDetail;
    @SerializedName("Status")
    @Expose
    private Object status;
    @SerializedName("course_type")
    @Expose
    private String courseType;
    @SerializedName("course_type_id")
    @Expose
    private Object courseTypeId;
    @SerializedName("course_file")
    @Expose
    private String courseFile;
    @SerializedName("courseText")
    @Expose
    private String courseText;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("has_assessment")
    @Expose
    private Object hasAssessment;
    @SerializedName("is_deleted")
    @Expose
    private Object isDeleted;
    @SerializedName("created_date")
    @Expose
    private Object createdDate;
    @SerializedName("created_by")
    @Expose
    private Object createdBy;
    @SerializedName("course_total_duration")
    @Expose
    private Object courseTotalDuration;
    @SerializedName("course_topic")
    @Expose
    private Object courseTopic;
    @SerializedName("course_start_date")
    @Expose
    private Object courseStartDate;
    @SerializedName("course_assigned_date")
    @Expose
    private Object courseAssignedDate;
    @SerializedName("course_completed_date")
    @Expose
    private Object courseCompletedDate;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Object getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Object courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getCourseFile() {
        return courseFile;
    }

    public void setCourseFile(String courseFile) {
        this.courseFile = courseFile;
    }

    public String getCourseText() {
        return courseText;
    }

    public void setCourseText(String courseText) {
        this.courseText = courseText;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Object getHasAssessment() {
        return hasAssessment;
    }

    public void setHasAssessment(Object hasAssessment) {
        this.hasAssessment = hasAssessment;
    }

    public Object getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Object isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getCourseTotalDuration() {
        return courseTotalDuration;
    }

    public void setCourseTotalDuration(Object courseTotalDuration) {
        this.courseTotalDuration = courseTotalDuration;
    }

    public Object getCourseTopic() {
        return courseTopic;
    }

    public void setCourseTopic(Object courseTopic) {
        this.courseTopic = courseTopic;
    }

    public Object getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(Object courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public Object getCourseAssignedDate() {
        return courseAssignedDate;
    }

    public void setCourseAssignedDate(Object courseAssignedDate) {
        this.courseAssignedDate = courseAssignedDate;
    }

    public Object getCourseCompletedDate() {
        return courseCompletedDate;
    }

    public void setCourseCompletedDate(Object courseCompletedDate) {
        this.courseCompletedDate = courseCompletedDate;
    }

}
