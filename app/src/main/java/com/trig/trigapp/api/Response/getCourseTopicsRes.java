package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getCourseTopicsRes {

    @SerializedName("course_id")
    @Expose
    private Integer courseId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("topic_id")
    @Expose
    private Integer topicId;
    @SerializedName("topic_name")
    @Expose
    private String topicName;
    @SerializedName("all")
    @Expose
    private Integer all;
    @SerializedName("completed")
    @Expose
    private Integer completed;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

}
