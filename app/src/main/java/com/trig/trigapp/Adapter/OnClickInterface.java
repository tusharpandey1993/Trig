package com.trig.trigapp.Adapter;

import android.view.View;

public interface OnClickInterface {

    default void   onClick(View view, int position){}

    default void   onClick(View view, int position,String selectedValue,String SelectedID,String type){}

    default void   onClickQuiz(View view, int position, int id, int assismentID,String optionID, int assessmentQuestionId){}

//    void  onClickChildView(View view, String viewID, int position);

//    void  onAppsStoreChildViewClicked(View view, String viewID, int position, int AppID);


}
