package com.trig.trigapp.CommonFiles;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.trig.trigapp.Adapter.DialogItemClickListAdapter;
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Response.GetBranchRes;
import com.trig.trigapp.api.Response.GetUnitRes;
import com.trig.trigapp.api.Response.UserListResponse;

import java.util.ArrayList;

public class CustomSelectionDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity mActivity;
    public Dialog d;
    public TextView titledialog;
    public RecyclerView listdialog;
    private DialogItemClickListAdapter dialogItemClickListAdapter;
    EditText editTextSearch;
    OnClickInterface mListner;
    DataPayload payload;

    public CustomSelectionDialog(Activity activity,DataPayload payload, OnClickInterface mListner) {
        super(activity);
        this.mActivity = activity;
        this.payload = payload;
        this.mListner = mListner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.selection_popup_mainview);
            titledialog = findViewById(R.id.titledialog);
            editTextSearch = findViewById(R.id.editTextSearch);
            editTextSearch.setHint("Select "+payload.getTitle());
            listdialog = findViewById(R.id.listdialog);
            titledialog.setText(payload.getTitle());
            if(payload.getTitle().equalsIgnoreCase(Constants.getInstance().Branch)){
                for(int i=0;i<payload.getGetBranchResArrayList().size();i++){
                    payload.setDataList(payload.getGetBranchResArrayList().get(i).getBranchName());
                }
            }else if(payload.getTitle().equalsIgnoreCase(Constants.getInstance().Unit)){
                for(int i=0;i<payload.getGetUnitResArrayList().size();i++){
                    payload.setDataList(payload.getGetUnitResArrayList().get(i).getUnitName());
                }
            }else if(payload.getTitle().equalsIgnoreCase(Constants.getInstance().EMPCode)){
                for(int i=0;i<payload.getUserListResponses().size();i++){
                    payload.setDataList(payload.getUserListResponses().get(i).getTirg_EmpCode());
                }
            }
            listdialog.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
            dialogItemClickListAdapter = new DialogItemClickListAdapter(payload,mListner);


            listdialog.setAdapter(dialogItemClickListAdapter);


            editTextSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    filter(editable.toString());
                }
            });

        } catch (Exception e){
            Log.e("TAG", "onCreate: " + e.getMessage() );
        }
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<String> filterdData = new ArrayList<>();
        ArrayList<GetBranchRes> filteredGetBranchRes = new ArrayList<>();
        ArrayList<GetUnitRes> filteredGetUnitRes = new ArrayList<>();
        ArrayList<UserListResponse> filteredGetUserListRes = new ArrayList<>();

        //looping through existing elements
        if(payload.getTitle().equalsIgnoreCase(Constants.getInstance().Branch)){
            for(int i =0;i<payload.getGetBranchResArrayList().size();i++){
                if (payload.getGetBranchResArrayList().get(i).getBranchName().toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdData.add(payload.getGetBranchResArrayList().get(i).getBranchName());
                    filteredGetBranchRes.add((payload.getGetBranchResArrayList().get(i)));
                }
            }

        }else if(payload.getTitle().equalsIgnoreCase(Constants.getInstance().Unit)){
            for(int i =0;i<payload.getGetUnitResArrayList().size();i++){
                if (payload.getGetUnitResArrayList().get(i).getUnitName().toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdData.add(payload.getGetUnitResArrayList().get(i).getUnitName());
                    filteredGetUnitRes.add((payload.getGetUnitResArrayList().get(i)));
                }
            }
        }else if(payload.getTitle().equalsIgnoreCase(Constants.getInstance().EMPCode)){
            for(int i =0;i<payload.getUserListResponses().size();i++){
                if (payload.getUserListResponses().get(i).getTirg_EmpCode().toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdData.add(payload.getUserListResponses().get(i).getTirg_EmpCode());
                    filteredGetUserListRes.add((payload.getUserListResponses().get(i)));
                }
            }
        }
        //calling a method of the adapter class and passing the filtered list
        dialogItemClickListAdapter.filterList(filterdData, filteredGetBranchRes, filteredGetUnitRes ,filteredGetUserListRes);
    }

    @Override
    public void onClick(View v) {

    }


}
