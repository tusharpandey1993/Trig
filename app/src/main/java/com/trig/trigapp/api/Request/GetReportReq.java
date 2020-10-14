package com.trig.trigapp.api.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetReportReq {


    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("unitId")
    private int unitId;
    @Expose
    @SerializedName("emp_code")
    private String emp_code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }
}
