package com.trig.trigapp.CommonFiles;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogBuilder;
import com.trig.trigapp.CustomViewsFiles.genericPopUp.GenericDialogPopup;
import com.trig.trigapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Utility {

    private static Utility utilities;

    public static Utility getInstance() {
        if (utilities == null) {
            utilities = new Utility();
        }
        return utilities;
    }

    public void hideKeyboard(Activity ctx) {
        try {
            InputMethodManager inputManager = (InputMethodManager) ctx
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            View v = ((Activity) ctx).getCurrentFocus();
            if (v == null)
                return;

            inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception e) {
            Log.d("util", "hideKeyboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public synchronized Snackbar showSnackbar(View content, String toast) {

        Snackbar snackbar = null;

        if (content != null) {
            try {
                snackbar = Snackbar.make(content, toast, Snackbar.LENGTH_LONG);
                View view1 = snackbar.getView();
                TextView textView = (TextView) view1.findViewById(R.id.snackbar_text);
                textView.setMaxLines(5);
                snackbar.show();

            } catch (Exception e) {
            }
        }
        return snackbar;
    }

    /**
     * Is email valid.
     *
     * @param email Pass a email in string format and this method check if it is
     *              a valid address or not.
     * @return True if email is valid otherwise false.
     */
    public boolean isEmailValid(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public boolean isNetworkAvailable(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        if (context!=null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected())
                        haveConnectedWifi = true;
                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (ni.isConnected())
                        haveConnectedMobile = true;
            }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void hideKeyboard(Context ctx) {
        try {
            InputMethodManager inputManager = (InputMethodManager) ctx
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            View v = ((Activity) ctx).getCurrentFocus();
            if (v == null)
                return;

            inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception e) {
            Log.d("util", "hideKeyboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // This will focus on specific edit text without opening keyboard
    public void requestFocus(Context context, View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // Show generic Dialog PopUp
    public void showDynamicDialog(Context context, GenericDialogBuilder genericDialogBuilder, GenericDialogPopup genericDialogPopup, FragmentManager fragmentManager) {
        genericDialogPopup = new GenericDialogPopup(genericDialogBuilder);
        genericDialogPopup.show(fragmentManager, "TAG");
    }

    public String[] permissions =
            {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
// Manifest.permission.READ_EXTERNAL_STORAGE,
// Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.MODIFY_AUDIO_SETTINGS,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
// Manifest.permission.SEND_SMS,
// Manifest.permission.READ_SMS,
// Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
            };

    @RequiresApi(api = Build.VERSION_CODES.M)
    public  boolean arePermissionsEnabled(Activity activity) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public  void requestMultiplePermissions(Activity activity) {
        List<String> remainingPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                remainingPermissions.add(permission);
            }
        }
        activity.requestPermissions(remainingPermissions.toArray(new String[remainingPermissions.size()]), 101);
    }
    public boolean isAppOnForeground(Context context, String pckgName) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            if (appProcesses == null) {
                return false;
            }
            final String packageName = pckgName;
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                    //                Log.e("app",appPackageName);
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }



}
