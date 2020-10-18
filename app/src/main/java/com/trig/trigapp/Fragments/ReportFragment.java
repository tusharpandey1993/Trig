package com.trig.trigapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonArray;
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.Adapter.ReportAdapter;
import com.trig.trigapp.Adapter.ReportChildAdapter;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.CustomSelectionDialog;
import com.trig.trigapp.CommonFiles.DataPayload;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.CommonFiles.onDialogClickCallback;
import com.trig.trigapp.CustomViewsFiles.CustomListViewDialog;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogClickListener;
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Request.GetReportReq;
import com.trig.trigapp.api.Request.User_id;
import com.trig.trigapp.api.Response.GetBranchRes;
import com.trig.trigapp.api.Response.GetReportRes;
import com.trig.trigapp.api.Response.GetUnitRes;
import com.trig.trigapp.api.Response.getCourseDetailsRes;
import com.trig.trigapp.api.Response.getDashboardRes;
import java.util.ArrayList;

public class ReportFragment extends BaseFragment implements GenericDialogClickListener,  onDialogClickCallback, View.OnClickListener , OnClickInterface, IPresenter {

    private static final String TAG = "ProfileFragment";
    FragmentActivity mActivity;
    View mView;
    TextInputEditText edit_branch, edit_unit, resetOneEmpET;
    private ArrayList<GetUnitRes> getUnitResArrayList;
    private ArrayList<GetBranchRes> getBranchResArrayList;
    private ArrayList<GetReportRes> getReportResArrayList;
    private ArrayList<getCourseDetailsRes> getCourseDetailsResArrayList;
    private ViewModel viewModel;
    CustomSelectionDialog cdd;
    ImageView backIcon;
    private TextView toolBarText;
    RecyclerView filteredListRecycler;
    LinearLayout assignCourseCheckboxContainer;
    private Button reassignAll, reassignEmp;
    private ArrayList<String> moreFilters;
    private int selectedUnitId;

    public ReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.assign_course_trainer, container, false);
        init(mView);


        moreFilters = new ArrayList<>();
        moreFilters.add("All");
        moreFilters.add("Completed");
        moreFilters.add("Pending");
        moreFilters.add("Active");


        showLoader();
        viewModel.getBranch(new User_id(TrigAppPreferences.getUserId(mActivity)));



        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                moveBackNavigation();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveBackNavigation();
            }
        });


        return mView;
    }
    private void moveBackNavigation() {
        if (TrigAppPreferences.getUser_Type(mActivity).equalsIgnoreCase(Constants.getInstance().user)) {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_profile_to_dashboardFragment);
        } else if (TrigAppPreferences.getUser_Type(mActivity).equalsIgnoreCase(Constants.getInstance().trainer)) {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_Report_to_dashboardTrainerFragment);
        }
    }

    public void setAdapter(){
        if(getReportResArrayList!=null) {
            filteredListRecycler.setNestedScrollingEnabled(false);
            ReportAdapter asCommonAdapter = new ReportAdapter(mActivity, this, getReportResArrayList);
            filteredListRecycler.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
            filteredListRecycler.setAdapter(asCommonAdapter);
        }
    }

    public void setAdapterGetCourse() {
        if (getCourseDetailsResArrayList != null) {

            CustomListViewDialog customDialog;

            ReportChildAdapter reportChildAdapter = new ReportChildAdapter(mActivity, this, getCourseDetailsResArrayList);
            customDialog = new CustomListViewDialog(mActivity, reportChildAdapter);

            customDialog.show();
            customDialog.setCanceledOnTouchOutside(false);

        }


       /* if(getCourseDetailsResArrayList!=null) {
            filteredListRecycler.setNestedScrollingEnabled(false);
            ReportChildAdapter reportChildAdapter = new ReportChildAdapter(mActivity, this, getCourseDetailsResArrayList);
            filteredListRecycler.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
            filteredListRecycler.setAdapter(reportChildAdapter);
        }*/
    }

    @Override
    public void onPositiveButtonClick(View view, int FucntionNumber) {
        try {
            switch (FucntionNumber) {
                case 800:
                    mActivity.finish();
                    break;
                case 810:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_LoginFragment);
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(View mView) {
        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("Report");
        edit_branch = mView.findViewById(R.id.edit_branch);
        edit_unit = mView.findViewById(R.id.edit_unit);
        resetOneEmpET           = mView.findViewById(R.id.resetOneEmpET);
        edit_branch.setOnClickListener(this);
        edit_unit.setOnClickListener(this);
        backIcon = mView.findViewById(R.id.backIcon);
        reassignEmp = mView.findViewById(R.id.reassignEmp);
        filteredListRecycler = mView.findViewById(R.id.filteredListRecycler);
        reassignAll = mView.findViewById(R.id.reassignAll);
        assignCourseCheckboxContainer = mView.findViewById(R.id.assignCourseCheckboxContainer);
        assignCourseCheckboxContainer.setVisibility(View.GONE);
        reassignAll.setVisibility(View.GONE);
        viewModel = new ViewModel(mActivity, this);

        reassignEmp.setText("Get Report");
        reassignEmp.setOnClickListener(this);
        resetOneEmpET.setOnClickListener(this);

    }

    @Override
    public void onClick(View view, int position, String selectedValue,String selectedID,String title) {
        if(title.equalsIgnoreCase(Constants.getInstance().Branch)){

            showLoader();

            edit_branch.setText(selectedValue);
            edit_unit.setEnabled(true);
            edit_unit.setHintTextColor(getResources().getColor(R.color.hint_color));

            CommonReq commonReq = new CommonReq();
            commonReq.setBranchId(selectedID);
            viewModel.getUnit(commonReq);

        }else if(title.equalsIgnoreCase(Constants.getInstance().Unit)){
            Log.d(TAG, "onClick: position " + position + " selectedValue " + selectedValue + " selectedID " + selectedID+ " title " + title);
//            selectedUnitId = Integer.parseInt(selectedValue);
            edit_unit.setText(selectedValue);
        }
        cdd.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.edit_branch:
                openDialog(Constants.getInstance().Branch);
                break;
            case R.id.edit_unit:
                openDialog(Constants.getInstance().Unit);
                break;
            case R.id.resetOneEmpET:
                openDialog(Constants.getInstance().FilterList);
                break;
            case R.id.reassignEmp:
                hitReportApi();
                break;

        }
    }

    private void openDialog(String title) {
        try {
            DataPayload payload = null;
            if(title.equalsIgnoreCase("Branch")){
                if(getBranchResArrayList!=null) {
                    payload = new DataPayload();
                    payload.setTitle(title);
                    payload.setGetBranchResArrayList(getBranchResArrayList);
                }
            }else if(title.equalsIgnoreCase(Constants.getInstance().Unit)){
                if(getUnitResArrayList!=null) {
                    payload = new DataPayload();
                    payload.setTitle(title);
                    payload.setGetUnitResArrayList(getUnitResArrayList);
                }
            } else {
                if(moreFilters!=null) {
                    payload = new DataPayload();
                    payload.setTitle(title);
                    payload.setFilterList(moreFilters);
                }
            }
            if(payload!=null) {
                cdd = new CustomSelectionDialog(mActivity, payload, this);
                cdd.show();
            }
        } catch (Exception e){
            Log.e("", "onClick: " + e.getMessage() );
        }
    }

    @Override
    public void onLeftClick(Context context, String text, Integer FunctionNum) {

    }

    @Override
    public void onRightClick(Context context, String text, Integer FunctionNum) {
        switch (FunctionNum){
            case 10:
                mActivity.finish();
                break;
        }
    }

    @Override
    public void onOkClick(Context context, String text, Integer FunctionNum) {

    }

    @Override
    public void onResponsegetBranch(JsonArray jsonArray) {
        try {
            hideLoader();
            if (jsonArray != null) {
                getBranchResArrayList = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    GetBranchRes getBranchRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), GetBranchRes.class);
                    getBranchResArrayList.add(getBranchRes);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponsegetBranch: exception " + e.getMessage());
        }
    }


    @Override
    public void onResponsegetUnit(JsonArray jsonArray) {
        try {
            hideLoader();
            getUnitResArrayList = new ArrayList<>();
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    GetUnitRes getUnitRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), GetUnitRes.class);
                    getUnitResArrayList.add(getUnitRes);
                    Log.d(TAG, "onResponsegetUnit: " + getUnitRes.toString());
                }
            }
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponsegetUnit: exception " + e.getMessage());
        }
    }

    @Override
    public void onResponseDashboardTrainer(getDashboardRes getDashboardRes) {
        try {
            hideLoader();
            if (getDashboardRes != null && !Utility.getInstance().getG().toJson(getDashboardRes).equals("{}")) {

            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseDashboardTrainer: exception" + e.getMessage());
        }
    }

    @Override
    public void onResponseGetUserReportRes(JsonArray jsonArray) {
        try {
            hideLoader();
            getReportResArrayList = new ArrayList<>();
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    GetReportRes getReportRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), GetReportRes.class);
                    getReportResArrayList.add(getReportRes);
                    Log.d(TAG, "onResponseGetUserReportRes: " + getReportResArrayList.toString());
                }

                setAdapter();
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponsegetUnit: exception " + e.getMessage());
        }
    }

    private void hitReportApi() {
        try {
            if (Utility.getInstance().isNetworkAvailable(mActivity)) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                showLoader();
                mLastClickTime = SystemClock.elapsedRealtime();
                GetReportReq getReportReq = new GetReportReq();
                getReportReq.setEmp_code(TrigAppPreferences.getEmployee_Code(mActivity));
                getReportReq.setUnitId(456);
                getReportReq.setStatus("all");
                viewModel.getReport(getReportReq);
            } else {
                Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.no_internet_message));
            }
        } catch (Exception e) {
            Log.e(TAG, "hitAssignAllApi: " + e.getMessage());
        }
    }
    @Override
    public void onError(Object error) {
        Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.server_error));
        hideLoader();
    }

    @Override
    public void onClickReport(View view, int viewName, int UnitId) {
        CommonReq commonReq = new CommonReq();
        switch (viewName) {
            case 220:
                commonReq.setUserid("" + UnitId);
                Log.d(TAG, "onClickReport: " + UnitId);
                viewModel.getCourseTrainer(commonReq);
                break;
            case 221:
                commonReq.setUserid("" + UnitId);
                viewModel.getCourseTrainer(commonReq);
                break;
            case 222:
                Constants.getInstance().sendUnitIdForFeedBack = UnitId;
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_DashboardTrainerFrag_to_FeedbackFragment);
                break;
        }

    }

    @Override
    public void onResponseGetCourseTrainerRes(JsonArray jsonArray) {
        try {
            hideLoader();
            getCourseDetailsResArrayList = new ArrayList<>();
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    getCourseDetailsRes getCourseDetailsRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), getCourseDetailsRes.class);
                    getCourseDetailsResArrayList.add(getCourseDetailsRes);
                    Log.d(TAG, "onResponseGetCourseTrainerRes: " + getCourseDetailsResArrayList.toString());
                }

                setAdapterGetCourse();
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponsegetUnit: exception " + e.getMessage());
        }
    }
}
