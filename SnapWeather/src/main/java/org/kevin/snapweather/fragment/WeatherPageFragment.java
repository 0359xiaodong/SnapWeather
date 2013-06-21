package org.kevin.snapweather.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.kevin.snapweather.R;
import org.kevin.snapweather.api.CityApi;
import org.kevin.snapweather.api.WeatherApi;
import org.kevin.snapweather.model.Forecast;
import org.kevin.snapweather.model.WeatherForecast;
import org.kevin.snapweather.model.WeatherToday;
import org.kevin.snapweather.util.Constants;
import org.kevin.snapweather.util.DateFormatter;
import org.kevin.snapweather.util.IconUtil;

import java.util.List;

/**
 * Created by thinkfeed#gmail.com on 13-5-19.
 */
public class WeatherPageFragment extends Fragment {
    protected static final String TAG = WeatherPageFragment.class.getSimpleName();
    private TextView mTodayTemp;
    private ImageView mWeatherIcon;
    private TextView mLowHighTemp;
    private TextView mCondition;
    private TextView mHumidity;
    private TextView mWindSpeed;
    private ImageView mWindDirectIcon;
    private TextView mWindDirect;
    private String mWeatherCity;

    public WeatherPageFragment(String city) {
        mWeatherCity = city;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_page, container, false);
        mTodayTemp = (TextView) view.findViewById(R.id.weather_today_temp);
        mWeatherIcon = (ImageView) view.findViewById(R.id.weather_icon);
        mLowHighTemp = (TextView) view.findViewById(R.id.weather_lowhigh_temp);
        mCondition = (TextView)view.findViewById(R.id.weather_condition);
        mHumidity = (TextView)view.findViewById(R.id.weather_humidity);
        mWindSpeed = (TextView)view.findViewById(R.id.weather_wind_speed);
        mWindDirect = (TextView)view.findViewById(R.id.weather_wind_direct);
        mWindDirectIcon = (ImageView)view.findViewById(R.id.weather_wind_direct_icon);
        Log.d(TAG,"city : "+mWeatherCity);
        WeatherApi.getWeatherForecast(mHandler,mWeatherCity,"zh_cn",14);
        return view;
    }


    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.MSG_WEATHER_FORECAST:
                    WeatherForecast weatherForecast = (WeatherForecast)msg.obj;

                    Forecast forecast = weatherForecast.getForecasts().get(0);

                    long dt = forecast.getDt() * 1000;
                    String date = DateFormatter.format(dt);
                    Log.d(TAG,"date :" + date);

                    int todayTemp = (int) forecast.getTemp().getDay();
                    int lowTemp = (int) forecast.getTemp().getMin();
                    int highTemp = (int) forecast.getTemp().getMax();
                    int windSpeed = (int) forecast.getSpeed();
                    int windDegree = (int) forecast.getDeg();
                    int humidity = (int) forecast.getHumidity();
                    mWeatherIcon.setImageResource(IconUtil.setWeatherIcon(forecast.getWeather().get(0).getIcon(),
                            forecast.getWeather().get(0).getId()));
                    mTodayTemp.setText(getResources().getString(R.string.fr_page_today_temp,todayTemp));
                    mLowHighTemp.setText(getResources().getString(R.string.fr_page_low_high_temp,lowTemp,highTemp));
                    mCondition.setText(forecast.getWeather().get(0).getDescription());
                    mHumidity.setText(humidity+"%");
                    mWindSpeed.setText(windSpeed+"m/s");
                    break;
            }
        }
    };
}
