package org.kevin.snapweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thinkfeed#gmail.com on 13-5-18.
 */
public class Wind {
    @SerializedName("speed")
    public double speed;
    @SerializedName("deg")
    public double deg;

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }
}
