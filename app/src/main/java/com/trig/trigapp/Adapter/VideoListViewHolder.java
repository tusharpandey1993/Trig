package com.trig.trigapp.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.trig.trigapp.R;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

class VideoListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    OnClickInterface mListener = null;
    ConstraintLayout childMainLYT;
    TextView listViewTitle, status, date;

    public VideoListViewHolder(@NonNull View mView,Context mContext, OnClickInterface listener) {
        super(mView);
        mListener = listener;
        childMainLYT=mView.findViewById(R.id.childMainLYT);
        listViewTitle=mView.findViewById(R.id.listViewTitle);
        status=mView.findViewById(R.id.status);
        date=mView.findViewById(R.id.date);
        childMainLYT.setOnClickListener(this);

    }

    public void configureModels() {


    }

    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
}
