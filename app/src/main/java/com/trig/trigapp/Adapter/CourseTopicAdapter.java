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
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.getCourseListRes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CourseTopicAdapter extends RecyclerView.Adapter<CourseTopicAdapter.MyVi> {

    ArrayList mValues;
    Context mContext;
    protected ItemListener mListener;
    private static final String TAG = "CourseTopicAdapter";

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
        holder.setData((getCourseListRes) mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(getCourseListRes item);
    }


    public class MyVi extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView cardHeading, totalCourses, completedCourses, progressEndPercentage;
        public ImageView imageView;
        public ConstraintLayout relativeLayout;
        public ProgressBar courseCompletionProgess;
        getCourseListRes item;

        public MyVi(View v) {

            super(v);

            try {

            } catch (Exception e) {
                Log.e(TAG, "MyVi: exception" +e.getMessage() );
            }
            v.setOnClickListener(this);
            cardHeading = (TextView) v.findViewById(R.id.cardHeading);
            totalCourses = (TextView) v.findViewById(R.id.totalCourses);
            completedCourses = (TextView) v.findViewById(R.id.completedCourses);
            relativeLayout = (ConstraintLayout) v.findViewById(R.id.relativeLayout);
            courseCompletionProgess = (ProgressBar) v.findViewById(R.id.courseCompletionProgess);
            progressEndPercentage = (TextView) v.findViewById(R.id.progressEndPercentage);
        }

        public void setData(getCourseListRes item) {
            try {
                this.item = item;

                cardHeading.setText(item.getTopic_name());
                totalCourses.setText(String.valueOf(item.getAll()));
                completedCourses.setText(String.valueOf(item.getCompleted()));

                float percentage = (float)((item.getCompleted()  * 100)/ item.getAll());
//                courseCompletionProgess.setProgress(1);
                courseCompletionProgess.setProgress((int) percentage);
                progressEndPercentage.setText(Math.round(percentage) + " %");
            } catch (Exception e) {
                Log.e(TAG, "setData: exception " + e.getMessage() );
            }
        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }
}