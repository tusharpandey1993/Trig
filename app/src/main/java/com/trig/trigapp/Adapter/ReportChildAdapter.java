package com.trig.trigapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.GetReportRes;

import java.util.ArrayList;

public class ReportChildAdapter extends RecyclerView.Adapter<ReportChildAdapter.ReportChildHolder>  {

    View view;
    Context context;
    OnClickInterface onClickListner;
    GetReportRes getReportRes;

    public ReportChildAdapter(Context context, OnClickInterface mListner, GetReportRes getReportRes) {
        this.context= context;
        this.onClickListner = mListner;
        this.getReportRes = getReportRes;
    }

    @NonNull
    @Override
    public ReportChildAdapter.ReportChildHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_single_child, viewGroup, false);
        return new ReportChildHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ReportChildAdapter.ReportChildHolder holder, final int position) {

        holder.key.setText(getReportRes.getFullName());
        holder.value.setText(getReportRes.getEmp_password());

    }


    @Override
    public int getItemCount() {
        return 1;
    }


    protected class ReportChildHolder extends RecyclerView.ViewHolder  {

        TextView key,value;

        public ReportChildHolder(View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            value = itemView.findViewById(R.id.value);
        }
    }

}