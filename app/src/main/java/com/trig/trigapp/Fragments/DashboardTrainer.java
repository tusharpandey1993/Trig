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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonArray;
import com.trig.trigapp.Adapter.CourseTopicAdapter;
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

    private static final String TAG = "ProfileFragment";
    FragmentActivity mActivity;
    View mView;
    TextInputEditText edit_branch, edit_unit;
    ArrayList<String> typeOfList;
    private RecyclerView list;
    private ImageView closeIcon;
    private SlidingRootNav slidingRootNav;
    private String[] branchList = new String[0];
    private ArrayList<GetUnitRes> getUnitResArrayList;
    private ViewModel viewModel;
    CustomSelectionDialog cdd;
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
        mView = inflater.inflate(R.layout.assign_course_trainer, container, false);
        init(mView);

        viewModel.getBranch(new User_id(TrigAppPreferences.getUserId(mActivity)));
        CommonReq commonReq = new CommonReq();
        commonReq.setBranchId(""+2);
        viewModel.getUnit(commonReq);

        TrainerDashboardReq trainerDashboardReq = new TrainerDashboardReq();
        trainerDashboardReq.setEmp_code("9082461859");
        trainerDashboardReq.setUnitId(4755);
        viewModel.getTrainerDashboard(trainerDashboardReq);

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
                            Constants.Cancel, Constants.Ok, Constants.ExitConfirm, DashboardTrainer.this);
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

    private void init(View mView) {
        edit_branch = mView.findViewById(R.id.edit_branch);
        edit_unit = mView.findViewById(R.id.edit_unit);
        edit_branch.setOnClickListener(this);
        edit_unit.setOnClickListener(this);

        viewModel = new ViewModel(mActivity, this);

    }

    @Override
    public void onClick(View view, int position, String selectedValue,String title) {
        if(title.equalsIgnoreCase("Branch")){
            edit_branch.setText(selectedValue);
            edit_unit.setEnabled(true);
            edit_unit.setHintTextColor(getResources().getColor(R.color.hint_color));
        }else if(title.equalsIgnoreCase("Unit")){
            edit_unit.setText(selectedValue);
        }
        cdd.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.edit_branch:
                openDialog(typeOfList,"Branch");
                break;
            case R.id.edit_unit:
                openDialog(typeOfList,"Unit");
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
                            .navigate(R.id.action_dashboardFrag_to_ProfileFragment);
                    break;
                case POS_REPORT:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_ProfileFragment);
                    break;
                case POS_PROFILE:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_ProfileFragment);
                    break;
                case POS_CONTACT_US:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_Contact);
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

    private void openDialog(ArrayList<String> typeOfList, String title) {
        try {
            typeOfList = new ArrayList<>();

            typeOfList.add("Ramiz");
            typeOfList.add("Belal");
            typeOfList.add("Azad");
            typeOfList.add("Manish");
            typeOfList.add("Sunny");
            typeOfList.add("Shahid");
            typeOfList.add("Deepak");
            typeOfList.add("Deepika");
            typeOfList.add("Sumit");
            typeOfList.add("Mehtab");
            typeOfList.add("Vivek");

            cdd = new CustomSelectionDialog(mActivity, typeOfList, title,this);
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

    @Override
    public void onResponsegetBranch(JsonArray jsonArray) {
        try {
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    GetBranchRes getBranchRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), GetBranchRes.class);
                    Log.d(TAG, "onResponsegetBranch:1L " + branchList.length);
                    branchList = increaseArray(new String[]{getBranchRes.getBranchName()});
                    Log.d(TAG, "onResponsegetBranch:2L " + branchList.length);
                }


            }

            for (int i =0; i< branchList.length;i++){
                Log.d(TAG, "onResponsegetBranch: " + branchList[i]);
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponsegetBranch: exception " + e.getMessage());
        }
    }

    public String[] increaseArray(String[] theArray) {
        int i = theArray.length;
        int n = ++i;
        String[] newArray = new String[n];
        for (int count = 0; count < theArray.length; count++) {
            newArray[count] = theArray[count];
        }
        return newArray;
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

            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseDashboardTrainer: exception" + e.getMessage());
        }
    }
}
