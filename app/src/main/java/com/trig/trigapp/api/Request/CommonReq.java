package com.trig.trigapp.api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonReq {

    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("BranchId")
    @Expose
    private String BranchId;

    @SerializedName("flag")
    @Expose
    private String flag;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String branchId) {
        BranchId = branchId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
