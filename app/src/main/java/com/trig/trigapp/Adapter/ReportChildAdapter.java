package com.trig.trigapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.AssessmentTrainerFromReportRes;
import com.trig.trigapp.api.Response.getCourseDetailsRes;
import java.util.ArrayList;

public class ReportChildAdapter extends RecyclerView.Adapter<ReportChildAdapter.ReportChildHolder>  {

    View view;
    Context context;
    OnClickInterface onClickListner;
    ArrayList<getCourseDetailsRes> getCourseDetailsResArrayList;
    private static final String TAG = "ReportChildAdapter";
    ArrayList<AssessmentTrainerFromReportRes> mgetAssessmentTrainerFromReport;
    private boolean showAssessment = false;

    public ReportChildAdapter(Context context, OnClickInterface mListner, ArrayList<getCourseDetailsRes> getCourseDetailsResArrayList) {
        this.context= context;
        this.onClickListner = mListner;
        this.getCourseDetailsResArrayList = getCourseDetailsResArrayList;
    }

    public ReportChildAdapter(Context context, OnClickInterface mListner, ArrayList<AssessmentTrainerFromReportRes> getAssessmentTrainerFromReport, boolean extras) {
        this.context= context;
        this.onClickListner = mListner;
        this.mgetAssessmentTrainerFromReport = getAssessmentTrainerFromReport;
        this.showAssessment = extras;
    }

    @NonNull
    @Override
    public ReportChildAdapter.ReportChildHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_single_child, viewGroup, false);
        return new ReportChildHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ReportChildAdapter.ReportChildHolder holder, final int position) {
        if(!showAssessment) {
            holder.course_nameHeading.setText("Course Name");
            holder.course_name.setText(getCourseDetailsResArrayList.get(position).getCourse_name());
            holder.Status.setText(getCourseDetailsResArrayList.get(position).getStatus());
            holder.course_assigned_date.setText(getCourseDetailsResArrayList.get(position).getCourse_assigned_date());
            holder.course_completed_date.setText(getCourseDetailsResArrayList.get(position).getCourse_completed_date());
        } else {

            holder.course_nameHeading.setText("Assessment Name");
            holder.course_name.setText(mgetAssessmentTrainerFromReport.get(position).getAssessment_name());
            holder.Status.setText(mgetAssessmentTrainerFromReport.get(position).getStatus());
            holder.course_assigned_date.setText(mgetAssessmentTrainerFromReport.get(position).getAssigned_date());
            holder.course_completed_date.setText(mgetAssessmentTrainerFromReport.get(position).getAssestment_completed_date());
        }
    }


    @Override
    public int getItemCount() {
        if(showAssessment) {
            return mgetAssessmentTrainerFromReport.size();
        } else {
            return getCourseDetailsResArrayList.size();
        }

    }


    protected class ReportChildHolder extends RecyclerView.ViewHolder  {

        TextView course_name, Status, course_assigned_date, course_completed_date, course_nameHeading;

        public ReportChildHolder(View itemView) {
            super(itemView);
            course_nameHeading = itemView.findViewById(R.id.course_nameHeading);
            course_name = itemView.findViewById(R.id.course_name);
            Status = itemView.findViewById(R.id.Status);
            course_assigned_date = itemView.findViewById(R.id.course_assigned_date);
            course_completed_date = itemView.findViewById(R.id.course_completed_date);
        }
    }

}