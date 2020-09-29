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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.CommonResponse;
import com.trig.trigapp.api.Response.getFeedbackRes;

public class FeedbackFragment extends Fragment implements IPresenter, View.OnClickListener {
    private static final String TAG = "FeedbackFragment";

    private View mView;
    private FragmentActivity mActivity;
    private TextView toolBarText;
    private Button btn_submit;
    private String feedBackText, feebackRemarkText, feedbackByText, feedbackOnText;
    private TextInputEditText feedback, suggestion, FeedbackBy, FeedbackOn;
    private ImageView backIcon;
    private ViewModel viewModel;



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
        viewModel.callgetFeedback("9919");
        getFeedbackRes getFeedbackRes = Utility.getInstance().getG().fromJson(TrigAppPreferences.getFeedbackApiResponse(mActivity), getFeedbackRes.class);
        if(getFeedbackRes != null){
            if(!getFeedbackRes.getFeedback().isEmpty() || !getFeedbackRes.getRemarksSuggestion().isEmpty() || !getFeedbackRes.getFeedbackBy().isEmpty() || !getFeedbackRes.getFeedbackOn().isEmpty()){
                feedback.setText(getFeedbackRes.getFeedback());
                suggestion.setText(getFeedbackRes.getRemarksSuggestion());
                FeedbackBy.setText(getFeedbackRes.getFeedbackBy());
                FeedbackOn.setText(getFeedbackRes.getFeedbackOn());
            }
        }

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                goBackToPreviousPageNav();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(mActivity, callback);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToPreviousPageNav();
            }
        });

        return mView;
    }

    private void goBackToPreviousPageNav() {
        Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.action_Feedback_to_Dasboard);
    }

    private void init(View mView) {
        viewModel = new ViewModel(mActivity, this);
        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("Feedback Form");

        feedback = mView.findViewById(R.id.feedback);
        suggestion = mView.findViewById(R.id.suggestion);
        FeedbackBy = mView.findViewById(R.id.FeedbackBy);
        FeedbackOn = mView.findViewById(R.id.FeedbackOn);

        btn_submit = mView.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        backIcon = mView.findViewById(R.id.backIcon);
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
        feedback.setKeyListener(null);
        feedBackText = feedback.getText().toString();
        feebackRemarkText = suggestion.getText().toString();
        feedbackByText = FeedbackBy.getText().toString();
        feedbackOnText = FeedbackOn.getText().toString();

        if (TextUtils.isEmpty(feedBackText) && feedBackText.length() < 1) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), "Feedback "+ getResources().getString(R.string.error_empty_field));
            Utility.getInstance().requestFocus(mActivity, feedback);
        } else if (TextUtils.isEmpty(feebackRemarkText) && feedBackText.length() < 1) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), "Suggestion/Remarks "+getResources().getString(R.string.error_empty_field));
            Utility.getInstance().requestFocus(mActivity, suggestion);
        } else if (TextUtils.isEmpty(feedbackByText) && feedBackText.length() < 1) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), "Feedback By "+getResources().getString(R.string.error_empty_field));
            Utility.getInstance().requestFocus(mActivity, FeedbackBy);
        } else if (TextUtils.isEmpty(feedbackOnText) && feedBackText.length() < 1) {
            Utility.getInstance().hideKeyboard(mActivity);
            Utility.getInstance().showSnackbar(getView(), "Feedback On "+getResources().getString(R.string.error_empty_field));
            Utility.getInstance().requestFocus(mActivity, FeedbackOn);
        } else {
            TrigAppPreferences.setSource_To_Desitnation(mActivity, Constants.getInstance().feedback);
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_dashboardFrag_to_SuccessFragment);
        }
    }

    @Override
    public void onResponseFeedback(getFeedbackRes getFeedbackRes) {
        if (getFeedbackRes != null && !Utility.getInstance().getG().toJson(getFeedbackRes).equals("{}")) {
            TrigAppPreferences.setFeedbackApiResponse(mActivity, getFeedbackRes.toString());
            if(!getFeedbackRes.getFeedback().isEmpty() || !getFeedbackRes.getRemarksSuggestion().isEmpty() || !getFeedbackRes.getFeedbackBy().isEmpty() || !getFeedbackRes.getFeedbackOn().isEmpty()){
                feedback.setText(getFeedbackRes.getFeedback());
                suggestion.setText(getFeedbackRes.getRemarksSuggestion());
                FeedbackBy.setText(getFeedbackRes.getFeedbackBy());
                FeedbackOn.setText(getFeedbackRes.getFeedbackOn());
            }
        }
    }
}
