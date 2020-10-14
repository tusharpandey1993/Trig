package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetReportRes {


    @Expose
    @SerializedName("trainer_branch_name")
    private String trainer_branch_name;
    @Expose
    @SerializedName("last_login")
    private String last_login;
    @Expose
    @SerializedName("is_active")
    private int is_active;
    @Expose
    @SerializedName("total_a_Count")
    private int total_a_Count;
    @Expose
    @SerializedName("total_course_Count")
    private int total_course_Count;
    @Expose
    @SerializedName("Unit")
    private String Unit;
    @Expose
    @SerializedName("emp_password")
    private String emp_password;
    @Expose
    @SerializedName("Tirg_EmpCode")
    private String Tirg_EmpCode;
    @Expose
    @SerializedName("FullName")
    private String FullName;
    @Expose
    @SerializedName("UserId")
    private int UserId;

    public String getTrainer_branch_name() {
        return trainer_branch_name;
    }

    public void setTrainer_branch_name(String trainer_branch_name) {
        this.trainer_branch_name = trainer_branch_name;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getTotal_a_Count() {
        return total_a_Count;
    }

    public void setTotal_a_Count(int total_a_Count) {
        this.total_a_Count = total_a_Count;
    }

    public int getTotal_course_Count() {
        return total_course_Count;
    }

    public void setTotal_course_Count(int total_course_Count) {
        this.total_course_Count = total_course_Count;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getEmp_password() {
        return emp_password;
    }

    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }

    public String getTirg_EmpCode() {
        return Tirg_EmpCode;
    }

    public void setTirg_EmpCode(String tirg_EmpCode) {
        Tirg_EmpCode = tirg_EmpCode;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    @Override
    public String toString() {
        return "{" +
                "trainer_branch_name='" + trainer_branch_name + '\'' +
                ", last_login='" + last_login + '\'' +
                ", is_active=" + is_active +
                ", total_a_Count=" + total_a_Count +
                ", total_course_Count=" + total_course_Count +
                ", Unit='" + Unit + '\'' +
                ", emp_password='" + emp_password + '\'' +
                ", Tirg_EmpCode='" + Tirg_EmpCode + '\'' +
                ", FullName='" + FullName + '\'' +
                ", UserId=" + UserId +
                '}';
    }
}
