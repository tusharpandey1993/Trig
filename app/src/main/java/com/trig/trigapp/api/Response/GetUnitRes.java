package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUnitRes {


    @Expose
    @SerializedName("UnitID")
    private int UnitID;
    @Expose
    @SerializedName("UnitName")
    private String UnitName;
    @Expose
    @SerializedName("BranchID")
    private int BranchID;

    public int getUnitID() {
        return UnitID;
    }

    public void setUnitID(int unitID) {
        UnitID = unitID;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }

    @Override
    public String toString() {
        return "{" +
                "UnitID=" + UnitID +
                ", UnitName='" + UnitName + '\'' +
                ", BranchID=" + BranchID +
                '}';
    }
}
