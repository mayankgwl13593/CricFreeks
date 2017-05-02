
package com.mmadapps.firebaseexample.matchdetails.matchdetailsbean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCricketMatchDetails {

    @SerializedName("creditsLeft")
    @Expose
    private Integer creditsLeft;
    @SerializedName("provider")
    @Expose
    private Provider provider;
    @SerializedName("ttl")
    @Expose
    private Integer ttl;
    @SerializedName("v")
    @Expose
    private String v;
    @SerializedName("cache")
    @Expose
    private Boolean cache;
    @SerializedName("matchStarted")
    @Expose
    private Boolean matchStarted;
    @SerializedName("team-2")
    @Expose
    private String team2;
    @SerializedName("team-1")
    @Expose
    private String team1;
    @SerializedName("dateTimeGMT")
    @Expose
    private String dateTimeGMT;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("innings-requirement")
    @Expose
    private String inningsRequirement;
    @SerializedName("cache2")
    @Expose
    private Boolean cache2;

    public Integer getCreditsLeft() {
        return creditsLeft;
    }

    public void setCreditsLeft(Integer creditsLeft) {
        this.creditsLeft = creditsLeft;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Boolean getCache() {
        return cache;
    }

    public void setCache(Boolean cache) {
        this.cache = cache;
    }

    public Boolean getMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(Boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getDateTimeGMT() {
        return dateTimeGMT;
    }

    public void setDateTimeGMT(String dateTimeGMT) {
        this.dateTimeGMT = dateTimeGMT;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getInningsRequirement() {
        return inningsRequirement;
    }

    public void setInningsRequirement(String inningsRequirement) {
        this.inningsRequirement = inningsRequirement;
    }

    public Boolean getCache2() {
        return cache2;
    }

    public void setCache2(Boolean cache2) {
        this.cache2 = cache2;
    }

}
