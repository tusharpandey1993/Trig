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
import com.trig.trigapp.CommonFiles.Constants;
import com.trig.trigapp.R;
import com.trig.trigapp.api.Response.getAssessmentListRes;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyVi> {

    ArrayList mValuesAssessment;
    Context mContext;
    protected AssessmentTopicsAdapter.ItemListener mListener;
    private static final String TAG = "CourseTopicAdapter";

    public UserListAdapter(Context context, ArrayList values, AssessmentTopicsAdapter.ItemListener itemListener) {
        mValuesAssessment = values;
        mContext = context;
        mListener=itemListener;
    }


    @Override
    public UserListAdapter.MyVi onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.user_single, parent, false);

        return new UserListAdapter.MyVi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserListAdapter.MyVi holder, int position) {
        holder.setData((getAssessmentListRes) mValuesAssessment.get(position));
    }

    @Override
    public int getItemCount() {
        return mValuesAssessment.size();
    }

    public interface ItemListener {
        void onItemClick(getAssessmentListRes item);
    }


    public class MyVi extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView key, value;
        getAssessmentListRes item;

        public MyVi(View v) {
            super(v);
            v.setOnClickListener(this);
            key = (TextView) v.findViewById(R.id.key);
            value = (TextView) v.findViewById(R.id.value);
        }

        public void setData(getAssessmentListRes item) {
            this.item = item;

            key.setText(""+item.getAssessment_name());
            value.setText(""+item.getAssigned_date());

        }



        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }


}