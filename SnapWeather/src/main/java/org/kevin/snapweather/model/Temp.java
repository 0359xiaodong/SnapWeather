package org.kevin.snapweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thinkfeed#gmail.com on 13-5-28.
 */
public class Temp {
    @SerializedName("day")
    public double day;
    @SerializedName("max")
    public double max;
    @SerializedName("min")
    public double min;
    @SerializedName("night")
    public double night;
    @SerializedName("eve")
    public double eve;
    @SerializedName("morn")
    public double morn;

    public double getDay() {
        return day;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getNight() {
        return night;
    }

    public double getEve() {
        return eve;
    }

    public double getMorn() {
        return morn;
    }
}

