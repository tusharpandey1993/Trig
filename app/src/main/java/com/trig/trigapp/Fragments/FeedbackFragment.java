package com.trig.trigapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.R;

public class FeedbackFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "FeedbackFragment";

    private View mView;
    private FragmentActivity mActivity;
    private TextView toolBarText;
    private Button btn_submit;
    private String feedBackText, feebackRemarkText, feedbackByText, feedbackOnText;
    private TextInputEditText feedback, suggestion, FeedbackBy, FeedbackOn;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_feedback, container, false);

        init(mView);

        return mView;
    }

    private void init(View mView) {
        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("Feedback");

        feedback = mView.findViewById(R.id.feedback);
        suggestion = mView.findViewById(R.id.suggestion);
        FeedbackBy = mView.findViewById(R.id.FeedbackBy);
        FeedbackOn = mView.findViewById(R.id.FeedbackOn);

        btn_submit = mView.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:
                checkValidation();
                break;
            default:
                break;
        }
    }


    public void checkValidation() {
        feedBackText = feedback.getText().toString();
        feebackRemarkText = suggestion.getText().toString();
        feedbackByText = FeedbackBy.getText().toString();
        feedbackOnText = FeedbackOn.getText().toString();

        if (TextUtils.isEmpty(feedBackText) && feedBackText.length() < 1) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), "Feedback"+ getResources().getString(R.string.error_empty_field));
            Utility.getInstance().requestFocus(mActivity, feedback);
        } else if (TextUtils.isEmpty(feebackRemarkText) && feedBackText.length() < 1) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), "Suggestion/Remarks"+getResources().getString(R.string.error_empty_field));
            Utility.getInstance().requestFocus(mActivity, suggestion);
        } else if (TextUtils.isEmpty(feedbackByText) && feedBackText.length() < 1) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), "Feedback By"+getResources().getString(R.string.error_empty_field));
            Utility.getInstance().requestFocus(mActivity, FeedbackBy);
        } else if (TextUtils.isEmpty(feedbackOnText) && feedBackText.length() < 1) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), "Feedback On"+getResources().getString(R.string.error_empty_field));
            Utility.getInstance().requestFocus(mActivity, FeedbackOn);
        } else {
            TrigAppPreferences.setSource_To_Desitnation(mActivity, Constants.getInstance().feedback);
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_dashboardFrag_to_SuccessFragment);
        }
    }
}
