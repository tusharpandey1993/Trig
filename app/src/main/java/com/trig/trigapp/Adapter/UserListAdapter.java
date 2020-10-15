package com.trig.trigapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.UserListResponse;
import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter< UserListAdapter.ReportHolder>  {

    View view;
    Context context;
    OnClickInterface onClickListner;
    ArrayList< UserListResponse> userListResponseArrayList;

    public  UserListAdapter(Context context, OnClickInterface mListner, ArrayList<UserListResponse> getUserListResponseList) {
        this.context= context;
        this.onClickListner = mListner;
        this.userListResponseArrayList = getUserListResponseList;
    }

    @NonNull
    @Override
    public  UserListAdapter.ReportHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_single_assign, viewGroup, false);
        return new  UserListAdapter.ReportHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull  UserListAdapter.ReportHolder holder, final int position) {

        holder.name.setText(userListResponseArrayList.get(position).getFullName());
        holder.AssignedCourse.setText(""+userListResponseArrayList.get(position).getTotal_course_Count());
        holder.AssignedAssessment.setText(""+userListResponseArrayList.get(position).getTotal_a_Count());
        holder.empCode.setText(""+userListResponseArrayList.get(position).getTirg_EmpCode());
        holder.EmpUnit.setText(userListResponseArrayList.get(position).getUnit());

    }


    @Override
    public int getItemCount() {
        return userListResponseArrayList.size();
    }


    protected class ReportHolder extends RecyclerView.ViewHolder  {

        TextView name, AssignedCourse, AssignedAssessment, empCode, EmpUnit;
        SwitchCompat UserStatus;

        public ReportHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            AssignedCourse = itemView.findViewById(R.id.AssignedCourse);
            AssignedAssessment = itemView.findViewById(R.id.AssignedAssessment);
            empCode = itemView.findViewById(R.id.empCode);
            EmpUnit = itemView.findViewById(R.id.EmpUnit);

        }
    }

}