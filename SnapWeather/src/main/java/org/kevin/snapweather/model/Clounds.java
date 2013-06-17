package org.kevin.snapweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by think on 13-5-18.
 */
public class Clounds {
    @SerializedName("all")
    public double all;

    public double getAll() {
        return all;
    }
}
