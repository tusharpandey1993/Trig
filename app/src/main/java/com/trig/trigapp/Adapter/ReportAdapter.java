package com.trig.trigapp.Adapter;

import android.content.Context;
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

        setAdapter(holder,position);

    }

    public void setAdapter(ReportAdapter.ReportHolder holder,int pos){
        if(getReportRes!=null) {
            holder.furtherDetailsRecycler.setNestedScrollingEnabled(false);
            ReportChildAdapter asCommonAdapter = new ReportChildAdapter(context, onClickListner, getReportRes.get(pos));
            holder.furtherDetailsRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            holder.furtherDetailsRecycler.setAdapter(asCommonAdapter);
        }
    }



    @Override
    public int getItemCount() {
        return getReportRes.size();
    }


    protected class ReportHolder extends RecyclerView.ViewHolder  {

        RecyclerView furtherDetailsRecycler;

        public ReportHolder(View itemView) {
            super(itemView);
            furtherDetailsRecycler = itemView.findViewById(R.id.furtherDetailsRecycler);
        }
    }

}