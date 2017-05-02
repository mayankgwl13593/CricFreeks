package com.mmadapps.firebaseexample.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.IntentService;
import android.content.Context;

import com.mmadapps.firebaseexample.customs.ConnectionDetector;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mayank.gupta on 3/9/2017.
 */

public class WebServices<T> {

    private T t;
    Call<T> call = null;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    ApiType apiTypeVariable;
    Context context;
    OnResponseListner<T> onResponseListner;
    android.app.ProgressDialog pdLoading;
    private static OkHttpClient.Builder builder;

    File httpCacheDirectory;
    Cache cache;

    public enum ApiType {
       getMatches,getNews,getMatcheDetails,getMatchCommentary
    }




    public final static String PublicDevService=" http://cricapi.com/api/";
    public final static String PublicService = PublicDevService;

    public WebServices(OnResponseListner<T> onResponseListner) {
        if (onResponseListner instanceof Activity) {
            this.context = (Context) onResponseListner;
        } else if (onResponseListner instanceof IntentService) {
            this.context = (Context) onResponseListner;
        } else if (onResponseListner instanceof android.app.DialogFragment) {
            android.app.DialogFragment dialogFragment = (android.app.DialogFragment) onResponseListner;
            this.context = dialogFragment.getActivity();
        } else if(onResponseListner instanceof android.support.v4.app.Fragment) {
            android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) onResponseListner;
            this.context = fragment.getActivity();
        }else{
            Fragment fragment = (Fragment) onResponseListner;
            this.context = fragment.getActivity();
        }
        this.onResponseListner= onResponseListner;

        httpCacheDirectory = new File( context.getCacheDir(), "responses");
        int cacheSize = 5 * 1024 * 1024; // 10 MiB
        cache = new Cache(httpCacheDirectory, cacheSize);

        builder = getHttpClient();
    }



    public OkHttpClient.Builder getHttpClient() {
        if (builder == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.addInterceptor(loggingInterceptor);
            client.writeTimeout(60000, TimeUnit.MILLISECONDS);
            client.readTimeout(60000,TimeUnit.MILLISECONDS);
            client.connectTimeout(60000,TimeUnit.MILLISECONDS);
            return client;
        }
        return builder;
    }



    public void getMatches(String api, ApiType apiTypes,String apikey) {

        apiTypeVariable = apiTypes;
        try {
            pdLoading = ProgressDialog.getInstance(context);
            pdLoading.show();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
       call = (Call<T>) gi.getCricketMatchesCall(apikey);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                   onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
            if (pdLoading.isShowing())
                pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }
    public void getMatcheDetails(String api, ApiType apiTypes,String apikey,String unique_id) {

        apiTypeVariable = apiTypes;
        try {
            pdLoading = ProgressDialog.getInstance(context);
            pdLoading.show();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getGetCricketMatchDetailsCall(apikey,unique_id);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }
    public void getMatchCommentary(String api, ApiType apiTypes,String apikey,String unique_id) {

        apiTypeVariable = apiTypes;
        try {
            pdLoading = ProgressDialog.getInstance(context);
            pdLoading.show();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getCricketMatchCommentaryCall(apikey,unique_id);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }


    public void getNews(String api, ApiType apiTypes,String apikey) {

        apiTypeVariable = apiTypes;
        try {
            pdLoading = ProgressDialog.getInstance(context);
            pdLoading.show();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
        GitApi gi = retrofit.create(GitApi.class);
        call = (Call<T>) gi.getCricketNewsCall(apikey);
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                t = (T) response.body();
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(t, apiTypeVariable, true);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (pdLoading.isShowing())
                    pdLoading.cancel();
                onResponseListner.onResponse(null, apiTypeVariable, false);
            }

        });
    }


}
