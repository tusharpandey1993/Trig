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

        holder.key.setText(getReportRes.get(position).getFullName());
        holder.value.setText(getReportRes.get(position).getEmp_password());
        holder.navChildLYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListner.onClick(view,position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return getReportRes.size();
    }


    protected class ReportHolder extends RecyclerView.ViewHolder  {

        TextView key,value;
        LinearLayout navChildLYT;

        public ReportHolder(View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            value = itemView.findViewById(R.id.value);
            navChildLYT = itemView.findViewById(R.id.navChildLYT);
        }
    }

}