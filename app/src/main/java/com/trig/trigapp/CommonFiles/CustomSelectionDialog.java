package com.trig.trigapp.CommonFiles;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trig.trigapp.R;

import java.util.ArrayList;

public class CustomSelectionDialog extends Dialog implements
            android.view.View.OnClickListener {

        public Activity mActivity;
        public Dialog d;
        public TextView titledialog;
        public RecyclerView listdialog;
        private ArrayList<String> dataList;
        private DialogItemClickListAdapter dialogItemClickListAdapter;
        private int selectedPos;
        private int po;
        private String title;

        public CustomSelectionDialog(Activity activity, ArrayList<String> dataList, int selectedPos, int po, String title) {
            super(activity);
            this.mActivity = activity;
            this.dataList = dataList;
            this.selectedPos = selectedPos;
            this.po = po;
            this.title = title;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            try {
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.selection_popup_mainview);
                titledialog = findViewById(R.id.titledialog);
                listdialog = findViewById(R.id.listdialog);
                titledialog.setText(title);
                listdialog.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
                dialogItemClickListAdapter = new DialogItemClickListAdapter(dataList, po);
                listdialog.setAdapter(dialogItemClickListAdapter);
            } catch (Exception e){
                Log.e("TAG", "onCreate: " + e.getMessage() );
            }
        }

        @Override
        public void onClick(View v) {

        }

        private class DialogItemClickListAdapter extends RecyclerView.Adapter<DialogItemClickListAdapter.MyViewHolder> {
            private ArrayList<String> mango;
            private int selectedPosition;

            public class MyViewHolder extends RecyclerView.ViewHolder {
                public View view;
                public TextView listitemdialogText;
                public ImageView selectedRB, NotselectedRB;
                private ConstraintLayout mainLayoutClickable;

                public MyViewHolder(View v) {
                    super(v);
                    try {
                        listitemdialogText = v.findViewById(R.id.listitemdialogText);
                        selectedRB = v.findViewById(R.id.selectedRB);
                        NotselectedRB = v.findViewById(R.id.NotselectedRB);
                        mainLayoutClickable = v.findViewById(R.id.mainLayoutClickable);
                    } catch (Exception e){
                        Log.e("TAG", "MyViewHolder: " + e.getMessage() );
                    }
                }
            }

            public DialogItemClickListAdapter(ArrayList<String> myDataset, int po) {
                mango = myDataset;
                this.selectedPosition = po;
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
                    Log.e("ChildProfileActivity", "Mango size: " + mango.size());
                    holder.listitemdialogText.setText(mango.get(position));
                    //position is male or female
                    if (selectedPosition == position) {
                        holder.selectedRB.setVisibility(View.VISIBLE);
                    } else {
                        holder.selectedRB.setVisibility(View.GONE);
                    }

                    holder.mainLayoutClickable.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //selectedPosition is autoselected one and position is which user is going to select at the moment
                            if (selectedPosition != position) {
                                selectedPosition = position; //position will be 0 onwards depending on item selected eg. male is 0, female will be 1
                                dialogItemClickListAdapter.notifyDataSetChanged();
                                if (selectedPos == 0) {
                                    setTextForValues(0, position, mango);
                                } else if (selectedPos == 1) {
                                    setTextForValues(1, position, mango);
                                } else if (selectedPos == 2) {
                                    setTextForValues(2, position, mango);
                                } else if (selectedPos == 3) {
                                    setTextForValues(3, position, mango);
                                }
                            }else{
                                dismiss();
                            }
                        }
                    });
                } catch (Exception e){
                    Log.e("TAG", "onBindViewHolder: " + e.getMessage() );
                }

            }

            @Override
            public int getItemCount() {
                return mango.size();
            }
        }

        private void setTextForValues(int selectedType, int selectedItemPosition, ArrayList<String> dataList) {

            try {
                if (selectedType == 0) {
                    //edit_category.setText(dataList.get(selectedItemPosition));
                }
                dismiss();
            } catch (Exception e){
                Log.e("TAG", "setTextForValues: " + e.getMessage() );
            }
        }

    }
