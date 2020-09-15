package com.trig.trigapp.Fragments;


import android.content.Context;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private View mView;
    private FragmentActivity mActivity;
    String mobileNumber, emailid;
    EditText editMobileNo, editEmailId;
    Button btn_login;
    TextView toolBarText;

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
        btn_login = (Button) mView.findViewById(R.id.btn_login);
        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("Login");
//        editEmailId = (EditText) mView.findViewById(R.id.edit_email_id);
    }


    public void checkValidation() {
        mobileNumber = editMobileNo.getText().toString();
        TrigAppPreferences.setMobileNumber(mActivity, mobileNumber);
//        emailid = editEmailId.getText().toString();

        if (mobileNumber.startsWith("0")) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_mobilenumberZero));
        } else if (TextUtils.isEmpty(mobileNumber) || mobileNumber.length() != 10) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_invalid_mobilenumber));
        } else {
            Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                    .navigate(R.id.action_loginFragment_to_otpFragment);
        }

    }



}
