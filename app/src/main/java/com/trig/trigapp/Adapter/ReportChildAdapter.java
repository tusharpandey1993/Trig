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
import com.trig.trigapp.api.Response.getCourseDetailsRes;
import java.util.ArrayList;

public class ReportChildAdapter extends RecyclerView.Adapter<ReportChildAdapter.ReportChildHolder>  {

    View view;
    Context context;
    OnClickInterface onClickListner;
    ArrayList<getCourseDetailsRes> getCourseDetailsResArrayList;
    private static final String TAG = "ReportChildAdapter";

    public ReportChildAdapter(Context context, OnClickInterface mListner, ArrayList<getCourseDetailsRes> getCourseDetailsResArrayList) {
        this.context= context;
        this.onClickListner = mListner;
        this.getCourseDetailsResArrayList = getCourseDetailsResArrayList;
    }

    @NonNull
    @Override
    public ReportChildAdapter.ReportChildHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_single_child, viewGroup, false);
        return new ReportChildHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ReportChildAdapter.ReportChildHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: " + getCourseDetailsResArrayList.get(position));

        holder.course_name.setText(getCourseDetailsResArrayList.get(position).getCourse_name());
        holder.Status.setText(getCourseDetailsResArrayList.get(position).getStatus());
        holder.course_assigned_date.setText(getCourseDetailsResArrayList.get(position).getCourse_assigned_date());
        holder.course_completed_date.setText(getCourseDetailsResArrayList.get(position).getCourse_completed_date());
    }


    @Override
    public int getItemCount() {
        return getCourseDetailsResArrayList.size();
    }


    protected class ReportChildHolder extends RecyclerView.ViewHolder  {

        TextView course_name, Status, course_assigned_date, course_completed_date;

        public ReportChildHolder(View itemView) {
            super(itemView);
            course_name = itemView.findViewById(R.id.course_name);
            Status = itemView.findViewById(R.id.Status);
            course_assigned_date = itemView.findViewById(R.id.course_assigned_date);
            course_completed_date = itemView.findViewById(R.id.course_completed_date);
        }
    }

}