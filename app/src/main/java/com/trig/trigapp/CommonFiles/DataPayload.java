package com.trig.trigapp.CommonFiles;

import com.trig.trigapp.api.Response.GetBranchRes;
import com.trig.trigapp.api.Response.GetUnitRes;
import com.trig.trigapp.api.Response.UserListResponse;

import java.util.ArrayList;

public class DataPayload {

    public String title;
    public GetUnitRes getUnitRes;
    private ArrayList<GetUnitRes> getUnitResArrayList = new ArrayList<>();
    private ArrayList<GetBranchRes> getBranchResArrayList = new ArrayList<>();
    private ArrayList<UserListResponse> userListResponses = new ArrayList<>();
    private ArrayList<String> dataList = new ArrayList<>();
    private ArrayList<String> filterList = new ArrayList<>();

    public ArrayList<UserListResponse> getUserListResponses() {
        return userListResponses;
    }

    public void setUserListResponses(ArrayList<UserListResponse> userListResponses) {
        this.userListResponses = userListResponses;
    }

    public void setDataList(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    public ArrayList<String> getDataList() {
        return dataList;
    }

    public ArrayList<String> getFilterList() {
        return filterList;
    }

    public void setFilterList(ArrayList<String> filterList) {
        this.filterList = filterList;
    }

    public void setDataList(String dataList) {
        this.dataList.add(dataList);
    }

    public ArrayList<GetBranchRes> getGetBranchResArrayList() {
        return getBranchResArrayList;
    }

    public void setGetBranchResArrayList(ArrayList<GetBranchRes> getBranchResArrayList) {
        this.getBranchResArrayList = getBranchResArrayList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GetUnitRes getGetUnitRes() {
        return getUnitRes;
    }

    public void setGetUnitRes(GetUnitRes getUnitRes) {
        this.getUnitRes = getUnitRes;
    }

    public ArrayList<GetUnitRes> getGetUnitResArrayList() {
        return getUnitResArrayList;
    }

    public void setGetUnitResArrayList(ArrayList<GetUnitRes> getUnitResArrayList) {
        this.getUnitResArrayList = getUnitResArrayList;
    }
}
