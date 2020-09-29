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
import com.trig.trigapp.MVP.IPresenter;
import com.trig.trigapp.MVP.ViewModel;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.getCourseListRes;
import com.trig.trigapp.api.Response.getLoadAssignmentsRes;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.widget.FButton;

public class AssessmentFragmentNew extends Fragment implements IPresenter, View.OnClickListener, RadioGroup.OnCheckedChangeListener, OnClickInterface {


    private static final String TAG = "AssessmentFragmentNew";
    private View mView;
    private FragmentActivity mActivity;
    public TextView toolBarText;
    int score = 0;
    RadioGroup question_1;


    FButton buttonA, buttonB, buttonC, buttonD;
    TextView questionText, triviaQuizText, timeText, resultText, coinText;
    TriviaQuizHelper triviaQuizHelper;
    TriviaQuestion currentQuestion;
    List<TriviaQuestion> list;
    int qid = 0;
    int timeValue = 20;
    int coinValue = 0;
    CountDownTimer countDownTimer;
    Typeface tb, sb;
    String test_result;
    Button endTestButton;
    ImageView backIcon;
    RecyclerView quizListRecycler;
    List<QuizPayLoadModel> quizPayLoadModelList;
    private ViewModel viewModel;
    private List<getLoadAssignmentsRes> getLoadAssignmentsRes;

    private QuizModel quizModel = new QuizModel();
    private List<QuizModel> quizModelList;

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

        if (quizModelList != null) {

        } else {
            quizModelList = new ArrayList<>();
            quizModelList.add(quizModel);
        }

        Log.e(TAG, "onClickQuiz: "+new Gson().toJson(quizModelList));
    }

    private void backToPreviousFragment() {

        Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.action_AssessmentFrag_to_CoursesTopics);

    }

    public void init(View mView) {
//        end_Test(mView);
        viewModel = new ViewModel(mActivity, this);
        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("Assessment");
        backIcon = mView.findViewById(R.id.backIcon);
        quizListRecycler = mView.findViewById(R.id.quizListRecycler);
        EditText nameField = mView.findViewById(R.id.name_field);


   /*     // Display the test result on the screen
        test_result = createTestResult(name, score);
        displayResult(test_result, view);

        // Disabled "End Test" button after clicking on it.
        endTestButton = view.findViewById(R.id.end_test_button);
        endTestButton.setEnabled(true);
        endTestButton.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {

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


    /**
     * Create summary of the test result.
     *
     * @param name  of the passing the test
     * @param score of the counting of right answers
     * @return text of the test result
     *//*
    private String createTestResult(String name, int score) {
        String test_result = getString(R.string.test_result_name) + name;
        test_result += "\n" + getString(R.string.test_result_score) + score;
        return test_result;
    }


    *//**
     * This method displays the given text on the screen.
     *//*
    private void displayResult(String result, View view) {
        TextView testResultTextView = view.findViewById(R.id.test_result_text_view);
        testResultTextView.setText(result);
    }

    *//**
     * This method is called when user selected the correct answer.
     * Added +1 to score for each correct answer
     *//*
    private int increment_score() {
        Log.d("increment_score", "before onClick: " + score);
        score = ++score;
        Log.d("increment_score", "after onClick: " + score);
        *//*if(increment_score() == 10) {
            endTestButton.setEnabled(true);
            endTestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("result", "onClick: ");
                }
            });
        }
        return score;
    }

    @Override
    public void onClick(View v) {
        increment_score();
    }


}*/
