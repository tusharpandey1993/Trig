package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class CourseListResponse {

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
}
