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
import com.trig.trigapp.Adapter.QuizPayLoadModel;
import com.trig.trigapp.Adapter.OnClickInterface;
import com.trig.trigapp.Adapter.QuizAdapter;
import com.trig.trigapp.Adapter.QuizModel;
import com.trig.trigapp.Adapter.QuizPayLoadModel;
import com.trig.trigapp.AssessmentFactory.TriviaQuestion;
import com.trig.trigapp.AssessmentFactory.TriviaQuizHelper;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.CommonFiles.Constants;
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
    private List<QuizModel> quizModelList;
    private Button end_test_button;
    private int[] intArray = new int[3];
    private int assismentId;
    private HashMap<Integer,String > hashMap;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
        hashMap = new HashMap<Integer,String>();
    }


    public AssessmentFragmentNew() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.quiz_layout, container, false);

        init(mView);

        viewModel.callLoadAssessment(2, TrigAppPreferences.getUserId(mActivity), Constants.getInstance().ATTEMPT);

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

        return mView;
    }


    public void setQuizAdapter() {
        quizListRecycler.setNestedScrollingEnabled(false);
        QuizAdapter quizAdapter = new QuizAdapter(mActivity, this, getLoadAssignmentsRes);
        quizListRecycler.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        quizListRecycler.setAdapter(quizAdapter);
    }

    @Override
    public void onClickQuiz(View view, int position, int id, int assismentID, String optionID) {

        quizModel.setPosition(position);
        quizModel.setSelectedID(id);

        assismentId = assismentID;

        switch (position) {
            case 0:
                hashMap.put(position, optionID+";");
            break;
            case 1:
                hashMap.put(position, optionID+";");
            break;
            case 2:
                hashMap.put(position, optionID+";");
            break;
            case 3:
                hashMap.put(position, optionID+";");
            break;
        }
    }

    private void backToPreviousFragment() {
        Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.action_AssessmentFrag_to_CoursesTopics);

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
        switch (view.getId()) {
            case R.id.end_test_button:
                if (Utility.getInstance().isNetworkAvailable(mActivity)) {
                    if (getLoadAssignmentsRes.size() == hashMap.size()) {
                        StringBuffer output = new StringBuffer(5);

                        for (int i = 0; i < hashMap.size(); i++) {
                            output.append(hashMap.get(i));
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
                    Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.no_internet_message));
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    @Override
    public void onResponseLoadAssessmentQuestions(JsonArray jsonArray) {
        getLoadAssignmentsRes = new ArrayList<>();
        for(int i =0; i < jsonArray.size(); i++) {
            getLoadAssignmentsRes getCourseListRes = Utility.getInstance().getG().fromJson(jsonArray.get(i), getLoadAssignmentsRes.class);
            getLoadAssignmentsRes.add(getCourseListRes);
        }
        setQuizAdapter();
    }


}
