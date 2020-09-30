package com.trig.trigapp.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.getLoadAssignmentsRes;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder>  {

    private static final String TAG = "QuizAdapter";
    View view;
    Context context;
    OnClickInterface onClickListner;
    List<getLoadAssignmentsRes> quizPayLoadModelList;
    int value;
    List<getLoadAssignmentsRes.Option> options;

    public QuizAdapter(Context context, OnClickInterface mListner,List<getLoadAssignmentsRes> quizPayLoadModelList) {
        this.context= context;
        this.onClickListner = mListner;
        this.quizPayLoadModelList = quizPayLoadModelList;
    }

    @NonNull
    @Override
    public QuizAdapter.QuizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quiz_child_layout, viewGroup, false);
        return new QuizAdapter.QuizViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.QuizViewHolder holder, final int position) {

        if(position == 0){
            holder.view.setVisibility(View.GONE);
        }else{
            holder.view.setVisibility(View.VISIBLE);
        }
        holder.headerTxt.setText(Constants.getInstance().Question +(position+1));
        holder.QuestionText.setText(quizPayLoadModelList.get(position).getQuestionText());

        getOptions(holder,position);

        holder.radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.answer_1_radiobutton:
                        onClickListner.onClickQuiz(view,position,R.id.answer_1_radiobutton,quizPayLoadModelList.get(position).getAssestmentId(), quizPayLoadModelList.get(position).options.get(0).getOptionId());
//                        Log.e("TAG", "onCheckedChanged: answer_1_radiobutton"+position +holder.answer_1_radiobutton.getText().toString());
                        Log.d(TAG, "onCheckedChanged:0 " + quizPayLoadModelList.get(0).options.get(0).getOptionId());
                        break;
                    case R.id.answer_2_radiobutton:
                        onClickListner.onClickQuiz(view,position,R.id.answer_2_radiobutton,quizPayLoadModelList.get(position).getAssestmentId(), quizPayLoadModelList.get(position).options.get(1).getOptionId());
//                        Log.e("TAG", "onCheckedChanged: answer_2_radiobutton"+position+holder.answer_2_radiobutton.getText().toString());
                        Log.d(TAG, "onCheckedChanged:1 " + options.get(1).getOptionId());
                        break;
                    case R.id.answer_3_radiobutton:
                        onClickListner.onClickQuiz(view,position,R.id.answer_3_radiobutton,quizPayLoadModelList.get(position).getAssestmentId(), quizPayLoadModelList.get(position).options.get(2).getOptionId());
//                        Log.e("TAG", "onCheckedChanged: answer_3_radiobutton"+position+holder.answer_3_radiobutton.getText().toString());
                        Log.d(TAG, "onCheckedChanged:2 " + options.get(2).getOptionId());
                        break;
                    case R.id.answer_4_radiobutton:
                        onClickListner.onClickQuiz(view,position,R.id.answer_4_radiobutton,quizPayLoadModelList.get(position).getAssestmentId(), quizPayLoadModelList.get(position).options.get(3).getOptionId());
//                        Log.e("TAG", "onCheckedChanged: answer_4_radiobutton"+position+holder.answer_4_radiobutton.getText().toString());
                        Log.d(TAG, "onCheckedChanged:3 " + options.get(3).getOptionId());
                        break;
                }
            }
        });

    }

    public void getOptions(QuizViewHolder holder,int position){
        options =  quizPayLoadModelList.get(position).getOptions();
        if(options!=null) {
            if(options.size() == 2){
                if (options.get(0).optionText != null) {
                    holder.answer_1_radiobutton.setText(options.get(0).optionText);
                }
                if (options.get(1).optionText != null) {
                    holder.answer_2_radiobutton.setText(options.get(1).optionText);
                }
            }else if(options.size() == 3) {
                if (options.get(0).optionText != null) {
                    holder.answer_1_radiobutton.setText(options.get(0).optionText);
                }
                if (options.get(1).optionText != null) {
                    holder.answer_2_radiobutton.setText(options.get(1).optionText);
                }
                if (options.get(2).optionText != null) {
                    holder.answer_3_radiobutton.setText(options.get(2).optionText);
                }
            }else if(options.size() == 4) {
                if (options.get(0).optionText != null) {
                    holder.answer_1_radiobutton.setText(options.get(0).optionText);
                }
                if (options.get(1).optionText != null) {
                    holder.answer_2_radiobutton.setText(options.get(1).optionText);
                }
                if (options.get(2).optionText != null) {
                    holder.answer_3_radiobutton.setText(options.get(2).optionText);
                }
                if (options.get(3).optionText != null) {
                    holder.answer_4_radiobutton.setText(options.get(3).optionText);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return quizPayLoadModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    protected class QuizViewHolder extends RecyclerView.ViewHolder  {

        TextView headerTxt,QuestionText;
        RadioButton answer_1_radiobutton,answer_2_radiobutton,answer_3_radiobutton,answer_4_radiobutton;
        View view;
        RadioGroup radioGrp;
        private RadioButton radioButton;

        public QuizViewHolder(View itemView) {
            super(itemView);

            radioGrp = itemView.findViewById(R.id.radioGrp);
            headerTxt = itemView.findViewById(R.id.headerTxt);
            view = itemView.findViewById(R.id.view);
            QuestionText = itemView.findViewById(R.id.QuestionText);
            answer_1_radiobutton = itemView.findViewById(R.id.answer_1_radiobutton);
            answer_2_radiobutton = itemView.findViewById(R.id.answer_2_radiobutton);
            answer_3_radiobutton = itemView.findViewById(R.id.answer_3_radiobutton);
            answer_4_radiobutton = itemView.findViewById(R.id.answer_4_radiobutton);

        }
    }
}
