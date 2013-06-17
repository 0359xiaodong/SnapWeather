package org.kevin.snapweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thinkfeed#gmail.com on 13-5-18.
 */
public class Sys {
    @SerializedName("country")
    public String country;
    @SerializedName("sunrise")
    public long sunrise;
    @SerializedName("sunset")
    public long sunset;

    public String getCountry() {
        return country;
    }

    public long getSunset() {
        return sunset;
    }

    public long getSunrise() {
        return sunrise;
    }
}
