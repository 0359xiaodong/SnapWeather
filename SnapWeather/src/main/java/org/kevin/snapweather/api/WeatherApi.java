package org.kevin.snapweather.api;

import android.os.Handler;
import android.util.Log;

import org.kevin.snapweather.model.WeatherForecast;
import org.kevin.snapweather.model.WeatherToday;
import org.kevin.snapweather.net.AsyncHttpResponseHandler;
import org.kevin.snapweather.net.RequestParams;
import org.kevin.snapweather.rest.RestClient;
import org.kevin.snapweather.util.Constants;
import org.kevin.snapweather.util.JsonParser;

/**
 * Created by thinkfeed#gmail.com on 13-5-17.
 */
public class WeatherApi {
    private static final String TAG = WeatherApi.class.getSimpleName();
    private static final String WEATHER_TODAY_SUFFIX_URL = "/data/2.5/weather";
    private static final String WEATHER_FORECAST_SUFFIX_URL = "/data/2.5/forecast/daily";
    private static final String APPID = "89516341f251e02d934e044d09d5001d";

    public static void getTodayWeather(final Handler handler,String city,String lang){
        RequestParams params = new RequestParams();
        params.put("q",city);
        params.put("appid",APPID);
        params.put("lang",lang);
        params.put("units","metric");

        RestClient.get(WEATHER_TODAY_SUFFIX_URL,params,new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, String content) {
                Log.d(TAG,content);
                WeatherToday today = JsonParser.fromJsonObject(content,WeatherToday.class);
                handler.obtainMessage(Constants.MSG_WEATHER_TODAY,today).sendToTarget();
            }
        });
    }

    public static void getWeatherForecast(final Handler handler,String city,String lang,int cnt){
        RequestParams params = new RequestParams();
        params.put("q",city);
        params.put("appid",APPID);
        params.put("lang",lang);
        params.put("units","metric");
        params.put("cnt",cnt);

        RestClient.get(WEATHER_FORECAST_SUFFIX_URL,params,new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, String content) {
                Log.d(TAG,content);
                WeatherForecast forecast = JsonParser.fromJsonObject(content,WeatherForecast.class);
                handler.obtainMessage(Constants.MSG_WEATHER_FORECAST,forecast).sendToTarget();
            }
        });
    }
}
