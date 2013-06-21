package org.kevin.snapweather.rest;

import org.kevin.snapweather.net.AsyncHttpClient;
import org.kevin.snapweather.net.AsyncHttpResponseHandler;
import org.kevin.snapweather.net.RequestParams;
import org.kevin.snapweather.util.Log;

/**
 * Created by thinkfeeed#gmail.com on 13-5-17.
 */
public class RestClient {
    private static final String TAG = RestClient.class.getSimpleName();
    public static String base_url = "http://api.openweathermap.org";

    public static AsyncHttpClient client = new AsyncHttpClient();


    public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.put(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void delete(String url, AsyncHttpResponseHandler responseHandler) {
        client.delete(getAbsoluteUrl(url), responseHandler);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        if(relativeUrl.startsWith("http")){
            return relativeUrl;
        }
        String url = base_url + relativeUrl;
        Log.d(TAG, url);
        return url;
    }
}
