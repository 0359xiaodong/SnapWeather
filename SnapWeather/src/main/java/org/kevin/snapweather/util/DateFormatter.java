package org.kevin.snapweather.util;

import java.text.SimpleDateFormat;

/**
 * Created by thinkfeed#gmail.com on 13-5-28.
 */
public class DateFormatter {

    public static String format(long miles){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(miles);
        return date;
    }
}
