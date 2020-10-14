package com.trig.trigapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.getAssessmentListRes;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyVi> {

    ArrayList mValuesAssessment;
    Context mContext;
    protected AssessmentTopicsAdapter.ItemListener mListener;
    private static final String TAG = "CourseTopicAdapter";

    public UserListAdapter(Context context, ArrayList values, AssessmentTopicsAdapter.ItemListener itemListener) {
        mValuesAssessment = values;
        mContext = context;
        mListener=itemListener;
    }


    @Override
    public UserListAdapter.MyVi onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.user_single, parent, false);

        return new UserListAdapter.MyVi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserListAdapter.MyVi holder, int position) {
        holder.setData((getAssessmentListRes) mValuesAssessment.get(position));
    }

    @Override
    public int getItemCount() {
        return mValuesAssessment.size();
    }

    public interface ItemListener {
        void onItemClick(getAssessmentListRes item);
    }


    public class MyVi extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView cardHeading, assignedDate, completedOn, status, percentage, InstallScheduleText, progressEndPercentage;
        public ImageView imageView, completedIcon;
        public ConstraintLayout relativeLayout;
        public ProgressBar courseCompletionProgess;
        getAssessmentListRes item;

        public MyVi(View v) {

            super(v);

            v.setOnClickListener(this);
            cardHeading = (TextView) v.findViewById(R.id.cardHeading);
            assignedDate = (TextView) v.findViewById(R.id.assignedDate);
            completedOn = (TextView) v.findViewById(R.id.completedOn);
            status = (TextView) v.findViewById(R.id.status);
            percentage = (TextView) v.findViewById(R.id.percentage);
            completedIcon = (ImageView) v.findViewById(R.id.completedIcon);
            InstallScheduleText = (TextView) v.findViewById(R.id.InstallScheduleText);
            progressEndPercentage = (TextView) v.findViewById(R.id.progressEndPercentage);


            relativeLayout = (ConstraintLayout) v.findViewById(R.id.relativeLayout);
            courseCompletionProgess = (ProgressBar) v.findViewById(R.id.courseCompletionProgess);

        }

        public void setData(getAssessmentListRes item) {
            this.item = item;

            cardHeading.setText(""+item.getAssessment_name());
            assignedDate.setText(""+item.getAssigned_date());
            completedOn.setText(item.getAssestment_completed_date());
            status.setText(item.getStatus());
            if(!item.getScore().isEmpty()) {
                float score = Float.parseFloat(item.getScore());
                percentage.setText(""+Math.round(score) + "%");
                courseCompletionProgess.setProgress(1);
                courseCompletionProgess.setProgress(Math.round(score));
                progressEndPercentage.setText(Math.round(score) + " %");
            }

            if(item.getStatus().equalsIgnoreCase("Completed")) {
                completedIcon.setVisibility(View.VISIBLE);
                InstallScheduleText.setText(Constants.getInstance().Result);
            } else {
                completedIcon.setVisibility(View.GONE);
                InstallScheduleText.setText(Constants.getInstance().ATTEMPT_small);
            }

        }



        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }


}