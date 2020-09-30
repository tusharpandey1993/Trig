package com.trig.trigapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.VideoListResponse;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.MyVi> {

    private static final String TAG = "CourseTopicAdapter";
    ArrayList mValuesAssessment;
    Context mContext;
    protected VideoListAdapter.ItemListener mListener;
    ConstraintLayout childMainLYT;

    public VideoListAdapter(Context context, ArrayList values, VideoListAdapter.ItemListener itemListener) {
        mValuesAssessment = values;
        mContext = context;
        mListener=itemListener;
    }


    @Override
    public VideoListAdapter.MyVi onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.video_list_child_view, parent, false);

        return new VideoListAdapter.MyVi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VideoListAdapter.MyVi holder, int position) {
        holder.setData((VideoListResponse) mValuesAssessment.get(position));
    }

    @Override
    public int getItemCount() {
        return mValuesAssessment.size();
    }

    public interface ItemListener {
        void onItemClick(VideoListResponse item);
    }


    public class MyVi extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView listViewTitle, status, date;
        public ProgressBar courseCompletionProgess;
        VideoListResponse item;

        public MyVi(View v) {

            super(v);

            v.setOnClickListener(this);

            childMainLYT=v.findViewById(R.id.childMainLYT);
            listViewTitle=v.findViewById(R.id.listViewTitle);
            status=v.findViewById(R.id.status);
            date=v.findViewById(R.id.date);
            childMainLYT.setOnClickListener(this);

            v.setOnClickListener(this);
        }

        public void setData(VideoListResponse item) {
            this.item = item;

            listViewTitle.setText(item.getCourse_name());
            status.setText(item.getStatus());
            date.setText(item.getCourse_start_date());
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }
}