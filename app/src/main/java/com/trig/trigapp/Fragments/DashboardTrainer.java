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
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.trig.trigapp.api.Request.CommonReq;
import com.trig.trigapp.api.Request.TrainerDashboardReq;
import com.trig.trigapp.api.Request.User_id;
import com.trig.trigapp.api.Response.GetBranchRes;
import com.trig.trigapp.api.Response.GetUnitRes;
import com.trig.trigapp.api.Response.getCourseListRes;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragListener;

import java.util.ArrayList;

public class DashboardTrainer extends BaseFragment implements GenericDialogClickListener,  onDialogClickCallback, View.OnClickListener , OnClickInterface, IPresenter {

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
    private ViewModel viewModel;
    CustomSelectionDialog cdd;
    private PieChart pieChartCourses, pieChartAssessment;
    private TextView courseNumber, courseCompletedNumber, assessmentNumber, assementCompleted;
    private ConstraintLayout courseContainer, assessmentContainer;
    private TextView toolBarText;

    public DashboardTrainer() {
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
        mView = inflater.inflate(R.layout.trainer_dash, container, false);
        init(mView);

        viewModel.getBranch(new User_id(TrigAppPreferences.getUserId(mActivity)));

        backButtonHandling();

        Toolbar toolbar = mView.findViewById(R.id.toolbar2);

        slidingRootNav = new SlidingRootNavBuilder(mActivity)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withGravity(SlideGravity.LEFT)
                .withContentClickableWhenMenuOpened(false)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        list = mActivity.findViewById(R.id.list);
        closeIcon = mActivity.findViewById(R.id.closeIcon);
        closeIcon.setOnClickListener(this);
        setAdapter();

        return mView;
    }
    public void setAdapter(){
        list.setNestedScrollingEnabled(false);
        NavDrawerAdapter asCommonAdapter = new NavDrawerAdapter(mActivity, this,0);
        list.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(asCommonAdapter);
    }

    private void backButtonHandling() {
        try {
            OnBackPressedCallback callback = new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    // Handle the back button event
                    Log.d(TAG, "handleOnBackPressed: ");
                    GenericDialogPopup genericDialogPopup = null;
                    GenericDialogBuilder genericDialogBuilder = new GenericDialogBuilder.Builder()
                            .setShowCloseButton(false)
                            .setHeading(mActivity.getResources().getString(R.string.DialogHeading))
                            .setDescription(mActivity.getResources().getString(R.string.appExit))
                            .setPositiveButtonText(Constants.EXIT)
                            .setNegativeButtonText(Constants.Cancel)
                            .setGenericDialogClickListener(DashboardTrainer.this)
                            .setFucntionNumber(Constants.getInstance().exitApp)
                            .build();
                    Utility.getInstance().showDynamicDialog(mActivity, genericDialogBuilder, genericDialogPopup, mActivity.getSupportFragmentManager());
                }
            };
            mActivity.getOnBackPressedDispatcher().addCallback(this, callback);
        } catch (Exception e) {
            Log.e(TAG, "backButtonHandling: exception" + e.getMessage());
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
                    TrigAppPreferences.clear(mActivity);
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_DashboardTrainerFrag_to_LoginFragment);
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
        toolBarText.setText("Dashboard Trainer");
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

        courseContainer = mView.findViewById(R.id.courseContainer);
        assessmentContainer = mView.findViewById(R.id.assessmentContainer);

        viewModel = new ViewModel(mActivity, this);

    }

    @Override
    public void onClick(View view, int position, String selectedValue,String selectedID,String title) {
        if(title.equalsIgnoreCase(Constants.getInstance().Branch)){
            edit_branch.setText(selectedValue);
            edit_unit.setEnabled(true);
            edit_unit.setHintTextColor(getResources().getColor(R.color.hint_color));

            CommonReq commonReq = new CommonReq();
            commonReq.setBranchId(selectedID);
            viewModel.getUnit(commonReq);

        }else if(title.equalsIgnoreCase(Constants.getInstance().Unit)){
            edit_unit.setText(selectedValue);
            TrainerDashboardReq trainerDashboardReq = new TrainerDashboardReq();
            trainerDashboardReq.setEmp_code(TrigAppPreferences.getEmployee_Code(mActivity));
            trainerDashboardReq.setUnitId(Integer.parseInt(selectedID));
            viewModel.getTrainerDashboard(trainerDashboardReq);
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

            case R.id.closeIcon:
                slidingRootNav.closeMenu();
                break;

        }
    }

    public final int POS_DASHBOARD = 0;
    public final int POS_ASSIGN = 1;
    public final int POS_REPORT = 2;
    public final int POS_PROFILE = 3;
    public final int POS_CONTACT_US = 4;
    public final int POS_LOGOUT = 5;

    @Override
    public void onClick(View view, int position) {
        try {

            switch (position) {
                case POS_DASHBOARD:
                    break;
                case POS_ASSIGN:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_DashboardTrainerFrag_to_AssignCourseTrainerFrag);
                    break;
                case POS_REPORT:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_DashboardTrainerFrag_to_ReportFrag);
                    break;
                case POS_PROFILE:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_DashboardTrainerFrag_to_ProfileFragment);
                    break;
                case POS_CONTACT_US:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_DashboardTrainerFrag_to_Contact);
                    break;
                case POS_LOGOUT:TrigAppPreferences.setLoginPref(mActivity, false);
                    GenericDialogPopup genericDialogPopup = null;
                    GenericDialogBuilder genericDialogBuilder = new GenericDialogBuilder.Builder()
                            .setShowCloseButton(false)
                            .setHeading(mActivity.getResources().getString(R.string.logoutHeading))
                            .setDescription(mActivity.getResources().getString(R.string.appLogout))
                            .setPositiveButtonText(Constants.LOGOUT)
                            .setNegativeButtonText(Constants.Cancel)
                            .setGenericDialogClickListener(DashboardTrainer.this)
                            .setFucntionNumber(Constants.getInstance().logout)
                            .build();
                    Utility.getInstance().showDynamicDialog(mActivity, genericDialogBuilder, genericDialogPopup, mActivity.getSupportFragmentManager());
                    break;
            }
            if(slidingRootNav != null) {
                slidingRootNav.closeMenu();
            }
        } catch (Exception e){
            Log.e(TAG, "onClick: exception" + e.getMessage());
        }
    }

    private void openDialog(String title) {
        try {
            DataPayload payload = null;
            if(title.equalsIgnoreCase(Constants.getInstance().Branch)){
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
    public void onResponseDashboardTrainer(getDashboardRes getDashboardRes) {
        try {
            hideLoader();
            if (getDashboardRes != null && !Utility.getInstance().getG().toJson(getDashboardRes).equals("{}")) {

                courseContainer.setVisibility(View.VISIBLE);
                assessmentContainer.setVisibility(View.VISIBLE);

                courseNumber.setText(String.valueOf((getDashboardRes.getCourseAssigned())));
                courseCompletedNumber.setText(String.valueOf(getDashboardRes.getCourseCompleted()));
                assessmentNumber.setText(String.valueOf(getDashboardRes.getAssessmentAssigned()));
                assementCompleted.setText(String.valueOf(getDashboardRes.getAssessmentCompleted()));

                pieChartCourses.setDescription(null);
                pieChartAssessment.setDescription(null);

                courseChart(getDashboardRes.getCourseCompleted(), getDashboardRes.getCourseAssigned());
                assessmentChart(getDashboardRes.getAssessmentCompleted(), getDashboardRes.getAssessmentAssigned());
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseDashboardTrainer: exception" + e.getMessage());
        }
    }

    private void courseChart(int completed, int total){
        float percentage = (float)((completed  * 100)/ total);
        float remaining = 100 - percentage;
        ArrayList completedCourses = new ArrayList();
        completedCourses.add(new Entry(percentage, 6));
        completedCourses.add(new Entry(remaining, 1 ));
        PieDataSet dataSet = new PieDataSet(completedCourses, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(textOnPieChart(), dataSet);
        pieChartCourses.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChartCourses.animateXY(1000, 1000);
    }


    private void assessmentChart(int completed, int total){
        float percentage = (float)((completed  * 100)/ total);
        float remaining = 100 - percentage;
        ArrayList completedCourses = new ArrayList();
        completedCourses.add(new Entry(percentage, 6));
        completedCourses.add(new Entry(remaining, 1 ));
        PieDataSet dataSet = new PieDataSet(completedCourses, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(textOnPieChart(), dataSet);
        pieChartAssessment.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChartAssessment.animateXY(1000, 1000);
    }

    private ArrayList textOnPieChart(){
        ArrayList textBelowPie = new ArrayList();
        textBelowPie.add("Completed %");
        textBelowPie.add("Not started %");
        return textBelowPie;
    }
}
