package com.trig.trigapp.CommonFiles;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.trig.trigapp.R;

public class ViewDialogCustom extends DialogFragment {


    //    Cancel and Action 2 BUTTON
    public static void customDialog2Btn(final Activity activity, String msg, String btnLeftMsg, String btnRightMsg, final Integer FunctionNum, final onDialogClickCallback onDialogClickCallback) {

        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);
        int layout_parms;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layout_parms = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layout_parms = WindowManager.LayoutParams.TYPE_PHONE;
        }
        new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                layout_parms,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_two_btn);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.dialog_bg)));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

//        TextView text = dialog.findViewById(R.id.txt_file_path);
//        text.setText(msg);

/*        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setText(btnLeftMsg);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                dialog.cancel();
                onDialogClickCallback.onLeftClick(activity, Constants.leftClick, FunctionNum);

            }
        });

        Button btn_okay = (Button) dialog.findViewById(R.id.btn_okay);
        btn_okay.setText(btnRightMsg);
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                onDialogClickCallback.onRightClick(activity, Constants.rightClick, FunctionNum);

            }
        });*/
        dialog.show();
    }


 /*   //    Only OK  1 BUTTON
    public static void customDialog1btn(Activity activity, String msg, String btnMsg, Integer FunctionNum, Boolean dialogDismiss,onDialogClickCallback onDialogClickCallback) {

        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);
        int layout_parms;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layout_parms = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layout_parms = WindowManager.LayoutParams.TYPE_PHONE;
        }
        new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                layout_parms,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialogbox_with_ok);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.dialog_bg)));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        TextView text = (TextView) dialog.findViewById(R.id.txt_file_path);
        text.setText(msg);

        TextView btn_okay = (TextView) dialog.findViewById(R.id.btn_okay);
        btn_okay.setText(btnMsg);
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                onDialogClickCallback.onOkClick(activity, Constants.Ok, FunctionNum);

            }
        });
        dialog.show();
        if(dialogDismiss){
            dismissDialogFewSeconds(dialog);
        }
    }

    public static void dismissDialogFewSeconds(Dialog dialog) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 5000);
    }*/
}
