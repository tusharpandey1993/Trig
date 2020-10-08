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
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.getLoadAssignmentsRes;

import java.util.List;

public class QuizRadioAdapter extends RecyclerView.Adapter<QuizRadioAdapter.QuizRadioViewHolder>  {

    private static final String TAG = "QuizAdapter";
    View view;
    Context context;
    OnClickInterface onClickListner;
    List<getLoadAssignmentsRes.Option> quizOptionsModelList;
    private int selectedPosition = -1;
    private getLoadAssignmentsRes res;

    public QuizRadioAdapter(Context context, OnClickInterface mListner,List<getLoadAssignmentsRes.Option> quizOptionsModelList,getLoadAssignmentsRes res) {
        this.context= context;
        this.onClickListner = mListner;
        this.quizOptionsModelList = quizOptionsModelList;
        this.res = res;
    }

    public QuizRadioAdapter(Context context, OnClickInterface mListner,List<getLoadAssignmentsRes.Option> quizOptionsModelList) {
        this.context= context;
        this.onClickListner = mListner;
        this.quizOptionsModelList = quizOptionsModelList;
        this.res = res;
    }

    @NonNull
    @Override
    public QuizRadioAdapter.QuizRadioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quiz_radio_child_layout, viewGroup, false);
        return new QuizRadioAdapter.QuizRadioViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull QuizRadioAdapter.QuizRadioViewHolder holder, final int position) {

        holder.radioText.setText(quizOptionsModelList.get(position).optionText);


        if (res != null) {
            if (res.getCorrectOpts() != null) {

                if (res.getCorrectOpts().toString().contains(res.options.get(position).getOptionId())) {
                    holder.correctRadioImg.setBackgroundResource(R.drawable.ic_radio_button_tick);
                }

                holder.correctRadioImg.setVisibility(View.VISIBLE);

                if (res.options != null && res.options.get(position) != null) {

                    if (res.getCorrectOpts().toString().contains(res.options.get(position).getOptionId())) {
                        holder.correctRadioImg.setBackgroundResource(R.drawable.ic_radio_button_tick);
                    }

                    String[] arrSplit = res.getSelectedOpts().toString().split(",");
                    for (int i = 0; i < arrSplit.length; i++) {
                        Log.d(TAG, "onBindViewHolder:CCCC " + arrSplit[i].equalsIgnoreCase(res.options.get(position).getOptionId()));
                        if (!res.getCorrectOpts().toString().contains(arrSplit[i]) && arrSplit[i].equalsIgnoreCase(res.options.get(position).getOptionId())) {
                            holder.correctRadioImg.setBackgroundResource(R.drawable.ic_wrong_ans);
                        }
                    }
                }

            } else {
                if (selectedPosition == position) {
                    holder.selectionRadioImg.setBackground(context.getDrawable(R.drawable.ic_radio_button_checked));
                    holder.correctRadioImg.setVisibility(View.GONE);
                } else {
                    holder.selectionRadioImg.setBackground(context.getDrawable(R.drawable.ic_radio_button_unchecked));
                    holder.correctRadioImg.setVisibility(View.GONE);
                }

                holder.selectionLayout.setOnClickListener(v -> {
                    if (selectedPosition >= 0)
                        notifyItemChanged(selectedPosition);
                    selectedPosition = holder.getAdapterPosition();
                    onClickListner.onClickQuiz(view, position, R.id.selectionLayout, res.getAssestmentId(), res.options.get(position).getOptionId(), res.assessmentQuestionId);
                    notifyItemChanged(selectedPosition);
                });
            }
        }


    }

    @Override
    public int getItemCount() {
        return quizOptionsModelList.size();
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

}

