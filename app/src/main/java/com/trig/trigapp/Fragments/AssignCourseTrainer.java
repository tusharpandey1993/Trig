package com.trig.trigapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonArray;
import com.trig.trigapp.Adapter.NavDrawerAdapter;
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.CustomSelectionDialog;
import com.trig.trigapp.CommonFiles.DataPayload;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.CommonFiles.ViewDialogCustom;
import com.trig.trigapp.CommonFiles.onDialogClickCallback;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogBuilder;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogClickListener;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogPopup;
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Request.AssignCoursesToEmp;
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Request.TrainerDashboardReq;
import com.trig.trigapp.api.Request.User_id;
import com.trig.trigapp.api.Response.GetBranchRes;
import com.trig.trigapp.api.Response.GetUnitRes;
import com.trig.trigapp.api.Response.UserListResponse;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Request.TrainerDashboardReq;
import com.trig.trigapp.api.Request.User_id;
import com.trig.trigapp.api.Response.GetBranchRes;
import com.trig.trigapp.api.Response.GetUnitRes;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;

public class AssignCourseTrainer extends BaseFragment implements GenericDialogClickListener,  onDialogClickCallback, View.OnClickListener , OnClickInterface, IPresenter {

    private static final String TAG = "DashboardTrainer";
    FragmentActivity mActivity;
    View mView;
    TextInputEditText edit_branch, edit_unit;
    ArrayList<String> typeOfList;
    private RecyclerView list;
    private ImageView closeIcon;
    private SlidingRootNav slidingRootNav;
    private ArrayList<GetUnitRes> getUnitResArrayList;
    private ArrayList<GetBranchRes> getBranchResArrayList;
    private ArrayList<UserListResponse> userListResponseArrayList;
    private ViewModel viewModel;
    CustomSelectionDialog cdd;
    private PieChart pieChartCourses, pieChartAssessment;
    private TextView courseNumber, courseCompletedNumber, assessmentNumber, assementCompleted;
    private ConstraintLayout courseContainer, assessmentContainer;
    private TextView toolBarText;
//    private ArrayList<GetUnitRes> getUnitResArrayList;
//    private ArrayList<GetBranchRes> getBranchResArrayList;
//    private ViewModel viewModel;
//    CustomSelectionDialog cdd;
    ImageView backIcon;
    public AssignCourseTrainer() {
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

        viewModel.getBranch(new User_id(TrigAppPreferences.getUserId(mActivity)));

        TrainerDashboardReq trainerDashboardReq = new TrainerDashboardReq();
        trainerDashboardReq.setEmp_code("9082461859");
        trainerDashboardReq.setUnitId(4755);
        viewModel.getTrainerDashboard(trainerDashboardReq);

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
        if (TrigAppPreferences.getUser_Type(mActivity).equalsIgnoreCase(Constants.getInstance().trainer)) {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_AssignCourse_to_dashboardTrainerFragment);
        }
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
        toolBarText.setText("Assign Course");
        edit_branch = mView.findViewById(R.id.edit_branch);
        edit_unit = mView.findViewById(R.id.edit_unit);
        edit_branch.setOnClickListener(this);
        edit_unit.setOnClickListener(this);
        pieChartCourses = mView.findViewById(R.id.pieChartCourses);
        pieChartAssessment = mView.findViewById(R.id.pieChartAssessment);

        courseNumber = mView.findViewById(R.id.courseNumber);
        courseCompletedNumber = mView.findViewById(R.id.courseCompletedNumber);
        assessmentNumber = mView.findViewById(R.id.assessmentNumber);
        assementCompleted = mView.findViewById(R.id.assementCompleted);
        backIcon = mView.findViewById(R.id.backIcon);
        viewModel = new ViewModel(mActivity, this);

        courseContainer = mView.findViewById(R.id.courseContainer);
        assessmentContainer = mView.findViewById(R.id.assessmentContainer);


    }

    @Override
    public void onClick(View view, int position, String selectedValue,String selectedID,String title) {
        if(title.equalsIgnoreCase("Branch")){
            edit_branch.setText(selectedValue);
            edit_unit.setEnabled(true);
            edit_unit.setHintTextColor(getResources().getColor(R.color.hint_color));

            CommonReq commonReq = new CommonReq();
            commonReq.setBranchId(selectedID);
            viewModel.getUnit(commonReq);

        }else if(title.equalsIgnoreCase("Unit")){
            edit_unit.setText(selectedValue);
            TrainerDashboardReq trainerDashboardReq = new TrainerDashboardReq();
            trainerDashboardReq.setEmp_code(TrigAppPreferences.getEmployee_Code(mActivity));
            trainerDashboardReq.setUnitId(Integer.parseInt(selectedID));
            viewModel.getUserList(trainerDashboardReq);
        }
        cdd.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.edit_branch:
                openDialog("Branch");
                break;
            case R.id.edit_unit:
                openDialog("Unit");
                break;
            case R.id.logout:
                TrigAppPreferences.clear(mActivity);
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_LoginFragment);
                break;

            case R.id.closeIcon:
                slidingRootNav.closeMenu();
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
            }else{
                if(getUnitResArrayList!=null) {
                    payload = new DataPayload();
                    payload.setTitle(title);
                    payload.setGetUnitResArrayList(getUnitResArrayList);
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
            getUnitResArrayList = new ArrayList<>();
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    GetUnitRes getUnitRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), GetUnitRes.class);
                    getUnitResArrayList.add(getUnitRes);
                    Log.d(TAG, "onResponsegetUnit: " + getUnitRes.toString());
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponsegetUnit: exception " + e.getMessage());
        }
    }

    @Override
    public void onResponseGetUserList(JsonArray jsonArray) {
        try {
            hideLoader();
            try {
                userListResponseArrayList = new ArrayList<>();
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        UserListResponse userListResponse = Utility.getInstance().getG().fromJson(jsonArray.get(i), UserListResponse.class);
                        userListResponseArrayList.add(userListResponse);
                        Log.d(TAG, "onResponsegetUnit: " + userListResponse.toString());
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "onResponsegetUnit: exception " + e.getMessage());
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseDashboardTrainer: exception" + e.getMessage());
        }
    }

    public void assignCourse() {
        AssignCoursesToEmp assignCoursesToEmp = new AssignCoursesToEmp();
        assignCoursesToEmp.setAssessment("1");
        assignCoursesToEmp.setCourse("1");
        assignCoursesToEmp.setTrainer_emp_code(Integer.parseInt(TrigAppPreferences.getEmployee_Code(mActivity)));
        assignCoursesToEmp.setUserName(TrigAppPreferences.getUserName(mActivity));
        viewModel.assignCourseTrainerToEmp(assignCoursesToEmp);
    }

    @Override
    public void onResponseAssignCourseRes(String string) {
        Log.d(TAG, "onResponseAssignCourseRes: " + string);
    }
}

