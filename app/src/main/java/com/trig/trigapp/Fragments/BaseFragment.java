package com.trig.trigapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.trig.trigapp.Adapter.NavDrawerAdapter;
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.R;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;


public class BaseFragment extends Fragment{ /*implements  View.OnClickListener , OnClickInterface {

    private static final String TAG = "BaseFragment";
    FragmentActivity mActivity;
    RecyclerView list;
    TextView toolBarText;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       *//* Toolbar toolbar = mView.findViewById(R.id.toolbar2);

        slidingRootNav = new SlidingRootNavBuilder(mActivity)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withGravity(SlideGravity.LEFT)
                .withContentClickableWhenMenuOpened(true)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        list = mActivity.findViewById(R.id.list);
        setAdapter();

        return mView;*//*
    }

    public void setAdapter(){
        list.setNestedScrollingEnabled(false);
        NavDrawerAdapter asCommonAdapter = new NavDrawerAdapter(mActivity, this);
        list.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(asCommonAdapter);
    }

    private void init(View mView){
        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("Dashboard");
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
                        .navigate(R.id.action_dashboardFrag_to_AssessmentFragment);
                break;
            case R.id.otherCoursesContainer:
                toolBarText.setText("Other Courses");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_VideoFragment);
                break;
            case R.id.courseContainer:
                toolBarText.setText("Assessments");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_ProfileFragment);
                break;
            case R.id.assessmentContainer:
                toolBarText.setText("Assessments");

                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_Contact);
                break;
            case R.id.feedback:
                toolBarText.setText("Feedback Form");
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_FeedbackFragment);
                break;
            case R.id.logout:
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_LoginFragment);
                break;
        }
    }



    public final int POS_DASHBOARD = 0;
    public final int POS_COURCES = 1;
    public final int POS_ASSESSMENT = 2;
    public final int POS_PROFILE = 3;
    public final int POS_CONTACT_US = 4;
    public final int POS_FEEDBACK = 5;
    public final int POS_LOGOUT = 6;
    @Override
    public void onClick(View view, int position) {
        switch (position) {
            case POS_DASHBOARD:
                break;
            case POS_COURCES:
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_VideoFragment);
                break;
            case POS_ASSESSMENT:
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_AssessmentFragment);
                break;
            case POS_PROFILE:
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_ProfileFragment);
                break;
            case POS_CONTACT_US:
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_Contact);
                break;
            case POS_FEEDBACK:
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_FeedbackFragment);
                break;
            case POS_LOGOUT:TrigAppPreferences.setLoginPref(mActivity, false);
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_LoginFragment);
                break;
        }
        slidingRootNav.closeMenu();
    }*/
}
