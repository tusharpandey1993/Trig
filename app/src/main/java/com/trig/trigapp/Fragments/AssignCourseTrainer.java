package com.trig.trigapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.trig.trigapp.Adapter.NavDrawerAdapter;
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.CustomSelectionDialog;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.CommonFiles.ViewDialogCustom;
import com.trig.trigapp.CommonFiles.onDialogClickCallback;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogBuilder;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogClickListener;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogPopup;
import com.trig.trigapp.R;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;

public class AssignCourseTrainer extends BaseFragment implements GenericDialogClickListener, onDialogClickCallback, View.OnClickListener , OnClickInterface {

    private static final String TAG = "ProfileFragment";
    FragmentActivity mActivity;
    View mView;
    TextInputEditText edit_branch, edit_unit;
    ArrayList<String> typeOfList;
    private RecyclerView list;
    private ImageView closeIcon;
    private AppCompatAutoCompleteTextView autoTextView;
    private SlidingRootNav slidingRootNav;
    private String[] branchList = {"Apple", "Appy", "Banana", "Cherry", "Date", "Grape", "Kiwi"};

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
        mView = inflater.inflate(R.layout.assign_courses, container, false);
        init(mView);

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
                    ViewDialogCustom.customDialog2Btn(mActivity, mActivity.getResources().getString(R.string.exitConfirmation),
                            Constants.Cancel, Constants.Ok, Constants.ExitConfirm, AssignCourseTrainer.this);
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
                            .navigate(R.id.action_assign_course_to_LoginFragment);
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(View mView) {
//        edit_branch = mView.findViewById(R.id.edit_branch);

        edit_unit = mView.findViewById(R.id.edit_unit);
        edit_unit = mView.findViewById(R.id.edit_unit);
//        edit_branch.setOnClickListener(this);
        edit_unit.setOnClickListener(this);
        autoTextView = mView.findViewById(R.id.autoTextView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (mActivity, android.R.layout.select_dialog_item, branchList);
        autoTextView.setThreshold(1); //will start working from first character
        autoTextView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            /*case R.id.edit_branch:
                openDialog(typeOfList,0,0,"Category");
                break;*/
            case R.id.edit_unit:
                openDialog(typeOfList,0,0,"Category");
                break;
            case R.id.logout:
                TrigAppPreferences.clear(mActivity);
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_assign_course_to_LoginFragment);
                break;

            case R.id.closeIcon:
                slidingRootNav.closeMenu();
                break;

        }
    }

    public final int POS_DASHBOARD = 0;
    public final int POS_PROFILE = 1;
    public final int POS_CONTACT_US = 2;
    public final int POS_LOGOUT = 3;

    @Override
    public void onClick(View view, int position) {
        try {

            switch (position) {
                case POS_DASHBOARD:
                    break;
                case POS_PROFILE:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_assign_course_to_ProfileFragment);
                    break;
                case POS_CONTACT_US:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_assign_course_to_Contact);
                    break;
                case POS_LOGOUT:TrigAppPreferences.setLoginPref(mActivity, false);
                    GenericDialogPopup genericDialogPopup = null;
                    GenericDialogBuilder genericDialogBuilder = new GenericDialogBuilder.Builder()
                            .setShowCloseButton(false)
                            .setHeading(mActivity.getResources().getString(R.string.logoutHeading))
                            .setDescription(mActivity.getResources().getString(R.string.appLogout))
                            .setPositiveButtonText(Constants.LOGOUT)
                            .setNegativeButtonText(Constants.Cancel)
                            .setGenericDialogClickListener(AssignCourseTrainer.this)
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

    private void openDialog(ArrayList<String> typeOfList, int pos, int po, String title) {
        try {
            CustomSelectionDialog cdd = new CustomSelectionDialog(mActivity, typeOfList, pos, po, title);
            cdd.show();
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

}
