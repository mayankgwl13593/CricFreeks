package com.mmadapps.firebaseexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mmadapps.firebaseexample.matches.MainActivity;

/**
 * Created by Mayank.gupta on 4/13/2017.
 */

public class SplashActivity extends Activity{
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        // Splash screen timer
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }

