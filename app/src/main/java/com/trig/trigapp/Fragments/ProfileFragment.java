package com.trig.trigapp.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.MobileConnectPermissions;
import com.trig.trigapp.CommonFiles.PermissionCallback;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.api.Response.CommonResponse;

import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.fragment.app.FragmentActivity;
import androidx.activity.OnBackPressedCallback;

public class ProfileFragment extends BaseFragment implements IPresenter, View.OnClickListener {

    private static final String TAG = "ProfileFragment";
    FragmentActivity mActivity;
    View mView;
    private ImageView backgroundImageChange2;
    public static ImageView profileImg;
    ImageView backIcon;
    private TextView toolBarText, name, email, employeeCode, designation, gender, address, dob, doJoining, age;
    private ViewModel viewModel;


    public ProfileFragment() {
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
        mView = inflater.inflate(R.layout.profile, container, false);
        MobileConnectPermissions.init(mActivity);
        init(mView);

        if (Utility.getInstance().isNetworkAvailable(mActivity)) {
            showLoader();
            viewModel.callProfileApi("9919","user");
        } else {
//            ShowGenericDialog(Constants.getInstance().NoInternetCase, Constants.OK, mActivity.getResources().getString(R.string.no_internet_message),"");
        }


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
        Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.action_profile_to_dashboardFragment);
    }

    private void init(View mView) {
        viewModel = new ViewModel(mActivity, this);
        backgroundImageChange2 = mView.findViewById(R.id.backgroundImageChange2);
        profileImg = mView.findViewById(R.id.profileImg);
        backIcon = mView.findViewById(R.id.backIcon);
        toolBarText = mView.findViewById(R.id.toolBarText);
        name = mView.findViewById(R.id.name);
        email = mView.findViewById(R.id.email);
        employeeCode = mView.findViewById(R.id.employeeCode);
        designation = mView.findViewById(R.id.designation);
        gender = mView.findViewById(R.id.gender);
        address = mView.findViewById(R.id.address);
        dob = mView.findViewById(R.id.dob);
        doJoining = mView.findViewById(R.id.doJoining);
        age = mView.findViewById(R.id.age);


        toolBarText.setText("User Profile");
        backgroundImageChange2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backgroundImageChange2:
//                checkPermissionAndThenLoad();

                break;

        }
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    public void showProgressDialog() {
        hideLoader();
    }

    @Override
    public void hideProgressDialog() {
        hideLoader();
    }

    @Override
    public void onError(String error) {
        hideLoader();
    }

    @Override
    public void onError(String error, int code) {
        hideLoader();
    }

    @Override
    public void onError(Object error) {
        hideLoader();
    }

    @Override
    public void onError(Object error, int Code) {
        hideLoader();
    }

    @Override
    public void onResponseProfile(CommonResponse commonResponse) {
        try {
            hideLoader();
            if (commonResponse != null && !Utility.getInstance().getG().toJson(commonResponse).equals("{}")) {
                name.setText(commonResponse.getUsername());
                email.setText(commonResponse.getEmailId());
                employeeCode.setText(commonResponse.getTirgEmpCode());
                designation.setText(commonResponse.getDesignation());
                gender.setText(commonResponse.getGender());
                address.setText(commonResponse.getAddress());
                dob.setText(commonResponse.getDob());
                doJoining.setText(commonResponse.getDoj());
                age.setText(commonResponse.getAge());
                Glide.with(mActivity).load(commonResponse.getEmp_photo_path()).centerCrop().placeholder(R.drawable.trig_bg).into(profileImg);
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }


    private boolean checkPermissionAndThenLoad() {
        if (MobileConnectPermissions.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE) && MobileConnectPermissions.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            selectImage(mActivity);
        } else {
            if (MobileConnectPermissions.shouldShowRequestPermissionRationale(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                MobileConnectPermissions.askForPermission(mActivity,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE
                                , android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionReadstorageCallback);
            } else {
                MobileConnectPermissions.askForPermission(mActivity, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        permissionReadstorageCallback);
            }
        }
        return false;
    }

    private final PermissionCallback permissionReadstorageCallback = new PermissionCallback() {
        @Override
        public void permissionGranted() {
            selectImage(mActivity);
        }

        @Override
        public void permissionRefused() {
            checkPermissionAndThenLoad();
        }
    };


}
