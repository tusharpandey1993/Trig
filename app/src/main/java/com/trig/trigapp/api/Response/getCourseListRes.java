package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class getCourseListRes {

    @Expose
    @SerializedName("completed")
    private int completed;
    @Expose
    @SerializedName("all")
    private int all;
    @Expose
    @SerializedName("topic_name")
    private String topic_name;
    @Expose
    @SerializedName("topic_id")
    private int topic_id;
    @Expose
    @SerializedName("user_id")
    private int user_id;
    @Expose
    @SerializedName("course_id")
    private int course_id;

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
