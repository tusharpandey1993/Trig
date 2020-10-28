package com.trig.trigapp.api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeStatusReq {


    @Expose
    @SerializedName("user_id")
    private String user_id;
    @Expose
    @SerializedName("userName")
    private String userName;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
