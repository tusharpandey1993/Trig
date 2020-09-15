package com.trig.trigapp.CommonFiles;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.trig.trigapp.R;

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



}
