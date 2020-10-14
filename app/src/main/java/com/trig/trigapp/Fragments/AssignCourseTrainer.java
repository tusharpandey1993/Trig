package com.trig.trigapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import java.util.ArrayList;

public class AssignCourseTrainer extends BaseFragment implements GenericDialogClickListener,  onDialogClickCallback, View.OnClickListener , OnClickInterface, IPresenter {

    private static final String TAG = "DashboardTrainer";
    private FragmentActivity mActivity;
    private View mView;
    private TextInputEditText edit_branch, edit_unit,resetOneEmpET;
    private ArrayList<String> typeOfList;
    private RecyclerView list, filteredListRecycler;
    private ImageView closeIcon;
    private SlidingRootNav slidingRootNav;
    private ArrayList<GetUnitRes> getUnitResArrayList;
    private ArrayList<GetBranchRes> getBranchResArrayList;
    private ArrayList<UserListResponse> userListResponseArrayList;
    private ViewModel viewModel;
    ImageView backIcon;
    private CustomSelectionDialog cdd;
    private CheckBox assignCourseCB, assignAssessCB;
    private Button reassignEmp, reassignAll;
    private Toolbar toolbar;

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

        list = mActivity.findViewById(R.id.list);
        closeIcon = mActivity.findViewById(R.id.closeIcon);
        closeIcon.setOnClickListener(this);
        setAdapter();

        return mView;
    }

    private void moveBackNavigation() {
        if (TrigAppPreferences.getUser_Type(mActivity).equalsIgnoreCase(Constants.getInstance().trainer)) {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_AssignCourse_to_dashboardTrainerFragment);
        }
    }

    public void setAdapter(){
        list.setNestedScrollingEnabled(false);
        NavDrawerAdapter asCommonAdapter = new NavDrawerAdapter(mActivity, this,0);
        list.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(asCommonAdapter);
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
        toolbar                 = mView.findViewById(R.id.toolbar2);
        viewModel               = new ViewModel(mActivity, this);
        edit_branch             = mView.findViewById(R.id.edit_branch);
        edit_unit               = mView.findViewById(R.id.edit_unit);
        assignCourseCB          = mView.findViewById(R.id.assignCourseCB);
        assignAssessCB          = mView.findViewById(R.id.assignAssessCB);
        reassignEmp             = mView.findViewById(R.id.reassignEmp);
        reassignAll             = mView.findViewById(R.id.reassignAll);
        resetOneEmpET           = mView.findViewById(R.id.resetOneEmpET);
        filteredListRecycler    = mView.findViewById(R.id.filteredListRecycler);



        backIcon = mView.findViewById(R.id.backIcon);

        edit_branch.setOnClickListener(this);
        edit_unit.setOnClickListener(this);
        reassignEmp.setOnClickListener(this);
        reassignAll.setOnClickListener(this);
        resetOneEmpET.setOnClickListener(this);

    }

    @Override
    public void onClick(View view, int position, String selectedValue,String selectedID,String title) {
        if(title.equalsIgnoreCase(Constants.getInstance().Branch)){
            edit_branch.setText(selectedValue);
            showLoader();
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

            viewModel.getUserList(trainerDashboardReq);
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
                openDialog(Constants.getInstance().EMPCode);
                break;
            case R.id.logout:
                TrigAppPreferences.clear(mActivity);
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_dashboardFrag_to_LoginFragment);
                break;

            case R.id.closeIcon:
                slidingRootNav.closeMenu();
                break;

            case R.id.reassignEmp:
                hitAssignAllApi(true);
                break;

            case R.id.reassignAll:
                hitAssignAllApi(false);
                break;
        }
    }

    private void hitAssignAllApi(boolean singleUser) {
        try {
            if (Utility.getInstance().isNetworkAvailable(mActivity)) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                AssignCoursesToEmp assignCoursesToEmp = new AssignCoursesToEmp();
                if (singleUser) {
                    assignCoursesToEmp.setUserName(TrigAppPreferences.getUserName(mActivity));
                }
                if (assignAssessCB != null && assignAssessCB.isChecked()) {
                    assignCoursesToEmp.setAssessment("1");
                } else {
                    assignCoursesToEmp.setAssessment("0");
                }
                if (assignCourseCB != null && assignCourseCB.isChecked()) {
                    assignCoursesToEmp.setCourse("1");
                } else {
                    assignCoursesToEmp.setCourse("0");
                }
                assignCoursesToEmp.setTrainer_emp_code(Long.parseLong(TrigAppPreferences.getEmployee_Code(mActivity)));

                viewModel.assignCourseTrainerToEmp(assignCoursesToEmp);

            } else {
                Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.no_internet_message));
            }
        } catch (Exception e) {
            Log.e(TAG, "hitAssignAllApi: " + e.getMessage());
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

    private void openDialog(String title) {
        try {
            DataPayload payload = null;
            if(title.equalsIgnoreCase("Branch")){
                if(getBranchResArrayList!=null) {
                    payload = new DataPayload();
                    payload.setTitle(title);
                    payload.setGetBranchResArrayList(getBranchResArrayList);
                }
            }else if(title.equalsIgnoreCase("Unit")){
                if(getUnitResArrayList!=null) {
                    payload = new DataPayload();
                    payload.setTitle(title);
                    payload.setGetUnitResArrayList(getUnitResArrayList);
                }
            }else{
                if(userListResponseArrayList!=null) {
                    payload = new DataPayload();
                    payload.setTitle(title);
                    payload.setUserListResponses(userListResponseArrayList);
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
            hideLoader();
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
                        Log.d(TAG, "onResponseGetUserList: response " + i + userListResponseArrayList.get(i));
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "onResponsegetUnit: exception " + e.getMessage());
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseDashboardTrainer: exception" + e.getMessage());
        }
    }

    @Override
    public void onResponseAssignCourseRes(String string) {
        Log.d(TAG, "onResponseAssignCourseRes: " + string);
    }
}

