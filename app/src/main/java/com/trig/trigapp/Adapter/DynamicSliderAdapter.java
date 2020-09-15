package com.trig.trigapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.R;

public class DynamicSliderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnClickInterface {



    View view;

    private Context mContext;
    OnClickInterface mListener = null;
    public DynamicSliderAdapter(Context context, OnClickInterface onClickInterface) {
        this.mContext = context;
        this.mListener = onClickInterface;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        view = layoutInflater.inflate(R.layout.video_list_child_view, parent, false);
        viewHolder = new VideoListViewHolder(view,mContext,mListener);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VideoListViewHolder vh5 = (VideoListViewHolder) holder;
        vh5.configureModels();
    }

    @Override
    public int getItemCount() {
        return 10 ;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    @Override
    public void onClick(View view, int position) {

    }
}

