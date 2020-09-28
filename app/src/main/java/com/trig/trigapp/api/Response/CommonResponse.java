package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonResponse {


    @Expose
    @SerializedName("branch")
    private String branch;
    @Expose
    @SerializedName("emp_photo_path")
    private String emp_photo_path;
    @Expose
    @SerializedName("age")
    private String age;
    @Expose
    @SerializedName("designation")
    private String designation;
    @Expose
    @SerializedName("mobileNo")
    private String mobileNo;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("doj")
    private String doj;
    @Expose
    @SerializedName("dob")
    private String dob;
    @Expose
    @SerializedName("emailId")
    private String emailId;
    @Expose
    @SerializedName("gender")
    private String gender;
    @Expose
    @SerializedName("isTrainerPlusUser")
    private String isTrainerPlusUser;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("userid")
    private String userid;
    @Expose
    @SerializedName("IsActive")
    private String IsActive;
    @Expose
    @SerializedName("TirgEmpCode")
    private String TirgEmpCode;
    @Expose
    @SerializedName("UserType")
    private String UserType;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmp_photo_path() {
        return emp_photo_path;
    }

    public void setEmp_photo_path(String emp_photo_path) {
        this.emp_photo_path = emp_photo_path;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsTrainerPlusUser() {
        return isTrainerPlusUser;
    }

    public void setIsTrainerPlusUser(String isTrainerPlusUser) {
        this.isTrainerPlusUser = isTrainerPlusUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String isActive) {
        IsActive = isActive;
    }

    public String getTirgEmpCode() {
        return TirgEmpCode;
    }

    public void setTirgEmpCode(String tirgEmpCode) {
        TirgEmpCode = tirgEmpCode;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }


    @Override
    public String toString() {
        return "{" +
                "branch='" + branch + '\'' +
                ", emp_photo_path='" + emp_photo_path + '\'' +
                ", age='" + age + '\'' +
                ", designation='" + designation + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", address='" + address + '\'' +
                ", doj='" + doj + '\'' +
                ", dob='" + dob + '\'' +
                ", emailId='" + emailId + '\'' +
                ", gender='" + gender + '\'' +
                ", isTrainerPlusUser='" + isTrainerPlusUser + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", userid='" + userid + '\'' +
                ", IsActive='" + IsActive + '\'' +
                ", TirgEmpCode='" + TirgEmpCode + '\'' +
                ", UserType='" + UserType + '\'' +
                '}';
    }
}
