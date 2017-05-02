package com.mmadapps.firebaseexample.utils;

/**
 * Created by sowmya on 11/21/2016.
 */
public interface OnResponseListner<T> {
    void onResponse(T response, WebServices.ApiType URL, boolean isSucces);
}
