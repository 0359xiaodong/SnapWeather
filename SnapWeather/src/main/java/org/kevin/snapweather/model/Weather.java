package org.kevin.snapweather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thinkfeed#gmail.com on 13-5-18.
 */
public class Weather {
    @SerializedName("id")
    public int id;
    @SerializedName("main")
    public String main;
    @SerializedName("description")
    public String description;
    @SerializedName("icon")
    public String icon;

    public int getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public String getMain() {
        return main;
    }
}
