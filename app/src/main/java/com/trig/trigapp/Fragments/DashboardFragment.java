package com.trig.trigapp.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.trig.trigapp.Adapter.NavDrawerAdapter;
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogBuilder;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogClickListener;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogPopup;
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.getDashboardRes;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment implements GenericDialogClickListener, View.OnClickListener , OnClickInterface, DragListener, IPresenter {

    private static final String TAG = "DashboardFragment";
    FragmentActivity mActivity;

    private View mView;
    private ConstraintLayout courseContainer, assessmentContainer;
    private TextView toolBarText, feedback, userText, courseNumber, courseCompletedNumber, assessmentNumber, assementCompleted;
    private MaterialButton Category, initials;
    private RecyclerView list;
    private ViewModel viewModel;
    public static boolean fromCourses = false;
    public static boolean goToContactTrainer = false;
    private PieChart pieChartResult, pieChartCourses, pieChartAssessment;

    private ImageView closeIcon;

    private SlidingRootNav slidingRootNav;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_dashboard, container, false);


        init(mView);
        viewModel.getDashboard(TrigAppPreferences.getUserId(mActivity));

        backButtonHandling();

        Toolbar toolbar = mView.findViewById(R.id.toolbar2);

        slidingRootNav = new SlidingRootNavBuilder(mActivity)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .addDragListener(this)
                .withGravity(SlideGravity.LEFT)
                .withContentClickableWhenMenuOpened(false)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();


        list = mActivity.findViewById(R.id.list);
        closeIcon = mActivity.findViewById(R.id.closeIcon);
        closeIcon.setOnClickListener(this);
        setAdapter();

        courseContainer.setOnClickListener(this);
        assessmentContainer.setOnClickListener(this);
        feedback.setOnClickListener(this);

        return mView;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        Log.e(TAG, "getLifecycle: "+super.getLifecycle().getCurrentState());
        return super.getLifecycle();
    }

    public void setAdapter(){
        list.setNestedScrollingEnabled(false);
        NavDrawerAdapter asCommonAdapter = new NavDrawerAdapter(mActivity, this,1);
        list.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(asCommonAdapter);
    }

    private void init(View mView){
        viewModel = new ViewModel(mActivity,this);
        courseContainer = mView.findViewById(R.id.courseContainer);
        assessmentContainer = mView.findViewById(R.id.assessmentContainer);
        feedback = mView.findViewById(R.id.feedback);
        userText = mView.findViewById(R.id.userText);
        Category = mView.findViewById(R.id.Category);
        initials = mView.findViewById(R.id.initials);
        courseNumber = mView.findViewById(R.id.courseNumber);
        courseCompletedNumber = mView.findViewById(R.id.courseCompletedNumber);
        assessmentNumber = mView.findViewById(R.id.assessmentNumber);
        assementCompleted = mView.findViewById(R.id.assementCompleted);

        userText.setText(TrigAppPreferences.getName(mActivity) + " (" + TrigAppPreferences.getEmployee_Code(mActivity) + ")");
        Category.setText(TrigAppPreferences.getUser_Type(mActivity));
        if(TrigAppPreferences.getisUserModeTrainer(mActivity)){
            initials.setVisibility(View.VISIBLE);
            initials.setText("T");
        } else {
            initials.setVisibility(View.INVISIBLE);
        }

        pieChartCourses = mView.findViewById(R.id.pieChartCourses);
        pieChartAssessment = mView.findViewById(R.id.pieChartAssessment);
        pieChartResult = mView.findViewById(R.id.pieChartResult);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.courseContainer:
//                toolBarText.setText("Assessments");
                fromCourses = true;
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_topics);
                break;
            case R.id.assessmentContainer:
//                toolBarText.setText("Assessments");
                fromCourses = false;
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_topics);
                break;
            case R.id.feedback:
//                toolBarText.setText("Feedback");
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_FeedbackFragment);
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

    //This dialog is show to the user after he ans correct
    public void feedBackDialog() {
        final Dialog dialogCorrect = new Dialog(mActivity);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
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
                            .setGenericDialogClickListener(DashboardFragment.this)
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

    @Override
    public void onNegativeButtonClick(View view, int FucntionNumber) {

    }

    @Override
    public void onDialogCloseButtonClick(View view, int FucntionNumber) {

    }

    public final int POS_DASHBOARD = 0;
    public final int POS_COURCES = 1;
    public final int POS_ASSESSMENT = 2;
    public final int POS_PROFILE = 3;
    public final int POS_CONTACT_US = 4;
    public final int POS_CONTACT_TRAINER = 5;
    public final int POS_FEEDBACK = 6;
    public final int POS_LOGOUT = 7;

    @Override
    public void onClick(View view, int position) {
        try {

            switch (position) {
                case POS_DASHBOARD:
                    break;
                case POS_COURCES:
                    fromCourses = true;
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_topics);
                    break;
                case POS_ASSESSMENT:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_topics);
                    break;
                case POS_PROFILE:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_ProfileFragment);
                    break;
                case POS_CONTACT_US:
                    goToContactTrainer = true;
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_Contact);
                    break;
                case POS_CONTACT_TRAINER:
                    goToContactTrainer = false;
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_Contact);
                    break;
                case POS_FEEDBACK:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_FeedbackFragment);
                    break;
                case POS_LOGOUT:TrigAppPreferences.setLoginPref(mActivity, false);
                    GenericDialogPopup genericDialogPopup = null;
                    GenericDialogBuilder genericDialogBuilder = new GenericDialogBuilder.Builder()
                            .setShowCloseButton(false)
                            .setHeading(mActivity.getResources().getString(R.string.logoutHeading))
                            .setDescription(mActivity.getResources().getString(R.string.appLogout))
                            .setPositiveButtonText(Constants.LOGOUT)
                            .setNegativeButtonText(Constants.Cancel)
                            .setGenericDialogClickListener(DashboardFragment.this)
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

    @Override
    public void onDrag(float progress) {
        Fragment navHostFragment = mActivity.getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        if (fragment!=null && !(fragment instanceof DashboardFragment)){
           /* slidingRootNav = new SlidingRootNavBuilder(mActivity)
                    .withMenuOpened(false)
                    .addDragListener(this)
                    .withGravity(SlideGravity.LEFT)
                    .withContentClickableWhenMenuOpened(true)
                    .withMenuLayout(R.layout.menu_left_drawer)
                    .withMenuLocked(true)
                    .inject();*/
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        fromCourses = false;

    }

    @Override
    public void showProgressDialog() {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void hideProgressDialog() {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void onError(String error) {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void onError(String error, int code) {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void onError(Object error) {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void onError(Object error, int Code) {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void onResponseGetDashboard(getDashboardRes dashboardResponse) {
        try {
            hideLoader();
            if (dashboardResponse != null && !Utility.getInstance().getG().toJson(dashboardResponse).equals("{}")) {

//                {"assessmentAssigned":2,"assessmentCompleted":2,"courseAssigned":7,"courseCompleted":6,"courseInprogress":0}

                courseNumber.setText(String.valueOf((dashboardResponse.getCourseAssigned())));
                courseCompletedNumber.setText(String.valueOf(dashboardResponse.getCourseCompleted()));
                assessmentNumber.setText(String.valueOf(dashboardResponse.getAssessmentAssigned()));
                assementCompleted.setText(String.valueOf(dashboardResponse.getAssessmentCompleted()));

                pieChartCourses.setDescription(null);
                pieChartAssessment.setDescription(null);
                pieChartResult.setDescription(null);

                courseChart(dashboardResponse.getCourseCompleted(), dashboardResponse.getCourseAssigned());
                assessmentChart(dashboardResponse.getAssessmentCompleted(), dashboardResponse.getAssessmentAssigned());
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: exception" + e.getMessage());
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
        pieChartResult.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChartResult.animateXY(1000, 1000);
    }

    private ArrayList textOnPieChart(){
        ArrayList textBelowPie = new ArrayList();
        textBelowPie.add("Assigned %");
        textBelowPie.add("Pending %");
        return textBelowPie;
    }
}
