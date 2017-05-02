package com.mmadapps.firebaseexample.customs;

import android.app.Activity;
import android.app.Application;
import android.graphics.Typeface;
import android.os.Bundle;

import com.mmadapps.firebaseexample.SplashActivity;


/**
 * Created by Mayank.gupta on 4/13/2017.
 */

public class CrickFreaksApplication extends Application implements Application.ActivityLifecycleCallbacks
{
    public Typeface ALLER_BOLD;
    public   Typeface ALLER_REGULAR;

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if(activity instanceof SplashActivity)
        {
            ALLER_BOLD=Typeface.createFromAsset(activity.getAssets(), "AllerBold.ttf");
            ALLER_REGULAR=Typeface.createFromAsset(activity.getAssets(), "AllerRegular.ttf");

        }

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
