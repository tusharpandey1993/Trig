package com.trig.trigapp.CommonFiles;

import com.trig.trigapp.api.Response.GetBranchRes;
import com.trig.trigapp.api.Response.GetUnitRes;

import java.util.ArrayList;

public class DataPayload {

    public String title;
    public GetUnitRes getUnitRes;
    private ArrayList<GetUnitRes> getUnitResArrayList = new ArrayList<>();
    private ArrayList<GetBranchRes> getBranchResArrayList = new ArrayList<>();
    private ArrayList<String> dataList = new ArrayList<>();

    public ArrayList<String> getDataList() {
        return dataList;
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
