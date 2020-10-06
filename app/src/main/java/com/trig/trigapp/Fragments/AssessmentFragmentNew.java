package com.trig.trigapp.Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.Adapter.QuizAdapter;
import com.trig.trigapp.Adapter.QuizModel;

import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.CommonFiles.NoInternetDialog;
import com.trig.trigapp.CommonFiles.TrigAppPreferences;
import com.trig.trigapp.CommonFiles.Utility;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogBuilder;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogPopup;
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Request.SubmitAssessmentReq;
import com.trig.trigapp.api.Response.getCourseListRes;
import com.trig.trigapp.api.Response.getLoadAssignmentsRes;
import com.trig.trigapp.onBoarding.OnboardingDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import info.hoang8f.widget.FButton;

public class AssessmentFragmentNew extends BaseFragment implements IPresenter, View.OnClickListener, RadioGroup.OnCheckedChangeListener, OnClickInterface {


    private static final String TAG = AssessmentFragmentNew.class.getSimpleName();

    private View mView;
    private FragmentActivity mActivity;
    public TextView toolBarText;
    private ImageView backIcon;
    private RecyclerView quizListRecycler;
    private ViewModel viewModel;
    private List<getLoadAssignmentsRes> getLoadAssignmentsRes;
    private QuizModel quizModel = new QuizModel();
    private Button end_test_button;
    private int assismentId;
    private static HashMap<Integer,String > hashMap = new HashMap<Integer,String>();
    private ArrayList<Integer> assessmentQuestionIdList = new ArrayList<>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    public AssessmentFragmentNew() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        try {

            mView = inflater.inflate(R.layout.quiz_layout, container, false);

            init(mView);

            if (Constants.getInstance().Assessment_1st_status.equalsIgnoreCase(Constants.getInstance().completed)) {
                viewModel.callLoadAssessment(Constants.getInstance().Assessment_1st_item, TrigAppPreferences.getUserId(mActivity), Constants.getInstance().REVIEW);
                end_test_button.setVisibility(View.GONE);
            } else if(Constants.getInstance().Assessment_1st_status.equalsIgnoreCase(Constants.getInstance().not_started)){
                viewModel.callLoadAssessment(Constants.getInstance().Assessment_1st_item, TrigAppPreferences.getUserId(mActivity), Constants.getInstance().ATTEMPT);
            }

            OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
                @Override
                public void handleOnBackPressed() {
                    backToPreviousFragment();
                }
            };
            requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
            backIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    backToPreviousFragment();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "onCreateView: exception " + e.getMessage() );
        }

        return mView;
    }


    public void setQuizAdapter() {
        try {
            quizListRecycler.setNestedScrollingEnabled(false);
            QuizAdapter quizAdapter = new QuizAdapter(mActivity, this, getLoadAssignmentsRes);
            quizListRecycler.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
            quizListRecycler.setAdapter(quizAdapter);
        } catch (Exception e) {
            Log.e(TAG, "setQuizAdapter: exception " + e.getMessage());
        }
    }

    @Override
    public void onClickQuiz(View view, int position, int id, int assismentID, String optionID, int assessmentQuestionId) {

        try {
            quizModel.setPosition(position);
            quizModel.setSelectedID(id);

            assismentId = assismentID;

            if(assessmentQuestionIdList!= null && !assessmentQuestionIdList.contains(assessmentQuestionId)){
                assessmentQuestionIdList.add(assessmentQuestionId);
            }

            switch (position) {
                case 0:
                    hashMap.put(assessmentQuestionId, optionID + ";");
                    break;
                case 1:
                    hashMap.put(assessmentQuestionId, optionID + ";");
                    break;
                case 2:
                    hashMap.put(assessmentQuestionId, optionID + ";");
                    break;
                case 3:
                    hashMap.put(assessmentQuestionId, optionID + ";");
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "onClickQuiz: exception " + e.getMessage());
        }
    }

    private void backToPreviousFragment() {
        try {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_AssessmentFrag_to_CoursesTopics);
        } catch (Exception e) {
            Log.e(TAG, "backToPreviousFragment: exception " + e.getMessage());
        }

    }

    public void init(View mView) {
        viewModel = new ViewModel(mActivity, this);

        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText(Constants.getInstance().Assessments);
        backIcon = mView.findViewById(R.id.backIcon);
        quizListRecycler = mView.findViewById(R.id.quizListRecycler);
        end_test_button = mView.findViewById(R.id.end_test_button);

        end_test_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.end_test_button:
                    if (Utility.getInstance().isNetworkAvailable(mActivity)) {
                        if (getLoadAssignmentsRes != null && assessmentQuestionIdList != null) {
                            if (getLoadAssignmentsRes.size() == assessmentQuestionIdList.size()) {
                                StringBuffer output = new StringBuffer(5);
                                for (HashMap.Entry<Integer, String> entry : hashMap.entrySet()) {
                                    output.append(entry.getValue());
                                }
                                SubmitAssessmentReq submitAssessmentReq = new SubmitAssessmentReq();
                                submitAssessmentReq.setAssessment_id(assismentId);
                                submitAssessmentReq.setUser_id(TrigAppPreferences.getUserId(mActivity));
                                submitAssessmentReq.setOptString(String.valueOf(output));

                                viewModel.submitAssessment(submitAssessmentReq);

                            } else {
                                Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.answer_all_questions));
                            }
                        } else {
                            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.answer_all_questions));
                        }
                    } else {
                        Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.no_internet_message));
                    }
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseLoadAssessmentQuestions: exception " + e.getMessage());
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    @Override
    public void onResponseLoadAssessmentQuestions(JsonArray jsonArray) {
        try {
            if(jsonArray != null) {
                getLoadAssignmentsRes = new ArrayList<>();
                for(int i =0; i < jsonArray.size(); i++) {
                    getLoadAssignmentsRes getCourseListRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), getLoadAssignmentsRes.class);
                    getLoadAssignmentsRes.add(getCourseListRes);
                }
                setQuizAdapter();
            }
        } catch (Exception e) {
            Log.e(TAG, "onResponseLoadAssessmentQuestions: exception " + e.getMessage() );
        }

    }

    @Override
    public void onResponseSubmitAssessment() {
        try {
            TrigAppPreferences.setSource_To_Desitnation(mActivity, Constants.getInstance().assessment);
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_AssessmentFrag_to_SuccessFragment);
        } catch (Exception e) {
            Log.e(TAG, "onResponseSubmitAssessment: exception " + e.getMessage());
        }
    }
}
