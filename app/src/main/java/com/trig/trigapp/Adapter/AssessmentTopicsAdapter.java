package com.trig.trigapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.getAssessmentListRes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AssessmentTopicsAdapter extends RecyclerView.Adapter<AssessmentTopicsAdapter.MyVi> {

    ArrayList mValues;
    Context mContext;
    protected ItemListener mListener;
    private static final String TAG = "CourseTopicAdapter";

    public AssessmentTopicsAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener=itemListener;
    }


    @Override
    public MyVi onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.assessment_topics_item, parent, false);

        return new MyVi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyVi holder, int position) {
        holder.setData((getAssessmentListRes) mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(getAssessmentListRes item);
    }


    public class MyVi extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView assignedDate, completedOn, status, percentage;
        public ImageView imageView, completedIcon;
        public ConstraintLayout relativeLayout;
        public ProgressBar courseCompletionProgess;
        getAssessmentListRes item;

        public MyVi(View v) {

            super(v);

            v.setOnClickListener(this);
            assignedDate = (TextView) v.findViewById(R.id.assignedDate);
            completedOn = (TextView) v.findViewById(R.id.completedOn);
            status = (TextView) v.findViewById(R.id.status);
            percentage = (TextView) v.findViewById(R.id.percentage);
            completedIcon = (ImageView) v.findViewById(R.id.completedIcon);


            relativeLayout = (ConstraintLayout) v.findViewById(R.id.relativeLayout);
            courseCompletionProgess = (ProgressBar) v.findViewById(R.id.courseCompletionProgess);

        }

        public void setData(getAssessmentListRes item) {
            this.item = item;

            assignedDate.setText(item.getAssignedDate());
            completedOn.setText(item.getAssestmentCompletedDate());
            status.setText(item.getStatus());
            percentage.setText(item.getScore());
            if(item.getStatus().equalsIgnoreCase("Completed")) {
                completedIcon.setVisibility(View.VISIBLE);
            } else {
                completedIcon.setVisibility(View.INVISIBLE);
            }

//            float percentage = (float)((item.getCompleted()  * 100)/ item.getAll());
            courseCompletionProgess.setProgress(Integer.parseInt(item.getScore()));
        }



        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }
}