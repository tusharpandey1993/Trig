package com.trig.trigapp.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;


import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.R;
import com.trig.trigapp.onBoarding.OnboardingDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";

    private View mView;
    private FragmentActivity mActivity;
    String mobileNumber, passWord;
    EditText editMobileNo, edit_password;
    Button btn_login;
    TextView toolBarText;
    ImageView backIcon;
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

        editMobileNo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
        editMobileNo = (EditText) mView.findViewById(R.id.edit_mobile_number);
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
        mobileNumber = editMobileNo.getText().toString();
        passWord = edit_password.getText().toString();
        Log.d(TAG, "checkValidation: " + passWord);
        TrigAppPreferences.setMobileNumber(mActivity, mobileNumber);

        if (mobileNumber.startsWith("0")) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_mobilenumberZero));
        } else if (TextUtils.isEmpty(mobileNumber) || mobileNumber.length() != 10) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_invalid_mobilenumber));
            Utility.getInstance().requestFocus(mActivity, editMobileNo);
        } else if (TextUtils.isEmpty(passWord) && mobileNumber.length() > 1) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_invalid_password));
            Utility.getInstance().requestFocus(mActivity, edit_password);
        }else {
            Log.d(TAG, "checkValidation: passWord " + passWord);
            if(passWord.equalsIgnoreCase("0")){
                TrigAppPreferences.setLoginCategory(mActivity, Constants.getInstance().trainer);
            } else {
                TrigAppPreferences.setLoginCategory(mActivity, Constants.getInstance().user);
            }
            TrigAppPreferences.setLoginPref(mActivity, true);
            TrigAppPreferences.setSource_To_Desitnation(mActivity, Constants.getInstance().loginScreen);

            Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                    .navigate(R.id.action_loginFragment_to_successFragment);

            /*if(passWord.equalsIgnoreCase("1")){
                TrigAppPreferences.setLoginPref(mActivity, true);
                TrigAppPreferences.setLoginCategory(mActivity, Constants.getInstance().user);
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_loginFragment_to_dashboardFragment);
            } else if(passWord.equalsIgnoreCase("0")){
                TrigAppPreferences.setLoginPref(mActivity, true);
                TrigAppPreferences.setLoginCategory(mActivity, Constants.getInstance().trainer);
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_loginFragment_to_dashboardTrainerFragment);
            } else {
                TrigAppPreferences.setLoginPref(mActivity, true);
                TrigAppPreferences.setLoginCategory(mActivity, Constants.getInstance().user);
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_loginFragment_to_dashboardFragment);
            }*/
        }
    }

}
