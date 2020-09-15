package com.trig.trigapp.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.trig.trigapp.AssessmentFactory.TriviaQuestion;
import com.trig.trigapp.AssessmentFactory.TriviaQuizHelper;
import com.trig.trigapp.R;

import java.util.Collections;
import java.util.List;

import info.hoang8f.widget.FButton;

public class AssessmentFragment extends Fragment implements View.OnClickListener {


    private View mView;
    private FragmentActivity mActivity;
    public TextView toolBarText;


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


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    public AssessmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.quiz_layout, container, false);

       /* init(mView);*/


        return mView;
    }
/*
    public void init(View mView) {

        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("Assessments");
        //Initializing variables
        questionText = (TextView) mView.findViewById(R.id.triviaQuestion);
        buttonA = (FButton) mView.findViewById(R.id.buttonA);
        buttonB = (FButton) mView.findViewById(R.id.buttonB);
        buttonC = (FButton) mView.findViewById(R.id.buttonC);
        buttonD = (FButton) mView.findViewById(R.id.buttonD);


        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);


//        triviaQuizText = (TextView) mView.findViewById(R.id.triviaQuizText);
//        timeText = (TextView) mView.findViewById(R.id.timeText);
        resultText = (TextView) mView.findViewById(R.id.resultText);
//        coinText = (TextView) mView.findViewById(R.id.coinText);


        //Our database helper class
        triviaQuizHelper = new TriviaQuizHelper(mActivity);
        //Make db writable
        triviaQuizHelper.getWritableDatabase();

        //It will check if the ques,options are already added in table or not
        //If they are not added then the getAllOfTheQuestions() will return a list of size zero
        if (triviaQuizHelper.getAllOfTheQuestions().size() == 0) {
            //If not added then add the ques,options in table
            triviaQuizHelper.allQuestion();
        }

        //This will return us a list of data type TriviaQuestion
        list = triviaQuizHelper.getAllOfTheQuestions();

        //Now we gonna shuffle the elements of the list so that we will get questions randomly
        Collections.shuffle(list);

        //currentQuestion will hold the que, 4 option and ans for particular id
        currentQuestion = list.get(qid);

        //countDownTimer
        countDownTimer = new CountDownTimer(22000, 1000) {
            public void onTick(long millisUntilFinished) {

                //here you can have your logic to set text to timeText
//                timeText.setText(String.valueOf(timeValue) + "\"");

                //With each iteration decrement the time by 1 sec
                timeValue -= 1;

                //This means the user is out of time so onFinished will called after this iteration
                if (timeValue == -1) {

                    //Since user is out of time setText as time up
//                    resultText.setText(getString(R.string.timeup));

                    //Since user is out of time he won't be able to click any buttons
                    //therefore we will disable all four options buttons using this method
                    disableButton();
                }
            }

            //Now user is out of time
            public void onFinish() {
                //We will navigate him to the time up activity using below method
                timeUp();
            }
        }.start();

        //This method will set the que and four options
        updateQueAndOptions();


    }


    public void updateQueAndOptions() {

        //This method will setText for que and options
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        buttonD.setText(currentQuestion.getOptD());

        timeValue = 20;

        //Now since the user has ans correct just reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();

        //set the value of coin text
//        coinText.setText(String.valueOf(coinValue));
        //Now since user has ans correct increment the coinvalue
        coinValue++;

    }

    //Onclick listener for first button
    public void buttonA(View view) {
        //compare the option with the ans if yes then make button color green
        if (currentQuestion.getOptA().equals(currentQuestion.getAnswer())) {
            buttonA.setButtonColor(ContextCompat.getColor(mActivity, R.color.lightGreen));
            //Check if user has not exceeds the que limit
            if (qid < list.size() - 1) {

                //Now disable all the option button since user ans is correct so
                //user won't be able to press another option button after pressing one button
                disableButton();

                //Show the dialog that ans is correct
                correctDialog();
            }
            //If user has exceeds the que limit just navigate him to GameWon activity
            else {

                gameWon();

            }
        }
        //User ans is wrong then just navigate him to the PlayAgain activity
        else {

            gameLostPlayAgain();

        }
    }

    //Onclick listener for sec button
    public void buttonB(View view) {
        if (currentQuestion.getOptB().equals(currentQuestion.getAnswer())) {
            buttonB.setButtonColor(ContextCompat.getColor(mActivity, R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    //Onclick listener for third button
    public void buttonC(View view) {
        if (currentQuestion.getOptC().equals(currentQuestion.getAnswer())) {
            buttonC.setButtonColor(ContextCompat.getColor(mActivity, R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {

            gameLostPlayAgain();
        }
    }

    //Onclick listener for fourth button
    public void buttonD(View view) {
        if (currentQuestion.getOptD().equals(currentQuestion.getAnswer())) {
            buttonD.setButtonColor(ContextCompat.getColor(mActivity, R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    //This method will navigate from current activity to GameWon
    public void gameWon() {
//        Intent intent = new Intent(this, GameWon.class);
//        startActivity(intent);
//        finish();
    }

    //This method is called when user ans is wrong
    //this method will navigate user to the activity PlayAgain
    public void gameLostPlayAgain() {
        wrongDialog();
//        Intent intent = new Intent(this, PlayAgain.class);
//        startActivity(intent);
//        finish();
    }

    //This method is called when time is up
    //this method will navigate user to the activity Time_Up
    public void timeUp() {
//        Intent intent = new Intent(this, Time_Up.class);
//        startActivity(intent);
//        finish();
    }

//    //If user press home button and come in the game from memory then this
//    //method will continue the timer from the previous time it left
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        countDownTimer.start();
//    }

    //When activity is destroyed then this will cancel the timer
    @Override
    public void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    //This will pause the time
    @Override
    public void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

//    //On BackPressed
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(this, HomeScreen.class);
//        startActivity(intent);
//        finish();
//    }

    //This dialog is show to the user after he ans correct
    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(mActivity);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        //Since the dialog is show to user just pause the timer in background
        onPause();


        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        FButton buttonNext = (FButton) dialogCorrect.findViewById(R.id.dialogNext);

        //Setting type faces
        correctText.setTypeface(sb);
        buttonNext.setTypeface(sb);

        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will dismiss the dialog
                dialogCorrect.dismiss();
                //it will increment the question number
                qid++;
                //get the que and 4 option and store in the currentQuestion
                currentQuestion = list.get(qid);
                //Now this method will set the new que and 4 options
                updateQueAndOptions();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                enableButton();
            }
        });
    }

    //This dialog is show to the user after he ans correct
    public void wrongDialog() {
        final Dialog dialogCorrect = new Dialog(mActivity);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        //Since the dialog is show to user just pause the timer in background
        onPause();


        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        FButton buttonNext = (FButton) dialogCorrect.findViewById(R.id.dialogNext);

        correctText.setText(mActivity.getResources().getString(R.string.wrongans));
        buttonNext.setText(mActivity.getResources().getString(R.string.play_again));

        //Setting type faces
        correctText.setTypeface(sb);
        buttonNext.setTypeface(sb);

        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will dismiss the dialog
                dialogCorrect.dismiss();
                //it will increment the question number
                qid++;
                //get the que and 4 option and store in the currentQuestion
                currentQuestion = list.get(qid);
                //Now this method will set the new que and 4 options
                updateQueAndOptions();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                enableButton();
            }
        });
    }


    //This method will make button color white again since our one button color was turned green
    public void resetColor() {
        buttonA.setButtonColor(ContextCompat.getColor(mActivity, R.color.button_blue));
        buttonB.setButtonColor(ContextCompat.getColor(mActivity, R.color.button_blue));
        buttonC.setButtonColor(ContextCompat.getColor(mActivity, R.color.button_blue));
        buttonD.setButtonColor(ContextCompat.getColor(mActivity, R.color.button_blue));
    }

    //This method will disable all the option button
    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    //This method will all enable the option buttons
    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }

*/
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

      /*      case R.id.buttonA:
                buttonA(view);
                break;
            case R.id.buttonB:
                buttonB(view);
                break;
            case R.id.buttonC:
                buttonC(view);
                break;
            case R.id.buttonD:
                buttonD(view);
                break;
*/
        }
    }
}
