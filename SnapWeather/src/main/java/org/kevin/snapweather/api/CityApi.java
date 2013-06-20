package org.kevin.snapweather.api;

import android.os.Handler;
import android.util.Log;
import org.json.JSONObject;
import org.kevin.snapweather.net.AsyncHttpResponseHandler;
import org.kevin.snapweather.net.RequestParams;
import org.kevin.snapweather.rest.RestClient;

/**
 * Created by think on 13-6-20.
 */
public class CityApi {
    protected static final String TAG = CityApi.class.getSimpleName();
    private static final String SEARCH_CITY_URL = "http://query.yahooapis.com/v1/public/yql";
    private static final String YQL_GEO_PLACES = "select * from geo.places where text=";

    public static void searchCity(final Handler handler ,String city){
        RequestParams params = new RequestParams();
        params.put("q",YQL_GEO_PLACES+"\""+city.trim()+"\"");
        params.put("format","json");
        RestClient.get(SEARCH_CITY_URL,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, String content) {
                Log.d(TAG,content);
            }
        });
    }
}
