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


    private ImageView closeIcon;
    private PieChart pieChart;
    private PieChart pieChart3;
    private PieData pieData1;
    private PieData pieData2;
    private PieData pieData3;
    private PieDataSet pieDataSet1;
    private PieDataSet pieDataSet2;
    private PieDataSet pieDataSet3;
    private ArrayList pieEntries;
    private ArrayList pieEntries2;
    private ArrayList PieEntryLabels;
    private ArrayList PieEntryLabels2;
    private ArrayList PieEntryLabels3;
    private PieChart coursePieChart1;
    private PieChart coursePieChart2;
    private PieChart coursePieChart3;
    private ArrayList<Entry> entries1;
    private ArrayList<Entry> entries2;
    private ArrayList<Entry> entries3;

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
        NavDrawerAdapter asCommonAdapter = new NavDrawerAdapter(mActivity, this);
        list.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(asCommonAdapter);
    }

    private void init(View mView){
        viewModel = new ViewModel(mActivity,this);
        courseContainer = mView.findViewById(R.id.courseContainer);
        assessmentContainer = mView.findViewById(R.id.assessmentContainer);
        feedback = mView.findViewById(R.id.feedback);

        pieChart = mView.findViewById(R.id.pieChart);
        pieChart3 = mView.findViewById(R.id.pieChart3);
        coursePieChart1 = mView.findViewById(R.id.coursePieChart);
        coursePieChart2 = mView.findViewById(R.id.coursePieChart);
        userText = mView.findViewById(R.id.userText);
        Category = mView.findViewById(R.id.Category);
        initials = mView.findViewById(R.id.initials);
        courseNumber = mView.findViewById(R.id.courseNumber);
        courseCompletedNumber = mView.findViewById(R.id.courseCompletedNumber);
        assessmentNumber = mView.findViewById(R.id.assessmentNumber);
        assementCompleted = mView.findViewById(R.id.assementCompleted);

        entries1 = new ArrayList<>();
        entries2 = new ArrayList<>();
        entries3 = new ArrayList<>();
        PieEntryLabels = new ArrayList<String>();
        PieEntryLabels2 = new ArrayList<String>();
        PieEntryLabels3 = new ArrayList<String>();

        AddValuesToPIEENTRY();
        AddValuesToPieEntryLabels();

        pieDataSet1 = new PieDataSet(entries1, "");
        pieDataSet2 = new PieDataSet(entries1, "");
        pieDataSet3 = new PieDataSet(entries1, "");
        pieData1 = new PieData(PieEntryLabels, pieDataSet1);
        pieData2 = new PieData(PieEntryLabels, pieDataSet2);
//        pieData3 = new PieData(PieEntryLabels3, pieDataSet3);
        pieDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet3.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(pieData1);
        pieChart3.setData(pieData1);
        coursePieChart1.setData(pieData1);
        coursePieChart2.setData(pieData2);
//        coursePieChart3.setData(pieData3);
        pieChart.animateY(1000);
        pieChart3.animateY(1000);
        coursePieChart1.animateY(1000);
        coursePieChart2.animateY(1000);
//        coursePieChart3.animateY(1000);

        userText.setText(TrigAppPreferences.getName(mActivity) + " (" + TrigAppPreferences.getEmployee_Code(mActivity) + ")");
        Category.setText(TrigAppPreferences.getUser_Type(mActivity));
//        String initialsExtract =TrigAppPreferences.getUser_Type(mActivity);
        if(TrigAppPreferences.getisUserModeTrainer(mActivity)){
            initials.setVisibility(View.VISIBLE);
            initials.setText("T");
        } else {
            initials.setVisibility(View.INVISIBLE);
        }

    }

    public void AddValuesToPIEENTRY(){
        entries1.add(new BarEntry(2f, 0));
        entries1.add(new BarEntry(2f, 1));
        entries2.add(new BarEntry(2f, 0));
        entries2.add(new BarEntry(1f, 1));
        entries3.add(new BarEntry(2f, 0));
        entries3.add(new BarEntry(1f, 1));
    }

    public void AddValuesToPieEntryLabels(){
        PieEntryLabels.add("Completed");
        PieEntryLabels.add("Pending");
        PieEntryLabels3.add("Score");
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
                SharedPreferences settings = mActivity.getSharedPreferences("ParentalPreferences", Context.MODE_PRIVATE);
                settings.edit().clear().commit();
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
            slidingRootNav = new SlidingRootNavBuilder(mActivity)
                    .withMenuOpened(false)
                    .addDragListener(this)
                    .withGravity(SlideGravity.LEFT)
                    .withContentClickableWhenMenuOpened(true)
                    .withMenuLayout(R.layout.menu_left_drawer)
                    .withMenuLocked(true)
                    .inject();
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
    public void onResponseProfile(getDashboardRes dashboardResponse) {
        try {
            hideLoader();
            if (dashboardResponse != null && !Utility.getInstance().getG().toJson(dashboardResponse).equals("{}")) {
                courseNumber.setText(String.valueOf((dashboardResponse.getCourseAssigned())));
                courseCompletedNumber.setText(String.valueOf(dashboardResponse.getCourseCompleted()));
                assessmentNumber.setText(String.valueOf(dashboardResponse.getAssessmentAssigned()));
                assementCompleted.setText(String.valueOf(dashboardResponse.getAssessmentCompleted()));
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: exception" + e.getMessage());
        }
    }

}
