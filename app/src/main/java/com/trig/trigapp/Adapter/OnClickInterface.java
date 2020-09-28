package com.trig.trigapp.Adapter;

import android.view.View;

public interface OnClickInterface {

    default void   onClick(View view, int position){}

//    void  onClickChildView(View view, String viewID, int position);

//    void  onAppsStoreChildViewClicked(View view, String viewID, int position, int AppID);


}
