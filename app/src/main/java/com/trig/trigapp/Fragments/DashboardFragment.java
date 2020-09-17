package com.trig.trigapp.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
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
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.trig.trigapp.CommonFiles.onDialogClickCallback;
import com.trig.trigapp.R;
import com.trig.trigapp.menu.DrawerAdapter;
import com.trig.trigapp.menu.DrawerItem;
import com.trig.trigapp.menu.SimpleItem;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements onDialogClickCallback, View.OnClickListener, DrawerAdapter.OnItemSelectedListener {

    private static final String TAG = "DashboardFragment";
    FragmentActivity mActivity;

    private View mView;
    ConstraintLayout constraintLayout1, skillContainer, constraintLayout3, otherCoursesContainer, assessmentContainer;
    TextView toolBarText, feedback;
    ImageView logout;



    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;

    private SlidingRootNav slidingRootNav;
    private static final int POS_DASHBOARD = 0;
    private static final int POS_COURCES = 1;
    private static final int POS_ASSESSMENT = 2;
    private static final int POS_PROFILE = 3;
    private static final int POS_CONTACT_US = 4;
    private static final int POS_LOGOUT = 5;
    private String[] screenTitles;
    private Drawable[] screenIcons;

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

        OnBackPressedCallback callback = new  OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Log.d(TAG, "handleOnBackPressed: ");
//                ViewDialogCustom.customDialog2Btn(mActivity, mActivity.getResources().getString(R.string.exitConfirmation),
//                        Constants.Cancel , Constants.Ok, Constants.ExitConfirm,  DashboardFragment.this);
            }
        };
        mActivity.getOnBackPressedDispatcher().addCallback(this, callback);

        Toolbar toolbar = mView.findViewById(R.id.toolbar2);

        slidingRootNav = new SlidingRootNavBuilder(mActivity)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withGravity(SlideGravity.LEFT)
                .withContentClickableWhenMenuOpened(true)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();


        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_COURCES),
                createItemFor(POS_ASSESSMENT),
                createItemFor(POS_PROFILE),
                createItemFor(POS_CONTACT_US),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = mActivity.findViewById(R.id.list);

        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(mActivity));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);

        constraintLayout1.setOnClickListener(this);
        skillContainer.setOnClickListener(this);
        constraintLayout3.setOnClickListener(this);
        otherCoursesContainer.setOnClickListener(this);
        assessmentContainer.setOnClickListener(this);
        feedback.setOnClickListener(this);

        return mView;
    }

    private void init(View mView){
        constraintLayout1 = mView.findViewById(R.id.constraintLayout1);
        skillContainer = mView.findViewById(R.id.skillContainer);
        constraintLayout3 = mView.findViewById(R.id.constraintLayout3);
        otherCoursesContainer = mView.findViewById(R.id.otherCoursesContainer);
        assessmentContainer = mView.findViewById(R.id.assessmentContainer);
        feedback = mView.findViewById(R.id.feedback);




//        toolBarText = mView.findViewById(R.id.toolBarText);
//        logout = mView.findViewById(R.id.logout);


//        toolBarText.setText("Dashboard");

//        logout.setVisibility(View.VISIBLE);
/*
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrigAppPreferences.setLoginPref(mActivity, false);
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_LoginFragment);
            }
        });
*/




        pieChart = mView.findViewById(R.id.pieChart);
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setSliceSpace(5f);
    }

    @Override
    public void onLeftClick(Context context, String text, Integer FunctionNum) {

    }

    @Override
    public void onRightClick(Context context, String text, Integer FunctionNum) {
        switch (FunctionNum){
            case 10:

                break;
        }

    }

    @Override
    public void onOkClick(Context context, String text, Integer FunctionNum) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.constraintLayout1:
//                toolBarText.setText("Induction Training");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_VideoFragment);
                break;
            case R.id.skillContainer:
//                toolBarText.setText("Skill Training");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_VideoFragment);
                break;
            case R.id.constraintLayout3:
//                toolBarText.setText("Functional Training");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_VideoFragment);
                break;
            case R.id.otherCoursesContainer:
//                toolBarText.setText("Other Courses");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_VideoFragment);
                break;
            case R.id.assessmentContainer:
//                toolBarText.setText("Assessments");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_AssessmentFragment);
                break;
            case R.id.feedback:
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_FeedbackFragment);
                break;
        }
    }


    private void getEntries() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(2f, 0));
        pieEntries.add(new PieEntry(8f, 1));
    }



    //This dialog is show to the user after he ans correct
    public void feedBackDialog() {
        final Dialog dialogCorrect = new Dialog(mActivity);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.feedback_dialog);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        //Since the dialog is show to user just pause the timer in background
        onPause();


        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        FButton buttonNext = (FButton) dialogCorrect.findViewById(R.id.dialogNext);




        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will dismiss the dialog
                dialogCorrect.dismiss();
                //it will increment the question numbe
            }
        });
    }

    @Override
    public void onItemSelected(int position) {
        if (position == POS_LOGOUT) {

        }
        slidingRootNav.closeMenu();
//        Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
//        showFragment(selectedScreen);
    }

    private void showFragment(Fragment fragment) {
        mActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @SuppressWarnings("rawtypes")
    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.gray))
                .withTextTint(color(R.color.gray))
                .withSelectedIconTint(color(R.color.colorAccent))
                .withSelectedTextTint(color(R.color.colorAccent));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(mActivity, id);
            }
        }
        ta.recycle();
        return icons;
    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(mActivity, res);
    }


}
