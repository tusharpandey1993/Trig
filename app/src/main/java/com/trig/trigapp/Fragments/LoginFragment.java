package com.trig.trigapp.Fragments;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;


import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogBuilder;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogClickListener;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogPopup;
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Request.LoginRequest;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.api.Response.LoginResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements IPresenter, GenericDialogClickListener {
    private static final String TAG = "LoginFragment";

    private View mView;
    private FragmentActivity mActivity;
    String UserName, passWord;
    EditText editUserName, edit_password;
    Button btn_login;
    TextView toolBarText;
    ImageView backIcon;
    ViewModel viewModel;
    public LoginFragment() {
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
        mView = inflater.inflate(R.layout.fragment_login, container, false);

        init(mView);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        viewModel = new ViewModel(mActivity,this);

        editUserName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Utility.getInstance().hideKeyboard(mActivity);
                    checkValidation();
                    return true;
                }
                return false;
            }
        });

        edit_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Utility.getInstance().hideKeyboard(mActivity);
                    checkValidation();
                    return true;
                }
                return false;
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });
        return mView;

    }

    private void init(View  mView) {
        editUserName = (EditText) mView.findViewById(R.id.edit_UserName);
        edit_password = (EditText) mView.findViewById(R.id.edit_password);
        btn_login = (Button) mView.findViewById(R.id.btn_login);
        toolBarText = mView.findViewById(R.id.toolBarText);
        backIcon = mView.findViewById(R.id.backIcon);
        backIcon.setVisibility(View.GONE);
        toolBarText.setText("Login");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Utility.getInstance().arePermissionsEnabled(mActivity)) {
            } else {
                Utility.getInstance().requestMultiplePermissions(mActivity);
            }
        }
//        editEmailId = (EditText) mView.findViewById(R.id.edit_email_id);
    }


    public void checkValidation() {
        UserName = editUserName.getText().toString();
        passWord = edit_password.getText().toString();
        Log.d(TAG, "checkValidation: " + passWord);
        TrigAppPreferences.setMobileNumber(mActivity, UserName);

        /*if (mobileNumber.startsWith("0")) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_mobilenumberZero));
        } else if (TextUtils.isEmpty(mobileNumber) || mobileNumber.length() != 10) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_invalid_mobilenumber));
            Utility.getInstance().requestFocus(mActivity, editMobileNo);
        } else */
         if (TextUtils.isEmpty(UserName)) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_invalid_username));
            Utility.getInstance().requestFocus(mActivity, editUserName);
        } else if (TextUtils.isEmpty(passWord) && UserName.length() > 1) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_invalid_password));
            Utility.getInstance().requestFocus(mActivity, edit_password);
        }else {
            Log.d(TAG, "checkValidation: passWord " + passWord);
            if(passWord.equalsIgnoreCase("0")){
                TrigAppPreferences.setLoginCategory(mActivity, Constants.getInstance().trainer);
            } else {
                if(Utility.getInstance().isNetworkAvailable(mActivity)) {
                    LoginRequest request = new LoginRequest(UserName, passWord);
                    viewModel.callLoginApi(request);
                }else{
                    ShowGenericDialog(Constants.getInstance().NoInternetCase, Constants.OK, mActivity.getResources().getString(R.string.no_internet_message),"");
                }
            }
        }
    }

    public void ShowGenericDialog(int FunctionNum,String ButtonText,String DialogMessage, String dialogHeading){
        GenericDialogBuilder genericDialogBuilder = new GenericDialogBuilder.Builder()
                .setShowCloseButton(false)
                .setHeading(dialogHeading.isEmpty()?mActivity.getResources().getString(R.string.DialogHeading): dialogHeading)
                .setDescription(DialogMessage)
                .setPositiveButtonText(ButtonText)
                .setGenericDialogClickListener(this)
                .setFucntionNumber(FunctionNum)
                .build();
        showDynamicDialog(genericDialogBuilder);
    }

    public void showDynamicDialog(GenericDialogBuilder genericDialogBuilder) {
        GenericDialogPopup genericDialogPopup = new GenericDialogPopup(genericDialogBuilder);
        genericDialogPopup.show(mActivity.getSupportFragmentManager(), TAG);
    }



    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onError(String error, int code) {

    }

    @Override
    public void onError(Object error) {

    }

    @Override
    public void onError(Object error, int Code) {

    }

    @Override
    public void onResponse(LoginResponse loginResponse) {
        TrigAppPreferences.setLoginCategory(mActivity, Constants.getInstance().user);
        TrigAppPreferences.setLoginPref(mActivity, true);
        TrigAppPreferences.setSource_To_Desitnation(mActivity, Constants.getInstance().loginScreen);

        Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                .navigate(R.id.action_loginFragment_to_successFragment);
    }

    @Override
    public void onPositiveButtonClick(View view, int FucntionNumber) {

    }

    @Override
    public void onNegativeButtonClick(View view, int FucntionNumber) {

    }

    @Override
    public void onDialogCloseButtonClick(View view, int FucntionNumber) {

    }
}
