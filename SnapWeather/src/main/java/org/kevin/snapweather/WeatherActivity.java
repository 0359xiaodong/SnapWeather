package org.kevin.snapweather;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import org.kevin.snapweather.api.WeatherApi;
import org.kevin.snapweather.fragment.WeatherLocationFragment;
import org.kevin.snapweather.fragment.WeatherPageFragment;
import org.kevin.snapweather.model.WeatherToday;
import org.kevin.snapweather.sliding.SlidingFragmentActivity;
import org.kevin.snapweather.sliding.widget.SlidingMenu;
import org.kevin.snapweather.util.Constants;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends SlidingFragmentActivity implements ViewPager.OnPageChangeListener {
    protected static final String TAG = WeatherActivity.class.getSimpleName();
    private ViewPager mWeatherPage;
    private WeatherPagerAdapter weatherPagerAdapter;
    private ActionBar mActionBar;
    private WeatherLocationFragment weatherLocation;
    private String[] cities = {"ShenZhen","Beijing","HengYang"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setBehindContentView(R.layout.menu_frame);

        FragmentTransaction mTransaction = this.getSupportFragmentManager()
                .beginTransaction();
        weatherLocation = WeatherLocationFragment.newIntence();
        mTransaction.replace(R.id.menu_frame, weatherLocation);
        mTransaction.commit();

        mActionBar = getActionBar();
        mWeatherPage = (ViewPager)findViewById(R.id.weather_page);
        weatherPagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager());
        mWeatherPage.setAdapter(weatherPagerAdapter);
        mWeatherPage.setOnPageChangeListener(this);

        SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayUseLogoEnabled(false);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setTitle("ShenZhen");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggle();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int position) {
        mActionBar.setTitle(cities[position]);
        switch (position) {
            case 0:
                getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            default:
                getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private static class WeatherHandler extends Handler {

        private final WeakReference<Activity> mActivity;

        public WeatherHandler(Activity activity){
            mActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constants.MSG_WEATHER_TODAY:
                    WeatherToday today = (WeatherToday)msg.obj;
                    Log.d("TAG", today.getWeather().get(0).description);
                    break;
            }
        }
    }

    public class WeatherPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public WeatherPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<Fragment>();
            for (int i=0;i<3;i++)
                mFragments.add(new WeatherPageFragment(cities[i]));
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

    }
}
