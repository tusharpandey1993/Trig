package com.trig.trigapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.getCourseDetailsRes;
import java.util.ArrayList;

public class PopCourseAssessmentAdapter extends RecyclerView.Adapter<PopCourseAssessmentAdapter.ReportHolder>  {

    View view;
    Context context;
    OnClickInterface onClickListner;
    ArrayList<getCourseDetailsRes> getCourseDetailsResArrayList;

    public PopCourseAssessmentAdapter(Context context, OnClickInterface mListner, ArrayList<getCourseDetailsRes> getCourseDetailsRes) {
        this.context= context;
        this.onClickListner = mListner;
        this.getCourseDetailsResArrayList = getCourseDetailsRes;
    }

    @NonNull
    @Override
    public PopCourseAssessmentAdapter.ReportHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_single_assign, viewGroup, false);
        return new PopCourseAssessmentAdapter.ReportHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull PopCourseAssessmentAdapter.ReportHolder holder, final int position) {
        holder.course_name.setText(getCourseDetailsResArrayList.get(position).getCourse_name());
        holder.Status.setText(getCourseDetailsResArrayList.get(position).getStatus());
        holder.course_assigned_date.setText(getCourseDetailsResArrayList.get(position).getCourse_assigned_date());
    }


    @Override
    public int getItemCount() {
        return getCourseDetailsResArrayList.size();
    }

    protected class ReportHolder extends RecyclerView.ViewHolder  {

        TextView course_name, Status, course_assigned_date;

        public ReportHolder(View itemView) {
            super(itemView);
            course_name = itemView.findViewById(R.id.course_name);
            Status = itemView.findViewById(R.id.Status);
            course_assigned_date = itemView.findViewById(R.id.course_assigned_date);
        }
    }

}