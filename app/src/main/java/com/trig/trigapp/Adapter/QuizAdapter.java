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

import com.trig.trigapp.R;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder>  {

    View view;
    Context context;
    OnClickInterface onClickListner;
    List<QuizPayLoadModel> quizPayLoadModelList;
    int value;

    public QuizAdapter(Context context, OnClickInterface mListner,List<QuizPayLoadModel> quizPayLoadModelList) {
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

        if(position==0){
            holder.view.setVisibility(View.GONE);
        }else{
            holder.view.setVisibility(View.VISIBLE);
        }
        holder.headerTxt.setText(quizPayLoadModelList.get(position).getHeaderTxt());
        holder.QuestionText.setText(quizPayLoadModelList.get(position).getQuestTxt());
        holder.answer_1_radiobutton.setText(quizPayLoadModelList.get(position).getOption1Txt());
        holder.answer_2_radiobutton.setText(quizPayLoadModelList.get(position).getOption2Txt());
        holder.answer_3_radiobutton.setText(quizPayLoadModelList.get(position).getOption3Txt());
        holder.answer_4_radiobutton.setText(quizPayLoadModelList.get(position).getOption4Txt());

//        int selectedId = holder.radioGrp.getCheckedRadioButtonId();
//
//        holder.radioButton = (RadioButton) holder.itemView.findViewById(selectedId);
//        Log.e("TAG", "onBindViewHolder: "+holder.radioButton.getText().toString());

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
