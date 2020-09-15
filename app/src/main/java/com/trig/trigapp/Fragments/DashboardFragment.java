package com.trig.trigapp.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

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
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CommonFiles.ViewDialogCustom;
import com.trig.trigapp.CommonFiles.onDialogClickCallback;
import com.trig.trigapp.R;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements onDialogClickCallback, View.OnClickListener {

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

        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Log.d(TAG, "handleOnBackPressed: ");
//                ViewDialogCustom.customDialog2Btn(mActivity, mActivity.getResources().getString(R.string.exitConfirmation),
//                        Constants.Cancel , Constants.Ok, Constants.ExitConfirm,  DashboardFragment.this);
            }
        };
        mActivity.getOnBackPressedDispatcher().addCallback(this, callback);


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
        toolBarText = mView.findViewById(R.id.toolBarText);
        logout = mView.findViewById(R.id.logout);


        toolBarText.setText("Dashboard");

        logout.setVisibility(View.VISIBLE);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrigAppPreferences.setLoginPref(mActivity, false);
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_LoginFragment);
            }
        });




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
                toolBarText.setText("Induction Training");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_VideoFragment);
                break;
            case R.id.skillContainer:
                toolBarText.setText("Skill Training");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_VideoFragment);
                break;
            case R.id.constraintLayout3:
                toolBarText.setText("Functional Training");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_VideoFragment);
                break;
            case R.id.otherCoursesContainer:
                toolBarText.setText("Other Courses");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_VideoFragment);
                break;
            case R.id.assessmentContainer:
                toolBarText.setText("Assessments");

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

}
