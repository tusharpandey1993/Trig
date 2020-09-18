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

import com.trig.trigapp.R;
import java.util.List;
import info.hoang8f.widget.FButton;

public class AssessmentFragmentNew extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


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
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_AssessmentFrag_to_DashbordFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_AssessmentFrag_to_DashbordFragment);
            }
        });

        return mView;
    }

    public void init(View mView) {
//        end_Test(mView);
        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("Functional Training");
        backIcon = mView.findViewById(R.id.backIcon);

        question_1 = (RadioGroup) mView.findViewById(R.id.question_1);
        RadioGroup question_2 = (RadioGroup) mView.findViewById(R.id.question_2);
        RadioGroup question_3 = (RadioGroup) mView.findViewById(R.id.question_3);
        RadioGroup question_4 = (RadioGroup) mView.findViewById(R.id.question_4);
        RadioGroup question_5 = (RadioGroup) mView.findViewById(R.id.question_5);
        RadioGroup question_6 = (RadioGroup) mView.findViewById(R.id.question_6);
        RadioGroup question_7 = (RadioGroup) mView.findViewById(R.id.question_7);
        RadioGroup question_8 = (RadioGroup) mView.findViewById(R.id.question_8);
        RadioGroup question_9 = (RadioGroup) mView.findViewById(R.id.question_9);
       RadioGroup question_10 = (RadioGroup) mView.findViewById(R.id.question_10);

        question_1.setOnCheckedChangeListener(this);
        question_2.setOnCheckedChangeListener(this);
        question_3.setOnCheckedChangeListener(this);
        question_4.setOnCheckedChangeListener(this);
        question_5.setOnCheckedChangeListener(this);
        question_6.setOnCheckedChangeListener(this);
        question_7.setOnCheckedChangeListener(this);
        question_8.setOnCheckedChangeListener(this);
        question_9.setOnCheckedChangeListener(this);
       question_10.setOnCheckedChangeListener(this);
    }



    /**
     * This method is called when the end test button is clicked.
     */
    public void end_Test(View view) {
        EditText nameField = (EditText) view.findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        radioQuestion_1(view);
        radioQuestion_2(view);
        radioQuestion_3(view);
        radioQuestion_4(view);
        radioQuestion_5(view);
        radioQuestion_6(view);
        radioQuestion_7(view);
        radioQuestion_8(view);
        radioQuestion_9(view);
       radioQuestion_10(view);

        // Display the test result on the screen
        test_result = createTestResult(name, score);
        displayResult(test_result, view);

        // Disabled "End Test" button after clicking on it.
        endTestButton = (Button) view.findViewById(R.id.end_test_button);
        endTestButton.setEnabled(true);
        endTestButton.setOnClickListener(this);
    }

    /**
     * Create summary of the test result.
     *
     * @param name of the passing the test
     * @param score of the counting of right answers
     * @return text of the test result
     */
    private String createTestResult(String name, int score) {
        String test_result = getString(R.string.test_result_name) + name;
        test_result += "\n" + getString(R.string.test_result_score) + score;
        return test_result;
    }

    public void radioQuestion_1(View view) {
        Log.d("question1", "radioQuestion_1: onClick" + view);
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) view.findViewById(R.id.question_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_2_1_radiobutton:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_1_radiobutton);
                increment_score();
                Log.d("question1", "radioQuestion_1: ");
                break;
            default:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_2_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_3_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_4_radiobutton);
        }
    }

    public void radioQuestion_2(View view) {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) view.findViewById(R.id.question_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_2_1_radiobutton:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_1_radiobutton);
                increment_score();
                break;
            default:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_2_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_3_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_4_radiobutton);
        }
    }
    public void radioQuestion_3(View view) {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) view.findViewById(R.id.question_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_2_1_radiobutton:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_1_radiobutton);
                increment_score();
                break;
            default:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_2_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_3_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_4_radiobutton);
        }
    }
    public void radioQuestion_4(View view) {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) view.findViewById(R.id.question_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_2_1_radiobutton:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_1_radiobutton);
                increment_score();
                break;
            default:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_2_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_3_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_4_radiobutton);
        }
    }
    public void radioQuestion_5(View view) {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) view.findViewById(R.id.question_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_2_1_radiobutton:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_1_radiobutton);
                increment_score();
                break;
            default:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_2_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_3_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_4_radiobutton);
        }
    }
    public void radioQuestion_6(View view) {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) view.findViewById(R.id.question_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_2_1_radiobutton:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_1_radiobutton);
                increment_score();
                break;
            default:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_2_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_3_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_4_radiobutton);
        }
    }
    public void radioQuestion_7(View view) {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) view.findViewById(R.id.question_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_2_1_radiobutton:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_1_radiobutton);
                increment_score();
                break;
            default:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_2_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_3_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_4_radiobutton);
        }
    }
    public void radioQuestion_8(View view) {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) view.findViewById(R.id.question_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_2_1_radiobutton:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_1_radiobutton);
                increment_score();
                break;
            default:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_2_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_3_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_4_radiobutton);
        }
    }
    public void radioQuestion_9(View view) {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) view.findViewById(R.id.question_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_2_1_radiobutton:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_1_radiobutton);
                increment_score();
                break;
            default:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_2_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_3_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_4_radiobutton);
        }
    }
    public void radioQuestion_10(View view) {
        RadioButton answerRadio;
        RadioGroup answers = (RadioGroup) view.findViewById(R.id.question_2);
        switch (answers.getCheckedRadioButtonId()) {
            case R.id.answer_2_1_radiobutton:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_1_radiobutton);
                increment_score();
                break;
            default:
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_2_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_3_radiobutton);
                answerRadio = (RadioButton) view.findViewById(R.id.answer_2_4_radiobutton);
        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayResult(String result, View view) {
        TextView testResultTextView = (TextView) view.findViewById(R.id.test_result_text_view);
        testResultTextView.setText(result);
    }

    /**
     * This method is called when user selected the correct answer.
     * Added +1 to score for each correct answer
     */
    private int increment_score() {
        Log.d("increment_score", "before onClick: " + score);
        score = ++score;
        Log.d("increment_score", "after onClick: " + score);
        /*if(increment_score() == 10) {
            endTestButton.setEnabled(true);
            endTestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("result", "onClick: ");
                }
            });
        }*/
        return score;
    }

    @Override
    public void onClick(View v) {
        increment_score();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View radioButton = group.findViewById(checkedId);
        int index = group.indexOfChild(radioButton);

        switch (group.getId()){
            case R.id.question_1:
                switch (index){
                    case 2:
                        Log.d("1", "onCheckedChanged: ");
                        break;
                }
                break;
            case R.id.question_2:
                switch (index){
                    case 2:
                        Log.d("2", "onCheckedChanged: ");
                        break;
                }
                break;
            case R.id.question_3:
                switch (index){
                    case 2:
                        Log.d("3", "onCheckedChanged: ");
                        break;
                }
                break;
            case R.id.question_4:
                switch (index){
                    case 2:
                        Log.d("4", "onCheckedChanged: ");
                        break;
                }
                break;
            case R.id.question_5:
                switch (index){
                    case 2:
                        Log.d("5", "onCheckedChanged: ");
                        break;
                }
                break;
            case R.id.question_6:
                switch (index){
                    case 2:
                        Log.d("6", "onCheckedChanged: ");
                        break;
                }
                break;
            case R.id.question_7:
                switch (index){
                    case 2:
                        Log.d("7", "onCheckedChanged: ");
                        break;
                }
                break;
            case R.id.question_8:
                switch (index){
                    case 2:
                        Log.d("8", "onCheckedChanged: ");
                        break;
                }
                break;
            case R.id.question_9:
                switch (index){
                    case 2:
                        Log.d("9", "onCheckedChanged: ");
                        break;
                }
                break;
            case R.id.question_10:
                switch (index){
                    case 2:
                        Log.d("10", "onCheckedChanged: ");
                        break;
                }
                break;
            default:
                break;
        }
    }
}
