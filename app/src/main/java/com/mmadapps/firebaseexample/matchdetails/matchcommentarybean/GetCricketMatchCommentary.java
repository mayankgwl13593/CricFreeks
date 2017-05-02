
package com.mmadapps.firebaseexample.matchdetails.matchcommentarybean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCricketMatchCommentary {

    @SerializedName("commentary")
    @Expose
    private String commentary;
    @SerializedName("cache")
    @Expose
    private Boolean cache;
    @SerializedName("v")
    @Expose
    private String v;
    @SerializedName("ttl")
    @Expose
    private Integer ttl;
    @SerializedName("provider")
    @Expose
    private Provider provider;
    @SerializedName("creditsLeft")
    @Expose
    private Integer creditsLeft;

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Boolean getCache() {
        return cache;
    }

    public void setCache(Boolean cache) {
        this.cache = cache;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Integer getCreditsLeft() {
        return creditsLeft;
    }

    public void setCreditsLeft(Integer creditsLeft) {
        this.creditsLeft = creditsLeft;
    }

}
