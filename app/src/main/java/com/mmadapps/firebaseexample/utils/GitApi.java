package com.mmadapps.firebaseexample.utils;


import com.mmadapps.firebaseexample.matchdetails.matchcommentarybean.GetCricketMatchCommentary;
import com.mmadapps.firebaseexample.matchdetails.matchdetailsbean.GetCricketMatchDetails;
import com.mmadapps.firebaseexample.matches.matchesbean.GetCricketMatches;
import com.mmadapps.firebaseexample.news.newsbean.GetCricketNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**

 */
public interface GitApi {

    @GET("cricket")
    Call<GetCricketMatches> getCricketMatchesCall(@Header("apikey") String apikey);

    @GET("cricketNews")
    Call<GetCricketNews> getCricketNewsCall(@Header("apikey") String apikey);

    @GET("cricketScore")
    Call<GetCricketMatchDetails> getGetCricketMatchDetailsCall(@Header("apikey") String apikey, @Query("unique_id") String unique_id);

    @GET("cricketCommentary")
    Call<GetCricketMatchCommentary> getCricketMatchCommentaryCall(@Header("apikey") String apikey, @Query("unique_id") String unique_id);
}
