package com.trig.trigapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.trig.trigapp.Model.DataModel;
import com.trig.trigapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CourseTopicAdapter extends RecyclerView.Adapter<CourseTopicAdapter.MyVi> {

    ArrayList mValues;
    Context mContext;
    protected ItemListener mListener;

    public CourseTopicAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener=itemListener;
    }


    @Override
    public MyVi onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.course_topics_item, parent, false);

        return new MyVi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyVi holder, int position) {
        holder.setData((DataModel) mValues.get(position));
    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(DataModel item);
    }


    public class MyVi extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView cardHeading, totalCourses, completedCourses;
        public ImageView imageView;
        public ConstraintLayout relativeLayout;
        public ProgressBar courseCompletionProgess;
        DataModel item;

        public MyVi(View v) {

            super(v);

            v.setOnClickListener(this);
            cardHeading = (TextView) v.findViewById(R.id.cardHeading);
            totalCourses = (TextView) v.findViewById(R.id.totalCourses);
            completedCourses = (TextView) v.findViewById(R.id.completedCourses);
            relativeLayout = (ConstraintLayout) v.findViewById(R.id.relativeLayout);
            courseCompletionProgess = (ProgressBar) v.findViewById(R.id.courseCompletionProgess);

        }

        public void setData(DataModel item) {
            this.item = item;

            cardHeading.setText(item.cardHeading);
            totalCourses.setText(String.valueOf(item.totalCourses));
            completedCourses.setText(String.valueOf(item.completedCourses));

            float percentage = (float)((item.completedCourses  * 100)/ item.totalCourses);
            courseCompletionProgess.setProgress((int) percentage);
        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }
}