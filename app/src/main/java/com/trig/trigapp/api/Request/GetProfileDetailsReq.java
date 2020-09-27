package com.trig.trigapp.api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProfileDetailsReq {

    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("profile")
    @Expose
    private String profile;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public GetProfileDetailsReq(String userid, String profile) {
        this.userid = userid;
        this.profile = profile;
    }

    public GetProfileDetailsReq(String userid) {
        this.userid = userid;
    }
}