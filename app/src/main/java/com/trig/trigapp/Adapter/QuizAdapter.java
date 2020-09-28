package com.trig.trigapp.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.R;

public class QuizAdapter{} /*extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder>  {

    View view;
    Context context;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    OnClickInterface onClickListner;
    private int selectedPosition = 0;

    public QuizAdapter(Context context, OnClickInterface mListner) {
        this.context= context;
        this.onClickListner = mListner;
    }

    @NonNull
    @Override
    public QuizAdapter.QuizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nav_child_layout, viewGroup, false);
        return new QuizAdapter.QuizViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.QuizViewHolder holder, final int position) {

        if(selectedPosition == position){
            holder.id_value.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }else{
            holder.id_value.setTextColor(context.getResources().getColor(R.color.gray));
        }
        holder.headerTxt.setText("");
        holder.QuestionText.setText("");
        holder.id_icon.setBackground(screenIcons[position]);

        holder.navChildLYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition >= 0) {
                    selectedPosition = position;
                    notifyDataSetChanged();
                }
                onClickListner.onClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return screenTitles.length;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    protected class QuizViewHolder extends RecyclerView.ViewHolder  {

        TextView headerTxt,QuestionText;
        RadioButton answer_1_radiobutton,answer_2_radiobutton,answer_3_radiobutton,answer_4_radiobutton;


        public QuizViewHolder(View itemView) {
            super(itemView);

            headerTxt = itemView.findViewById(R.id.headerTxt);
            QuestionText = itemView.findViewById(R.id.QuestionText);
            answer_1_radiobutton = itemView.findViewById(R.id.answer_1_radiobutton);
            answer_2_radiobutton = itemView.findViewById(R.id.answer_2_radiobutton);
            answer_3_radiobutton = itemView.findViewById(R.id.answer_3_radiobutton);
            answer_4_radiobutton = itemView.findViewById(R.id.answer_4_radiobutton);

        }
    }
}*/
