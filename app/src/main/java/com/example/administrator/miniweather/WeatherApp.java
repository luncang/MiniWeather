package com.example.administrator.miniweather;

import android.app.Application;

import com.example.api.ApiClient;


/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/4
 */
public class WeatherApp extends Application {

    private static WeatherApp weatherAppInstance;

    public static WeatherApp getInstance(){

        return weatherAppInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        weatherAppInstance = this;

        //init retrofit2
        ApiClient.init();
    }
}
