package com.trig.trigapp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.CommonFiles.DataPayload;
import com.trig.trigapp.R;

import java.util.ArrayList;

public class DialogItemClickListAdapter extends RecyclerView.Adapter<DialogItemClickListAdapter.MyViewHolder> {

    private DataPayload payload;
    private OnClickInterface mListner;
    private ArrayList<String> dataList;

    public DialogItemClickListAdapter(DataPayload payload, OnClickInterface mListner) {
        this.payload = payload;
        this.mListner = mListner;
        dataList = payload.getDataList();
    }

    @Override
    public DialogItemClickListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selection_popup_childview, parent, false);
        DialogItemClickListAdapter.MyViewHolder vh = new DialogItemClickListAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DialogItemClickListAdapter.MyViewHolder holder, final int position) {
        try {
            holder.listitemdialogText.setText(dataList.get(position));

            holder.mainLayoutClickable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(payload.getTitle().equalsIgnoreCase("Branch")){
                        mListner.onClick(v,position,dataList.get(position),"" +payload.getGetBranchResArrayList().get(position).getBranchID(),payload.getTitle());
                    }else{
                        mListner.onClick(v,position,dataList.get(position),"" +payload.getGetUnitResArrayList().get(position).getUnitID(),payload.getTitle());
                    }

                }
            });
        } catch (Exception e){
            Log.e("TAG", "onBindViewHolder: " + e.getMessage() );
        }

    }

    //This method will filter the list
    //here we are passing the filtered data
    //and assigning it to the list with notifydatasetchanged method
    public void filterList(ArrayList<String> filterdData) {
        dataList = filterdData;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(payload != null) {
            return dataList.size();
        } else {
            return 0;
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView listitemdialogText;
        private ConstraintLayout mainLayoutClickable;
        private int selectedPos;
        public MyViewHolder(View v) {
            super(v);
            try {
                listitemdialogText = v.findViewById(R.id.listitemdialogText);
                mainLayoutClickable = v.findViewById(R.id.mainLayoutClickable);
            } catch (Exception e){
                Log.e("TAG", "MyViewHolder: " + e.getMessage() );
            }
        }
    }

}
