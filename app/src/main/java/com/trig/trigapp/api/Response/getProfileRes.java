package com.trig.trigapp.api.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getProfileRes {

    @SerializedName("UserType")
    @Expose
    private Object userType;
    @SerializedName("TirgEmpCode")
    @Expose
    private String tirgEmpCode;
    @SerializedName("IsActive")
    @Expose
    private Object isActive;
    @SerializedName("userid")
    @Expose
    private Object userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("isTrainerPlusUser")
    @Expose
    private Object isTrainerPlusUser;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("doj")
    @Expose
    private String doj;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("emp_photo_path")
    @Expose
    private String empPhotoPath;
    @SerializedName("branch")
    @Expose
    private Object branch;

    public Object getUserType() {
        return userType;
    }

    public void setUserType(Object userType) {
        this.userType = userType;
    }

    public String getTirgEmpCode() {
        return tirgEmpCode;
    }

    public void setTirgEmpCode(String tirgEmpCode) {
        this.tirgEmpCode = tirgEmpCode;
    }

    public Object getIsActive() {
        return isActive;
    }

    public void setIsActive(Object isActive) {
        this.isActive = isActive;
    }

    public Object getUserid() {
        return userid;
    }

    public void setUserid(Object userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getIsTrainerPlusUser() {
        return isTrainerPlusUser;
    }

    public void setIsTrainerPlusUser(Object isTrainerPlusUser) {
        this.isTrainerPlusUser = isTrainerPlusUser;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmpPhotoPath() {
        return empPhotoPath;
    }

    public void setEmpPhotoPath(String empPhotoPath) {
        this.empPhotoPath = empPhotoPath;
    }

    public Object getBranch() {
        return branch;
    }

    public void setBranch(Object branch) {
        this.branch = branch;
    }
}
