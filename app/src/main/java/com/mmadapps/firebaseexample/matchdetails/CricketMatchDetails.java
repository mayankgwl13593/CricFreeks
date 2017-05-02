package com.mmadapps.firebaseexample.matchdetails;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.mmadapps.firebaseexample.R;
import com.mmadapps.firebaseexample.customs.ConnectionDetector;
import com.mmadapps.firebaseexample.customs.SnackBar;


import com.mmadapps.firebaseexample.matchdetails.matchdetailsbean.GetCricketMatchDetails;
import com.mmadapps.firebaseexample.matchdetails.matchcommentarybean.GetCricketMatchCommentary;

import com.mmadapps.firebaseexample.utils.OnResponseListner;
import com.mmadapps.firebaseexample.utils.WebServices;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Mayank.gupta on 4/13/2017.
 */

public class CricketMatchDetails extends Activity implements OnResponseListner{
    String unique_id;
    ConnectionDetector mDetector;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricketmatch);
        Bundle bundle=getIntent().getExtras();
        unique_id=bundle.getString("unique_id");
        Log.e("Score",unique_id);
        init();
        mDetector = new ConnectionDetector(CricketMatchDetails.this);
        if (mDetector.isConnectingToInternet()) {
            callCricketMatch(unique_id);


        } else {
            SnackBar.makeText(CricketMatchDetails.this, getString(R.string.please_check_internet), Toast.LENGTH_SHORT).show();
        }

    }

    private void callCricketMatch(String unique_id) {
        WebServices<GetCricketMatchDetails> getCricketMatchDetailsWebServices = new WebServices<>(CricketMatchDetails.this);
        getCricketMatchDetailsWebServices.getMatcheDetails(WebServices.PublicDevService, WebServices.ApiType.getMatcheDetails, "VNmGckEG1UP5HOBxzIxgNuccdsc2",unique_id);

    }
    private void callCricketMatchCommentary(String unique_id) {
        WebServices<GetCricketMatchCommentary> getCricketMatchCommentaryWebServices = new WebServices<>(CricketMatchDetails.this);
        getCricketMatchCommentaryWebServices.getMatchCommentary(WebServices.PublicDevService, WebServices.ApiType.getMatchCommentary, "VNmGckEG1UP5HOBxzIxgNuccdsc2",unique_id);



    }

    private void init() {
    }

    @Override
    public void onResponse(Object response , WebServices.ApiType URL, boolean isSucces) {
        if (URL == WebServices.ApiType.getMatcheDetails) {
            if (isSucces) {

                GetCricketMatchDetails getCricketMatchDetails = (GetCricketMatchDetails) response;
                if (getCricketMatchDetails != null) {
                    if (getCricketMatchDetails.getCache()) {
                        Log.e("Score1",getCricketMatchDetails.getScore());
                        callCricketMatchCommentary(unique_id);
                    }


                } else {
                    SnackBar.makeText(CricketMatchDetails.this, R.string.no_data, SnackBar.LENGTH_SHORT).show();
                }

            }
        }
        if (URL == WebServices.ApiType.getMatchCommentary) {
            if (isSucces) {

                GetCricketMatchCommentary getCricketMatchCommentary = (GetCricketMatchCommentary) response;
                if (getCricketMatchCommentary != null) {
                    if (getCricketMatchCommentary.getCache()) {
                        Log.e("Score2",getCricketMatchCommentary.getCommentary());


                    }
                } else {
                    SnackBar.makeText(CricketMatchDetails.this, R.string.no_data, SnackBar.LENGTH_SHORT).show();
                }

            }
        }
    }
}
