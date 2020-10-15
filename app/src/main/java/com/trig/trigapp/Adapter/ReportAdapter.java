package com.trig.trigapp.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.GetReportRes;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportHolder>  {

    View view;
    Context context;
    OnClickInterface onClickListner;
    ArrayList<GetReportRes> getReportRes;

    public ReportAdapter(Context context, OnClickInterface mListner, ArrayList<GetReportRes> getReportRes) {
        this.context= context;
        this.onClickListner = mListner;
        this.getReportRes = getReportRes;
    }

    @NonNull
    @Override
    public ReportAdapter.ReportHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_single, viewGroup, false);
        return new ReportHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ReportHolder holder, final int position) {

        holder.name.setText(getReportRes.get(position).getFullName());
        if(getReportRes.get(position).getTotal_course_Count() >0) {
            holder.AssignedCourse.setText("Yes");
        } else {
            holder.AssignedCourse.setText("No");
        }
        holder.CourseStatus.setText("Not started");

        if(getReportRes.get(position).getTotal_a_Count() >0) {
            holder.AssignedAssessment.setText("Yes");
        } else {
            holder.AssignedAssessment.setText("No");
        }

        holder.AssessmentStatus.setText("Not started");
        holder.AssessmentScore.setText("");
        holder.empCode.setText(getReportRes.get(position).getTirg_EmpCode());
        holder.EmpPassword.setText(getReportRes.get(position).getEmp_password());

        if(getReportRes.get(position).getIs_active() ==1){
            holder.UserStatus.setChecked(true);
        } else {
            holder.UserStatus.setChecked(false);
        }



    }


    @Override
    public int getItemCount() {
        return getReportRes.size();
    }


    protected class ReportHolder extends RecyclerView.ViewHolder  {

        TextView name, AssignedCourse, CourseStatus, AssignedAssessment, AssessmentStatus, AssessmentScore, empCode, EmpPassword, ViewCoursedetails, Assessment, feedback;
        LinearLayout navChildLYT;
        SwitchCompat UserStatus;

        public ReportHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            AssignedCourse = itemView.findViewById(R.id.AssignedCourse);
            CourseStatus = itemView.findViewById(R.id.CourseStatus);
            AssignedAssessment = itemView.findViewById(R.id.AssignedAssessment);
            AssessmentStatus = itemView.findViewById(R.id.AssessmentStatus);
            AssessmentScore = itemView.findViewById(R.id.AssessmentScore);
            empCode = itemView.findViewById(R.id.empCode);
            EmpPassword = itemView.findViewById(R.id.EmpPassword);
            UserStatus = itemView.findViewById(R.id.UserStatus);

        }
    }

}