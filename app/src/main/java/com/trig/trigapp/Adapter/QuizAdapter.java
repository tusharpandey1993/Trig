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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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

        if (position == 0) {
            holder.view.setVisibility(View.GONE);
        } else {
            holder.view.setVisibility(View.VISIBLE);
        }
        holder.headerTxt.setText(Constants.getInstance().Question + (position + 1));
        holder.QuestionText.setText(quizPayLoadModelList.get(position).getQuestionText());

        QuizRadioAdapter adapter = new QuizRadioAdapter(context, onClickListner, quizPayLoadModelList.get(position).getOptions(), quizPayLoadModelList.get(position));
        holder.radioList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.radioList.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return quizPayLoadModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    protected class QuizRadioViewHolder  extends RecyclerView.ViewHolder  {

        TextView radioText;
        private ImageView selectionRadioImg, correctRadioImg;
        private ConstraintLayout selectionLayout;

        public QuizRadioViewHolder(View itemView) {
            super(itemView);

            selectionRadioImg = itemView.findViewById(R.id.selectionRadioImg);
            correctRadioImg = itemView.findViewById(R.id.correctRadioImg);
            radioText = itemView.findViewById(R.id.radioText);
            selectionLayout = itemView.findViewById(R.id.selectionLayout);
        }
    }


    protected class QuizViewHolder extends RecyclerView.ViewHolder  {

        TextView headerTxt,QuestionText;
        View view;
        RecyclerView radioList;

        public QuizViewHolder(View itemView) {
            super(itemView);

            headerTxt = itemView.findViewById(R.id.headerTxt);
            view = itemView.findViewById(R.id.view);
            QuestionText = itemView.findViewById(R.id.QuestionText);
            radioList = itemView.findViewById(R.id.radioList);
        }
    }
}
