package com.example.testgooglesearch.Models.ResponseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metatag {

    @SerializedName("referrer")
    @Expose
    private String referrer;
    @SerializedName("og:image")
    @Expose
    private String ogImage;

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getOgImage() {
        return ogImage;
    }

    public void setOgImage(String ogImage) {
        this.ogImage = ogImage;
    }
}
