package com.mmadapps.firebaseexample.utils;

import android.content.Context;

import com.mmadapps.firebaseexample.R;


/**
 * Created by Jinesh on 28-06-2016.
 */
public class ProgressDialog {
    private static android.app.ProgressDialog pdLoading = null;

    public static android.app.ProgressDialog getInstance(Context context) {
        if (pdLoading == null || pdLoading.getContext() != context) {
            pdLoading = new android.app.ProgressDialog(context);
            pdLoading.setMessage(context.getResources().getString(R.string.please_wait));
            pdLoading.setCancelable(false);
            pdLoading.setCanceledOnTouchOutside(false);
            return pdLoading;
        }

        return pdLoading;
    }
}
